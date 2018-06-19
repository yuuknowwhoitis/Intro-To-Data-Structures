#include <stdio.h>
#include <stdlib.h>

char* make_string_from(char* from, int count) {
	/* TODO 2 */
	char * new_string = calloc(count + 1, sizeof(char));
	for(int i = 0; i < count; i++){
		new_string[i] = from[i];
	}
	return new_string;
	/* TODO 2 */
}

int main(int argc, char** argv) {
	/* TODO 1 */

   for(int i = 0; i < argc; i++){
   	printf("%s\n", argv[i]);
   }
 	/* TODO 1 */
    
  
 	/* TODO 3 */
   //char* char_buffer = calloc(1000, sizeof(int));
   char char_buffer[1000];
   int buffer_index = 0;
   char rule[1000];

   printf("Please input:\n");
   char c = getchar();
   while(c != EOF){
   	if(c == ':'){
   		for(int j = 0; j < buffer_index ; j++){
   			rule[j] = char_buffer[j];
   		}
   		buffer_index = 0;
   		
   	}
   	else if(c == ',' || c == '\n'){
   		char* expansion = calloc(buffer_index + 2, sizeof(char));
   		for(int j = 0; j < buffer_index ; j++){
   			expansion[j] = char_buffer[j];
   		}
   		printf("A potential expansion of rule %s is %s.\n", rule, expansion);
   		buffer_index = 0;
   		free(expansion);
   	}

   	else{
		char_buffer[buffer_index] = c;
		buffer_index++;
   	}

   	c = getchar();
   }
 	/* TODO 3 */

   
}


