#include <stdio.h>
#include <string.h>
#include <unistd.h>

#include "commands.h"

int do_cd(int argc, char** argv) {
  if (!validate_cd_argv(argc, argv))
    return -1;
 
  int res;
  char buf[9000];

  res = chdir(argv[1]);

  if(res == -1)
    return -1;
  
  return 0;
}

int do_pwd(int argc, char** argv) {
  if (!validate_pwd_argv(argc, argv))
    return -1;


  char buf[9000];

  if(getcwd(buf, 9000) == NULL)
     return -1;
  else
     printf("%s\n", buf);

  return 0;
}

int validate_cd_argv(int argc, char** argv) {

  if(argc != 2)
   return 0;
  else if(strcmp(argv[0], "cd"))
   return 0;
  else
   return 1;
}

int validate_pwd_argv(int argc, char** argv) {
  
 if(argc != 1 || (argc == 1 && strcmp(argv[0], "pwd")))
  return 0;
 else if(argc == 1 && !strcmp(argv[0], "pwd"))
  return 1;
 else
  return 0;
}
