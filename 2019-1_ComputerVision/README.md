Computer Vision (2019 Spring)
=============  
* **Assignment 1**
    * Moving object segmentation by background subtraction and temporal analysis (specially for car)
    * It is implemented with exploiting functions in openCV2 package (medianBlur(),  MorphologyEx())
    * Acheived high Recall(97.36%), Precision(93.33%).
    
* **Assignment 2**  
    * I implemented Resnet-32 object detection model for CIFAR-10.
    * I pre-processed input images with data augmentation (horizontal flip, random crop)
    * I performed hyperparameter tuning and the values are below.
      * learning rate: 0.1(epochs <= 81), 0.01(81 <= epochs <= 123), 0.001(123 <= epochs <= 163)
      * momentum: 0.9 | weight_decay: 0.0001 | epochs: 500
      * train batch_size: 128 | test_batch_size: 100
    * I also leveraged He-initialization for effective setting initial network weights.
    * object detection Accuracy: 92.47%, Model memory size: 1.81MB.
