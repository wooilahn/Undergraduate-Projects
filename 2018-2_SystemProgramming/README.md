System Programming (2018 Fall)
=============  
* **Homework**
    * implemented simple device-driver in Linux with vim.
    * Read and Write short characters with the device-driver.
  
* **Team Project (Smart Bedroom)**  
    * We implemented smart bedroom with two Raspberry-Pi.
    * One Raspberry-Pi is for controling the bed and the other is for controling the curtain on window.
    * Two Raspberry-Pis are connected via socket communication in Linux.
    * The list of sensors used is as follows.
      * 1. DHT11 temp & humidity: It is used for adjusting sleep temperature optimally.
      * 2. Gyroscope Sensor: It is used for checking whether the person lying in bed is really asleep.
      * 3. Touch Sensors: It is used for checking whether the person lying in bed is really asleep.
      * 4. LED(Light Emitting Diode): It is used for For mood-light.
      * 5. Motor Sensor in curatin Raspberry-Pi: It is used for letting down curtains.
      * 6. Motor Sensor in Bed Raspberry-Pi: It is used for raising the bed at the alarm time to wake up.
      * 7. Buzzer Sensor: It is used to sound an alarm at the time to wake up.
