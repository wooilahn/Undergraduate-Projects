#include "utils.h"
#include <string.h>
#include <stdlib.h>

void mysh_parse_command(const char* command,
                        int *argc, char*** argv)
{

 char str[9000];
 char* token;
 int len, idx = 0;

 strcpy(str, command);
 len = strlen(str);

 for(int i=0; i<len; i++){
     if(32 <= str[i] && str[i] <= 126)
	 continue;
     else
        str[i] = ' ';
 }

 (*argv) = (char**)calloc(100, sizeof(char*));

 token = strtok(str, " ");
 
 while(token != NULL){
  (*argv)[idx] = (char*)calloc(8100, sizeof(char));
  strcpy((*argv)[idx++], token);
  token = strtok(NULL, " "); 
 }

 if(idx == 0){
   idx = 1;
   (*argv)[0] = (char*)calloc(10, sizeof(char));
   strcpy((*argv)[0], "");
 }

 *argc = idx;

 return;

}
