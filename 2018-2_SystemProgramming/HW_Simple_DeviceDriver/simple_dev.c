#define _CRT_SECURE_NO_WARNINGS
#include <linux/init.h>
#include <linux/module.h>
#include <linux/fs.h>
#include <linux/cdev.h>
#include <linux/slab.h>
#include <linux/uaccess.h>
#include <linux/kernel.h>

struct cdev my_cdev;
dev_t devno;
static char *msg = NULL;
int major;

module_param(major, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP);
MODULE_PARM_DESC(major, "major");

int simple_open(struct inode *pinode, struct file *pfile)
{
	printk(KERN_ALERT "Open simple file\n");
	return 0;
}

ssize_t simple_read(struct file *pfile, char __user *buffer, size_t length, loff_t *offset)
{
	printk("Read simple char drv\n");

	if(strcmp(msg, "test") == 0) {
		if(copy_to_user(buffer, "GET TEST", 10) < 0){
			printk("Read error\n");
			return -1;
		}
	}
	else{
		if(copy_to_user(buffer, "NOT TEST", 10) < 0){
			printk("Read error\n");
			return -1;
		}
	}
	return 0;
}

ssize_t simple_write(struct file *pfile, const char __user *buffer, size_t length, loff_t *offset)
{
	printk("Write simple char drv\n");

	if(copy_from_user(msg, buffer, 30) < 0){
		printk("Write error\n");
		return -1;
	}

	return length;
}

int simple_close(struct inode *pinode, struct file *pfile)
{
	printk(KERN_ALERT "close simple file\n");
	return 0;
}

struct file_operations fop = {
	.owner = THIS_MODULE,
	.read = simple_read,
	.write = simple_write,
	.open = simple_open,
	.release = simple_close,
};

int __init simple_device_init(void)
{
	int err;

	if(major){
		devno = MKDEV(major, 0);
		register_chrdev_region(devno, 1, "simple_dev");
	}
	else{
		alloc_chrdev_region(&devno, 0, 1, "simple_dev");
		major = MAJOR(devno);
	}

	cdev_init(&my_cdev, &fop);
	my_cdev.owner = THIS_MODULE;
	err = cdev_add(&my_cdev, devno, 1);

	if(err < 0) {
		printk("Device add error\n");
		return -1;
	}

	msg = (char *)kmalloc(32, GFP_KERNEL);

	if(msg != NULL)
		printk("malloc allocator address: 0x%p\n", msg);

	return 0;
}

void __exit simple_device_exit(void)
{
	printk("Goodbye simple_dev\n");

	if(msg) {
		kfree(msg);
	}

	unregister_chrdev_region(devno, 1);
	cdev_del(&my_cdev);
}

module_init(simple_device_init);
module_exit(simple_device_exit);

