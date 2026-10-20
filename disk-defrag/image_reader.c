#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>
#include <string.h>


#define N_DBLOCKS 10
#define N_IBLOCKS 4
#define SUPER_BLOCK_START 512
#define BOOT_REGION_SIZE 512
#define SUPER_BLOCK_SIZE 512
#define REGION_OFFSET 1024

/** Superblock outlining various regions in the file system */
typedef struct
{
    int blocksize; /* size of blocks in bytes */
    int inode_offset; /* offset of inode region in blocks */
    int data_offset; /* data region offset in blocks */
    int swap_offset; /* swap region offset in blocks */
    int free_inode; /* head of free inode list */
    int free_block; /* head of free block list */
} superblock;

/** Index node for any given file in a file system */
typedef struct
{
    int next_inode; /* list for free inodes */
    int protect; /*  protection field */
    int nlink; /* Number of links to this file */
    int size; /* Number of bytes in file */
    int uid; /* Owner's user ID */
    int gid; /* Owner's group ID */
    int ctime; /* Time field */
    int mtime; /* Time field */
    int atime; /* Time field */
    int dblocks[ N_DBLOCKS ]; /* Pointers to data blocks */
    int iblocks[ N_IBLOCKS ]; /* Pointers to indirect blocks */
    int i2block; /* Pointer to doubly indirect block */
    int i3block; /* Pointer to triply indirect block */
} inode;

/** Representation of a file system's different regions and binary data */
typedef struct
{
	unsigned char *buffer;
    superblock *sb;     /* superblock for the disk image */
    inode *inodeRegion; /* start of inode region */
    char *dataRegion;   /* start of data region */
    int freeBlockIndex; /* index of the next free block */
} diskImage;

void printData( FILE *out, diskImage *di )
{
    int numBlocks = ( di->sb->swap_offset - di->sb->data_offset );

    for( int blockIndex = 0; blockIndex < numBlocks; blockIndex++ )
    {
        fprintf( out, "Data Block %d - Value: ", blockIndex );
        int *block = ( int * ) ( di->dataRegion + blockIndex * di->sb->blocksize );
        fprintf( out, "%d\n", *block );
    }
}

void printInode( FILE *out, inode *node )
{
	fprintf( out,
			"Next inode: %d\n"
			"Protect: %d\n"
			"Nlink: %d\n"
			"Size: %d\n"
			"Uid: %d\n"
			"Gid: %d\n"
			"Ctime: %d\n"
			"Mtime: %d\n"
			"Atime: %d\n"
			"Dblocks: [%d, %d, %d, %d, %d, %d, %d, %d, %d, %d]\n"
			"Iblocks: [%d, %d, %d, %d]\n"
			"I2block: %d\n"
			"I3block: %d\n",
			node->next_inode,
			node->protect,
			node->nlink,
			node->size,
			node->uid,
			node->gid,
			node->ctime,
			node->mtime,
			node->atime,
			node->dblocks[0], node->dblocks[1], node->dblocks[2], node->dblocks[3], node->dblocks[4], node->dblocks[5], node->dblocks[6], node->dblocks[7], node->dblocks[8], node->dblocks[9],
			node->iblocks[0], node->iblocks[1], node->iblocks[2], node->iblocks[3],
			node->i2block,
			node->i3block
	);
}

void printInodes( FILE *out, diskImage *di )
{
    //Iterate through each inode
	inode *currentInode = di->inodeRegion;
    int inodeRegionSize = ( di->sb->data_offset - di->sb->inode_offset ) * di->sb->blocksize; /* inode region size in bytes */
    int inodeIndex = 0;

    //Defrag the data section for each inode
    while ( ( (inodeIndex + 1) * sizeof(inode) ) < inodeRegionSize )
    {
    	fprintf( out, "\nInode %d -\n", inodeIndex );
    	printInode( out, currentInode );
        inodeIndex++;
        currentInode++;
    }
    fprintf( out, "\n" );

}

void printSuperblock( FILE *out, diskImage *di )
{
	superblock *sb = di->sb;
	fprintf( out,
			"Superblock-\n"
			"Blocksize: %d\n"
			"Data offset: %d\n"
			"Free block: %d\n"
			"Free inode: %d\n"
			"Inode offset: %d\n"
			"Swap offset: %d\n",
			sb->blocksize,
			sb->data_offset,
			sb->free_block,
			sb->free_inode,
			sb->inode_offset,
			sb->swap_offset);
}

void printDiskImage( FILE *out, diskImage *di )
{
	printSuperblock( out, di );
	printInodes( out, di );
	printData( out, di );
}

int main(int argc, char *argv[]) {
    //make sure number of args passed is correct
    if(argc != 2) {
        perror("Invalid number of arguments");
        exit(1);
    }    
    
    FILE *f;                    //input file pointer
    unsigned char *buffer;      //char array to store file contents
    size_t bytes;               //size of file, read in
    struct stat fileStat;       //struct to hold file size

    //use stat to get size of file
    if(stat(argv[1], &fileStat) < 0) {
        perror("Cannot get size");
        exit(1);
    }    
    buffer = malloc(fileStat.st_size);
    if(buffer == NULL) {
        perror("Memory allocation error");
        exit(1);
    }
    //open input file
    f = fopen(argv[1], "r");
    if(f == NULL) {
        perror("fopen error");
        exit(1);
    }     
    //read file and store it into our byte array
    bytes = fread((void *)buffer, 1,fileStat.st_size, f);
    if(bytes != fileStat.st_size) {
        perror("Error reading file");
        exit(1);
    }
    fclose(f);
	diskImage di;
    // superblock is located 512 bytes into the buffer
    di.buffer = buffer;
    di.sb = ( superblock * ) ( buffer + SUPER_BLOCK_START );
    // inode region located at address 1024 + (inode_offset * block_size)
    di.inodeRegion = ( inode * ) ( buffer + REGION_OFFSET + di.sb->inode_offset * di.sb->blocksize );
    printf("inodes begin writing at %d bytes", + REGION_OFFSET + di.sb->inode_offset * di.sb->blocksize);
    // data region located at address 1024 + (data_offset * block_size)
    di.dataRegion = ( char * ) ( buffer + REGION_OFFSET + di.sb->data_offset * di.sb->blocksize );
    printf("data starts writing at %d bytes", REGION_OFFSET + di.sb->data_offset * di.sb->blocksize);
	FILE *out = fopen( "diskdefragoutput.txt", "w+" );

	printDiskImage( out, &di );
	fclose(out);
	free(di.buffer);
}

