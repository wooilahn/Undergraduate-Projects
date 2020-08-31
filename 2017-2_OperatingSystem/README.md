2017-2 Operating System
=============  
* **Assignment 0**
    * Parsing commands(utils.c)
    * cd,pwd command implementation(commands.c)
  
* **Assignment 1** 
    * Process creation [Fork(), execv()], Path resolution
    * Inter-Process Communication and threading with 'domain socket' [dup(), dup2(), listen(), pthread]
    * Signal handling (signal_handlers.c) [Ctrl+C, Ctrl+Z]
  
* **Assignment 2(implemented imperfectly)**
    * modifying 26 operations related to FUSE (File System in User Space)
    
* **Assignment 3(implemented imperfectly)**
    * Implementing Malloc, Realloc, Free function
    * There are three algorithms for searching the list of free blocks for a specific amount of memory.  
      * First fit: Begins at the start of primary memory and allocates memory from the first hole it encounters large enough to satisfy the request.
      * Best fit: The allocator places a process in the smallest block of unallocated memory in which it will fit.
      * Worst fit: The memory manager places a process in the largest block of unallocated memory available.
