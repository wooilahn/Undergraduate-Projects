#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <pthread.h>
#include <string.h>

#include "dht11temp.h"
#include "led.h"
#include "gravity.h"
#include "sender.h"
#include "main.h"

void *gyro_count(void *unuse);
void *button_function(void *unuse);

int main(void){
	int receive, button_receive;
	int bedtouch_state; // for bed touch state
	int piltouch_state; // for pillow touch state
	float temp_value;   // for temperature value
	float gyro_value;   // for gyro value
	int gyro_thr_id;    // for gyro_thread id
	int btn_thr_id;     // for button_thread id


	/* device driver open */
	int bedtouch_fd = open(BED_TOUCH_DEV_PATH, O_RDWR);  
	int piltouch_fd = open(PIL_TOUCH_DEV_PATH, O_RDWR);  	
	int buzzer_fd = open(BUZZER_DEV_PATH, O_RDWR);       
	int sound_fd = open(SOUND_DEV_PATH, O_RDWR);         
	int motor_fd = open(MOTOR_DEV_PATH, O_RDWR);
	int led_fd = open(LED_DEV_PATH, O_RDWR);

	if(bedtouch_fd == -1 || buzzer_fd == -1 || piltouch_fd == -1 
	|| motor_fd == -1 || sound_fd == -1 || led_fd == -1){	// error exist
		printf("device not open!\n");
		return -1;
	}

	int btr_thr_id = pthread_create(&btn_thread, NULL, button_function, 0);
	write(led_fd, "lion", 4);

	/* if not wake-up state */
	while(!wakeup_flag) {
		
		read(bedtouch_fd, &bedtouch_state, sizeof(bedtouch_state));
		read(piltouch_fd, &piltouch_state, sizeof(piltouch_state));
		
		/* if bed is touched */
		if(bedtouch_state && piltouch_state){
		  if(!sleep_flag){
		    printf("gyro_thread start! \n");
		    gyro_thr_id = pthread_create(&thread, NULL, gyro_count, 0);
		  }

		  /* if in sleep state */
  		  if(sleep_flag){
			printf("sleep mode start! \n");

			if(moodlight_flag){
				write(led_fd, "off", 3);
				moodlight_flag = 0;
			}

			if(window_flag){
			  /* send "down" to receiver for lowering curtain */
			  send_from_server("down");
			  window_flag--;
			}
			
	 		/* temperature estimate */		  
			temp_value = read_dht11_dat();
		        
			/* if temperature value exists */
			if(temp_value){
				if(temp_value > 22.0){ // temperature is high
					printf("temp is %.3f\n",temp_value);
					LED1_ON();
				}
				else{  //temperature is low
					printf("temp is %.3f\n",temp_value);
					LED2_ON();
		        	}
		  	}
		  }
		  else{
                      pthread_join(thread, (void **)&sleep_flag);
		  }
		}
		/* if not touched. */
		else{
			sleep_flag = 0;
			moodlight_flag = 1;
		}
	}

	/* if wake-up state  */
	if(wakeup_flag){ 
		
		
		LED_OFF();
		/* send "up" to receiver for raise curtain */
		send_from_server("up");
		
		int cnt = 0;
		int button_fd = open(BUTTON_DEV_PATH, O_RDWR);

		while(1){
			write(buzzer_fd, "on", 2);
			read(sound_fd, &receive, sizeof(int));
                        read(button_fd, &button_receive, sizeof(int));

			if(button_receive)
				break;


			if(!receive)
				cnt++;

			/* after buzzer ringing 5 times, servo moter lift bed 70 degrees.*/
			if(cnt == 5){
				write(motor_fd, "on", 2);
				sleep(5);
				break;
			}	

			sleep(1);
		}
	}

	write(led_fd, "on", 2);
	
	/* close device driver */
	close(bedtouch_fd);
	close(piltouch_fd);
	close(buzzer_fd);
	close(sound_fd);
	close(motor_fd);
	close(led_fd);

	return 0;
}

void *gyro_count(void *unuse){ //gyro thread function.

	float gyro_list[5] = { 0 };

	for(int i = 0; i < 5; i++){
		sleep(1);
		
		gyro_list[i] = gyro();
		printf("gyro_list[i] : %.3f\n", gyro_list[i]);

		/* compare prior value 5 times */
		if(i != 0){
			float gyro_dist = gyro_list[i] - gyro_list[i-1];

			/* measure difference gyro distance  */
			if(gyro_dist >= -0.2 && gyro_dist <= 0.2)
				continue;
			else{
				sleep_flag = 0;
				pthread_exit((void *)sleep_flag);
			}
		}
	}

	sleep_flag = 1;
	pthread_exit((void *)sleep_flag);
}

void *button_function(void *unuse){

	int button_fd = open(BUTTON_DEV_PATH, O_RDWR);
	int button_state = 0;

	while(1){
		read(button_fd, &button_state, sizeof(button_state));
		
		if(button_state)
			break;	
	}

	wakeup_flag = 1;
	close(button_fd);
	pthread_exit((void*)&button_fd);
}
