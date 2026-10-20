"""Check a defragmented disk image against its original.

Usage: verify_defrag.py <original image> <defragmented image>
Exits non-zero and prints the first problems if any file's contents changed,
blocks are not laid out contiguously, or the swap region was not preserved.
"""
import struct
import sys

N_DBLOCKS, N_IBLOCKS = 10, 4
INODE_FMT = "<9i" + f"{N_DBLOCKS}i" + f"{N_IBLOCKS}i" + "2i"
INODE_SIZE = struct.calcsize(INODE_FMT)
REGION_OFFSET = 1024


def parse(path):
    data = open(path, "rb").read()
    bs, ino_off, data_off, swap_off, free_ino, free_blk = struct.unpack_from("<6i", data, 512)
    sb = dict(bs=bs, ino_off=ino_off, data_off=data_off, swap_off=swap_off, free_ino=free_ino, free_blk=free_blk)
    ino_start = REGION_OFFSET + ino_off * bs
    count = (data_off - ino_off) * bs // INODE_SIZE
    inodes = [struct.unpack_from(INODE_FMT, data, ino_start + i * INODE_SIZE) for i in range(count)]
    return data, sb, inodes


def block(data, sb, n):
    start = REGION_OFFSET + (sb["data_off"] + n) * sb["bs"]
    return data[start:start + sb["bs"]]


def pointers(data, sb, n):
    return list(struct.unpack_from(f"<{sb['bs'] // 4}i", block(data, sb, n)))


def file_blocks(data, sb, ino):
    """Data block numbers of a file in logical order (indirect blocks themselves excluded)."""
    size = ino[3]
    need = -(-size // sb["bs"])
    out = list(ino[9:9 + N_DBLOCKS])[:need]

    def walk(ptr, depth):
        if len(out) >= need:
            return
        if depth == 0:
            out.append(ptr)
            return
        for p in pointers(data, sb, ptr):
            if len(out) >= need:
                return
            walk(p, depth - 1)

    for p in ino[9 + N_DBLOCKS:9 + N_DBLOCKS + N_IBLOCKS]:
        walk(p, 1)
    walk(ino[-2], 2)
    walk(ino[-1], 3)
    return out[:need]


def layout(data, sb, ino):
    """Every block a file uses, pointer blocks included, in the order a defragmenter lays them out."""
    size = ino[3]
    need = -(-size // sb["bs"])
    out, used = [], [0]
    for p in list(ino[9:9 + N_DBLOCKS])[:need]:
        out.append(p); used[0] += 1

    def walk(ptr, depth):
        if used[0] >= need:
            return
        out.append(ptr)
        if depth == 0:
            used[0] += 1
            return
        for p in pointers(data, sb, ptr):
            if used[0] >= need:
                return
            walk(p, depth - 1)

    for p in ino[9 + N_DBLOCKS:9 + N_DBLOCKS + N_IBLOCKS]:
        walk(p, 1)
    walk(ino[-2], 2)
    walk(ino[-1], 3)
    return out


def contents(data, sb, ino):
    return b"".join(block(data, sb, n) for n in file_blocks(data, sb, ino))[:ino[3]]


def main(orig_path, new_path):
    a, sa, ia = parse(orig_path)
    b, sb_, ib = parse(new_path)
    problems = []
    if (sa["bs"], sa["ino_off"], sa["data_off"], sa["swap_off"]) != (sb_["bs"], sb_["ino_off"], sb_["data_off"], sb_["swap_off"]):
        problems.append(f"superblock layout changed: {sa} -> {sb_}")
    if len(a) != len(b):
        problems.append(f"image size changed: {len(a)} -> {len(b)} bytes")
    swap_a = a[REGION_OFFSET + sa["swap_off"] * sa["bs"]:]
    swap_b = b[REGION_OFFSET + sb_["swap_off"] * sb_["bs"]:]
    if swap_a != swap_b:
        problems.append(f"swap region differs ({len(swap_a)} vs {len(swap_b)} bytes)")
    files = 0
    for i, (x, y) in enumerate(zip(ia, ib)):
        if x[2] <= 0:  # nlink == 0: free inode
            continue
        files += 1
        if contents(a, sa, x) != contents(b, sb_, y):
            problems.append(f"inode {i}: file contents changed")
        blocks = layout(b, sb_, y)
        if blocks and blocks != list(range(blocks[0], blocks[0] + len(blocks))):
            problems.append(f"inode {i}: blocks not contiguous")
    print(f"{files} files checked, {len(problems)} problems")
    for p in problems[:8]:
        print("  -", p)
    return 1 if problems else 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1], sys.argv[2]))
