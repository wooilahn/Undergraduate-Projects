#include <linux/gpio.h>
#include <linux/init.h>
#include <linux/module.h>
#include <linux/delay.h>
#include <linux/fs.h>
#include <linux/uaccess.h>
#include <linux/kernel.h>
#include <linux/slab.h>
#include <linux/cdev.h>
#define LED      19
#define LED_B    13
#define DEV_NAME "led_dev"
#define DEV_NUM  233

MODULE_LICENSE("GPL");

static char *msg = NULL;

int led_open(struct inode *pinode, struct file *pfile) {
	
	printk(KERN_ALERT "OPEN led_dev\n");
	
	gpio_request(LED, "LED");
	gpio_direction_output(LED, 0);
	gpio_request(LED_B, "LED_B");
	gpio_direction_output(LED_B, 0);

	return 0;
}

int led_close(struct inode *pinode, struct file *pfile) {
	
	printk(KERN_ALERT, "RELEASE led_dev\n");
	
	gpio_set_value(LED, 0);
	gpio_set_value(LED_B, 0);
	mdelay(1);
	return 0;
}

ssize_t led_write(struct file *pfile, const char *buffer, size_t length, loff_t *offset){
	
	int i;
	
	if(copy_from_user(msg,buffer,length)){
		printk("Write Error\n");
		return -1;
	}
	

	/* if moodlight strong -> weak */
	if(length == 3){ //write "off"
		for(i=0; i<50; i++){
		gpio_set_value(LED, 1);
		mdelay(19.9);
		gpio_set_value(LED, 0);
		mdelay(0.1);
		}
		for(i=0; i<50; i++){
		gpio_set_value(LED, 1);
		mdelay(16);
		gpio_set_value(LED, 0);
		mdelay(4);
		}
		for(i=0; i<50; i++){
		gpio_set_value(LED, 1);
		mdelay(12);
		gpio_set_value(LED, 0);
		mdelay(8);
		}
		for(i=0; i<50; i++){
		gpio_set_value(LED, 1);
		mdelay(8);
		gpio_set_value(LED, 0);
		mdelay(12);
		}
		for(i=0; i<50; i++){
		gpio_set_value(LED, 1);
		mdelay(4);
		gpio_set_value(LED, 0);
		mdelay(16);
		}
	}
	/* if moodlight weak -> strong */
	else if(length == 2){ // write "on"
		for(i=0; i<50; i++){
		gpio_set_value(LED_B, 1);
		mdelay(4);
		gpio_set_value(LED_B, 0);
		mdelay(16);
		}
		for(i=0; i<50; i++){
		gpio_set_value(LED_B, 1);
		mdelay(8);
		gpio_set_value(LED_B, 0);
		mdelay(12);
		}
		for(i=0; i<50; i++){
		gpio_set_value(LED_B, 1);
		mdelay(12);
		gpio_set_value(LED_B, 0);
		mdelay(8);
		}
		for(i=0; i<50; i++){
		gpio_set_value(LED_B, 1);
		mdelay(16);
		gpio_set_value(LED_B, 0);
		mdelay(4);
		}
		for(i=0; i<50; i++){
		gpio_set_value(LED_B, 1);
		mdelay(20);
		}
	}
	/* for moodlight init */
	else if(length == 4){ // write "init"
		for(i=0; i<50; i++){
		gpio_set_value(LED, 1);	
		mdelay(20);
		}
	}
	return length;
}

struct file_operations fop = {
	.owner = THIS_MODULE,
	.open = led_open,
	.release = led_close,
	.write = led_write,
};

int __init led_init(void){

	printk(KERN_ALERT "INIT led\n");
	msg = (char *)kmalloc(32, GFP_KERNEL);
	register_chrdev(DEV_NUM, DEV_NAME, &fop);
	
	return 0;
}

void __exit led_exit(void){
	printk(KERN_ALERT "EXIT led_dev\n");
	unregister_chrdev(DEV_NUM, DEV_NAME);
	kfree(msg);
}

module_init(led_init);
module_exit(led_exit);
