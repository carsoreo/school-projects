#!/bin/sh
# Builds defrag, runs it on each sample fragmented disk, and checks the result with verify_defrag.py:
# every file's contents must be unchanged, each file's blocks contiguous, and the swap region preserved.
set -e
cd "$(dirname "$0")"
gcc -std=c11 -Wall -O1 -o defrag defrag.c
status=0
for i in 0 1 2 3; do
  ./defrag "disk_frag_$i"
  printf 'disk_frag_%s: ' "$i"
  ${PYTHON:-python3} verify_defrag.py "disk_frag_$i" disk_defrag || status=1
done
rm -f disk_defrag
exit $status
