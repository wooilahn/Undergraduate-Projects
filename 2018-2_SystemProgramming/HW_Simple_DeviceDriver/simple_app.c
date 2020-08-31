#include <stdio.h>
#include <errno.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>

#define BUFFER_LENGTH 256
#define DEV_PATH "/dev/simple_dev"
static char receive[BUFFER_LENGTH];

int main(){
	int ret, fd;
	char stringToSend[BUFFER_LENGTH];

	fd = open(DEV_PATH, O_RDWR); // Open the device with read/write access

	if(fd < 0){
		perror("Failed to open the device...");
		return errno;
	}

	printf("Type in a short string to send to the kernel module:\n");
	scanf("%[^\n]%*c" , stringToSend);
	printf("Writing message to the device [%s].\n", stringToSend);

	ret = write(fd, stringToSend, 30); //Send the string to the LKM

	if(ret < 0){
		perror("Failed to write the message to the device.");
		return errno;
	}

	printf("Reading from the device...\n");

	ret = read(fd, receive, 30); // Get the Response from the LKM

	if(ret < 0){
		perror("Failed to the read the message from the device.");
		return errno;
	}

	printf("The received message is: [%s]\n", receive);
	printf("End of the program\n");
	return 0;
}
