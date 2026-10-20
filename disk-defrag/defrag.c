#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>
#include <string.h>
#include <stdbool.h>

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
    int inode_offset; /* offdset of inode region in blocks */
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

int main(int argc, char *argv[]) {

    if(argc != 2) {
        perror("Invalid number of arguments");
        exit(1);
    }

    FILE *f;                    //input file pointer
    FILE *o;                    //output file pointer
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
    bytes = fread((void *)buffer, 1, fileStat.st_size, f);
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
    // data region located at address 1024 + (data_offset * block_size)
    di.dataRegion = ( char * ) ( buffer + REGION_OFFSET + di.sb->data_offset * di.sb->blocksize );
    //number of inodes in the file
    int inodeRegionSize = ( di.sb->data_offset - di.sb->inode_offset ) * di.sb->blocksize; /* inode region size in bytes */
    //the index of the current inode we are using
    int inodeIndex = 0;

    int numInodes = 0;
    while ( ( (inodeIndex + 1) * sizeof(inode) ) < inodeRegionSize )
    {
        numInodes++;
        inodeIndex++;
    }
    // printf("there are %d inodes in this file", numInodes);
    diskImage *newDi = (diskImage *)calloc(1, sizeof(diskImage));
    
    //set up new disk image
    int numBlocks = ( di.sb->swap_offset - di.sb->data_offset );
    newDi->sb = di.sb;
    newDi->inodeRegion = (inode *)calloc(1, sizeof(inode) * numInodes);
    newDi->dataRegion = (char *)calloc(1, numBlocks * di.sb->blocksize);

    //current index of what we have filled up in the memory of datablocks
    int freeMem = 0;
    // the node that we are currently inspecting
    inode *node = di.inodeRegion;
    //the new Inode we are creating
    inode *newNode = newDi->inodeRegion;
    //how big the inode region is
    inodeRegionSize = ( di.sb->data_offset - di.sb->inode_offset ) * di.sb->blocksize; /* inode region size in bytes */
    //the index of the current inode we are using
    inodeIndex = 0;

    /**
     * This while loop iterates through all of the inodes and checks their nlink num
     * to see if they are free or in use. If they are in use it starts inspecting
     * their dblocks to see if there are any that are out of order and need to be
     * defragmented. 
    */
    while ( ( (inodeIndex + 1) * sizeof(inode) ) < inodeRegionSize )
    {
        // //prints out inode information for debugging
    	// printf("\nInode %d -\n", inodeIndex );
        // printf(
		// 	"Next inode: %d\n"
		// 	"Protect: %d\n"
		// 	"Nlink: %d\n"
		// 	"Size: %d\n"
		// 	"Uid: %d\n"
		// 	"Gid: %d\n"
		// 	"Ctime: %d\n"
		// 	"Mtime: %d\n"
		// 	"Atime: %d\n"
		// 	"Dblocks: [%d, %d, %d, %d, %d, %d, %d, %d, %d, %d]\n"
		// 	"Iblocks: [%d, %d, %d, %d]\n"
		// 	"I2block: %d\n"
		// 	"I3block: %d\n",
		// 	node->next_inode,
		// 	node->protect,
		// 	node->nlink,
		// 	node->size,
		// 	node->uid,
		// 	node->gid,
		// 	node->ctime,
		// 	node->mtime,
		// 	node->atime,
		// 	node->dblocks[0], node->dblocks[1], node->dblocks[2], node->dblocks[3], node->dblocks[4], node->dblocks[5], node->dblocks[6], node->dblocks[7], node->dblocks[8], node->dblocks[9],
		// 	node->iblocks[0], node->iblocks[1], node->iblocks[2], node->iblocks[3],
		// 	node->i2block,
		// 	node->i3block
	    // );
        newNode->protect = node->protect;
        newNode->nlink = node->nlink;
        newNode->size = node->size;
        newNode->uid = node->uid;
        newNode->gid = node->gid;
        newNode->ctime = node->ctime;
        newNode->mtime = node->mtime;
        newNode->atime = node->atime;
        newNode->next_inode = node->next_inode;
        //loop through the dblocks and check each of them 
        for(int i = 0; i < N_DBLOCKS; i++) {
            // printf("current Dblock idx in our inode = %d\n", i);
            //if the dlbock points to -1 it is not in use so we can move on
            if(node->dblocks[i] == -1) {
                newNode->dblocks[i] = -1;
            }
            else {
                // if the dblock isn't -1 then we need to move it into the new disk image
                // printf("block %d found at dlbock idx %d in our inode moving to block %d in new disk image\n", node->dblocks[i], i, freeMem);
                int *newBlockPtr = (int *) (newDi->dataRegion + freeMem * newDi->sb->blocksize);
                int *oldBlockPtr = (int *) (di.dataRegion + node->dblocks[i] * di.sb->blocksize);
                memcpy(newBlockPtr, oldBlockPtr, newDi->sb->blocksize);
                newNode->dblocks[i] = freeMem;
                freeMem++;
            }
        }
        // loop through all of the Iblocks in the inode to see if they are in use
        for(int i = 0; i < N_IBLOCKS; i++) {
            // printf("current Iblock idx in our inode = %d\n", i);
            if(node->iblocks[i] == -1) {
                //if we hit free data copy the -1 over and move on
                newNode->iblocks[i] = -1;
            }
            else {
                //reference for where the actual indirect pointer should be stored in memory
                int pointerReference = freeMem;
                freeMem++;
                //if the iblock isn't -1 then we need to check where it points and start reading from there
                int *iBlockPtr = (int *) (di.dataRegion + node->iblocks[i] * di.sb->blocksize);
                int *newIBlockPtr = (int *) (newDi->dataRegion + pointerReference * newDi->sb->blocksize);
                for(int j = 0; j < di.sb->blocksize / 4; j++) {
                    if(iBlockPtr[j] == -1) {
                        //if we hit free data copy the -1 over and move on
                        newIBlockPtr[j] = -1;
                    }
                    else {
                        //if the block isn't -1 then we need to move it into the new disk image
                        // printf("block %d found in iblock %d idx %d in our inode. Moving to block %d in new disk image\n", iBlockPtr[j], i, j, freeMem);
                        //make pointers to each region in memory
                        //this is the new area that we need to fill up
                        int *newBlockPtr = (int *) (newDi->dataRegion + freeMem * newDi->sb->blocksize);
                        //this is the data we are copying in
                        int *oldBlockPtr = (int *) (di.dataRegion + iBlockPtr[j] * di.sb->blocksize);
                        //copy the data
                        memcpy(newBlockPtr, oldBlockPtr, newDi->sb->blocksize);
                        //update the pointer in the inode in the iblock
                        newIBlockPtr[j] = freeMem;
                        freeMem++;
                    }
                }
                //update the indirect pointer to the front of all of the blocks it points to
                // printf("iblock %d now points to block %d\n", i, pointerReference);
                newNode->iblocks[i] = pointerReference;
            }
        }
        //check the i2block to see if it is in use
        if(node->i2block == -1) {
            //if we hit free data copy the -1 over and move on
            newNode->i2block = -1;
        }
        else {
            int i2PointerReference = freeMem;
            freeMem++;
            //if the i2block isn't -1 then we need to check where it points and start reading from there
            int *i2BlockPtr = (int *) (di.dataRegion + node->i2block * di.sb->blocksize);
            int *newi2BlockPtr = (int *) (newDi->dataRegion + i2PointerReference * newDi->sb->blocksize);
            for(int i = 0; i < di.sb->blocksize / 4; i++) {
                if(i2BlockPtr[i] == -1) {
                    //if we hit free data copy the -1 over and move on
                    newi2BlockPtr[i] = -1;
                }
                else {
                    int pointerReference = freeMem;
                    freeMem++;
                    //if the iblock isn't -1 then we need to check where it points and start reading from there
                    int *iBlockPtr = (int *) (di.dataRegion + i2BlockPtr[i] * di.sb->blocksize);
                    int *newIBlockPtr = (int *) (newDi->dataRegion + pointerReference * newDi->sb->blocksize);
                    for(int j = 0; j < di.sb->blocksize / 4; j++) {
                        if(iBlockPtr[j] == -1) {
                            // printf("free memory hit\n");
                            newIBlockPtr = (int*)-1;
                        }
                        else {
                            //if the block isn't -1 then we need to move it into the new disk image
                            
                            //make pointers to each region in memory
                            //this is the new area that we need to fill up
                            int *newBlockPtr = (int *) (newDi->dataRegion + freeMem * newDi->sb->blocksize);
                            //this is the data we are copying in
                            int *oldBlockPtr = (int *) (di.dataRegion + iBlockPtr[j] * di.sb->blocksize);
                            // printf("block %d found in iblock2, iblock %d idx %d in our inode. Moving to block %d in new disk image val is %d\n", iBlockPtr[j], i, j, freeMem, *oldBlockPtr);
                            //copy the data
                            memcpy(newBlockPtr, oldBlockPtr, newDi->sb->blocksize);
                            //update the pointer in the inode in the iblock
                            newIBlockPtr[j] = freeMem;
                            freeMem++;
                        }
                    }
                    //update the indirect pointer to the front of all of the blocks it points to
                    // printf("iblock %d now points to block %d in i2block\n", i, pointerReference);
                    newi2BlockPtr[i] = pointerReference;
                }
            }
            // printf("i2block now points to block %d", i2PointerReference);
            newNode->i2block = i2PointerReference;
        }
        //check i3block to see if there is a pointer there
        if(node->i3block == -1) {
            newNode->i3block = -1;
        }
        else {
            int i3PointerReference = freeMem;
            freeMem++;
            //if the i3block isn't -1 then we need to check where it points and start reading from there
            int *i3BlockPtr = (int *) (di.dataRegion + node->i3block * di.sb->blocksize);
            int *newi3BlockPtr = (int *) (newDi->dataRegion + i3PointerReference * newDi->sb->blocksize);
            for(int i = 0; i < di.sb->blocksize / 4; i++) { 
                if(i3BlockPtr[i] == -1) {
                    newi3BlockPtr[i] = -1;
                }
                else {
                    int i2PointerReference = freeMem;
                    freeMem++;
                    int *i2BlockPtr = (int *) (di.dataRegion + i3BlockPtr[i] * di.sb->blocksize);
                    int *newi2BlockPtr = (int *) (newDi->dataRegion + i2PointerReference * newDi->sb->blocksize); 
                    for(int j = 0; j < di.sb->blocksize / 4; j++) {
                        if(i2BlockPtr[j] == -1) {
                            newi2BlockPtr[j] = -1;
                        }
                        else {
                            int pointerReference = freeMem;
                            freeMem++;
                            //if the iblock isn't -1 then we need to check where it points and start reading from there
                            int *iBlockPtr = (int *) (di.dataRegion + i2BlockPtr[j] * di.sb->blocksize);
                            int *newIBlockPtr = (int *) (newDi->dataRegion + pointerReference * newDi->sb->blocksize);
                            for(int k = 0; k < di.sb->blocksize / 4; k++) {
                                if(iBlockPtr[k] == -1) {
                                    newIBlockPtr[k] = -1;
                                }
                                else {
                                    //this is the new area we need to copy to
                                    int *newBlockPtr = (int *) (newDi->dataRegion + freeMem * newDi->sb->blocksize);
                                    //this is the data we are copying in
                                    int *oldBlockPtr = (int *) (di.dataRegion + iBlockPtr[k] * di.sb->blocksize);
                                    // printf("block %d found in iblock3, in iblock2 %d, iblock %d idx %d in our inode. Moving to block %d in new disk image val is %d\n", iBlockPtr[k], i, j, k, freeMem, *oldBlockPtr);
                                    memcpy(newBlockPtr, oldBlockPtr, newDi->sb->blocksize);
                                    newIBlockPtr[k] = freeMem;
                                    freeMem++;
                                }
                            }
                            // printf("iblock %d now points to block %d in i2block %d in iblock3\n", j , pointerReference, i);
                            newi2BlockPtr[j] = pointerReference;
                        }
                    }
                    // printf("i2block %d now points to block %d in i3block", i, i2PointerReference);
                    newi3BlockPtr[i] = i2PointerReference;
                }
            }
            newNode->i3block = i3PointerReference;
            // printf("i3block now points to block %d", i3PointerReference);
        }
        newNode++;
        inodeIndex++;
        node++;
    }
    newNode = newDi->inodeRegion;
    //how big the inode region is
    inodeRegionSize = ( di.sb->data_offset - di.sb->inode_offset ) * di.sb->blocksize; /* inode region size in bytes */
    //the index of the current inode we are using
    inodeIndex = 0;
    //this just prints out the data after it is defragged to check if it is correct
    //  printf("Start of Defragmented data \n\n\n\n\n");
     while ( ( (inodeIndex + 1) * sizeof(inode) ) < inodeRegionSize )
    {
        //prints out inode information for debugging
    	// printf("\nInode %d -\n", inodeIndex );
        // printf(
		// 	"Next inode: %d\n"
		// 	"Protect: %d\n"
		// 	"Nlink: %d\n"
		// 	"Size: %d\n"
		// 	"Uid: %d\n"
		// 	"Gid: %d\n"
		// 	"Ctime: %d\n"
		// 	"Mtime: %d\n"
		// 	"Atime: %d\n"
		// 	"Dblocks: [%d, %d, %d, %d, %d, %d, %d, %d, %d, %d]\n"
		// 	"Iblocks: [%d, %d, %d, %d]\n"
		// 	"I2block: %d\n"
		// 	"I3block: %d\n",
		// 	newNode->next_inode,
		// 	newNode->protect,
		// 	newNode->nlink,
		// 	newNode->size,
		// 	newNode->uid,
		// 	newNode->gid,
		// 	newNode->ctime,
		// 	newNode->mtime,
		// 	newNode->atime,
		// 	newNode->dblocks[0], newNode->dblocks[1], newNode->dblocks[2], newNode->dblocks[3], newNode->dblocks[4], newNode->dblocks[5], newNode->dblocks[6], newNode->dblocks[7], newNode->dblocks[8], newNode->dblocks[9],
		// 	newNode->iblocks[0], newNode->iblocks[1], newNode->iblocks[2], newNode->iblocks[3],
		// 	newNode->i2block,
		// 	newNode->i3block
	    // );
        inodeIndex++;
        newNode++;
    }
    //printf("num blocks %d\n", numBlocks);
    //creates the free memory linked list at the start of the freeMem pointer
    for( int blockIndex = freeMem; blockIndex < numBlocks - 1; blockIndex++ )
    {
        int *newVal = (int *)calloc(1, di.sb->blocksize);
        *newVal = blockIndex + 1;
        int *newBlockPtr = (int *)(newDi->dataRegion + blockIndex * newDi->sb->blocksize);
        memcpy(newBlockPtr, newVal, newDi->sb->blocksize);
    }
    //updates the last val at the end of free mem to -1
    int *newVal = (int *)calloc(1, di.sb->blocksize);
    *newVal = -1;
    int *newBlockPtr = (int *) (newDi->dataRegion + (numBlocks - 1) * newDi->sb->blocksize);
    memcpy(newBlockPtr, newVal, newDi->sb->blocksize);
    // printf("%d\n", freeMem);
    newDi->freeBlockIndex = freeMem;
    newDi->sb->free_block = freeMem;
	// printf(
	// 		"Superblock-\n"
	// 		"Blocksize: %d\n"
	// 		"Data offset: %d\n"
	// 		"Free block: %d\n"
	// 		"Free inode: %d\n"
	// 		"Inode offset: %d\n"
	// 		"Swap offset: %d\n",
	// 		sb->blocksize,
	// 		sb->data_offset,
	// 		sb->free_block,
	// 		sb->free_inode,
	// 		sb->inode_offset,
	// 		sb->swap_offset);
    for( int blockIndex = 0; blockIndex < 100; blockIndex++ )
    {
        // printf("Data Block %d - Value: ", blockIndex );
        // printf("%d\n", *block );
    }

    //opening a file for writing
    o = fopen("disk_defrag", "wb");
    if(o == NULL) {
        perror("fopen error");
        exit(1);
    }    
    
    fwrite(di.buffer, 512, 1, o);
    fwrite(newDi->sb, 512, 1, o);
    // printf("data block 0 = %d\n", *block);
    int dataOffset = (REGION_OFFSET + di.sb->data_offset * di.sb->blocksize) - ((numInodes * sizeof(inode)) + REGION_OFFSET);
    // printf("dataOffset %d\n", dataOffset);
    fwrite(newDi->inodeRegion, numInodes * sizeof(inode) + dataOffset, 1, o);
    // printf("inode starts writing at %d\n", 512 + 512);
    fwrite(newDi->dataRegion, newDi->sb->blocksize, numBlocks, o);
    // printf("data starts writing at %d\n", (numInodes * newDi->sb->blocksize) + 512 + 512);
    // printf("data offset %d, inode offset %d\n", newDi->sb->data_offset, newDi->sb->inode_offset);
    free(di.buffer);
    fclose(o);
    exit(0);
}
