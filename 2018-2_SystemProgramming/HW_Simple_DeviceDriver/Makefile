KERNELDIR = /lib/modules/$(shell uname -r)/build

obj-m := simple_dev.o

PWD := $(shell pwd)

default :
		make -C $(KERNELDIR) M=$(PWD) modules

clean :
		make -C $(KERNELDIR) M=$(PWD) clean
