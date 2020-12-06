import mraa
import sys
import time
from evdev import InputDevice, categorize, ecodes


try:  
    #Usually the PS4 controller is on event2
    ps4Controller = InputDevice('/dev/input/event2')
    
except:
    print ("Fail PS4 Controller")
    sys.exit()



##first we initialize the pins, then we set the mode (PWM or GPIO, according to the instructions PDF) and lastly we enable the PWM pins
##MOTOR A = forward and backwards
motorA_Pwm = 0
pin_motorA_Pwm = mraa.Pwm(motorA_Pwm)
pin_motorA_Pwm.enable(True)

motorA_Pin1 = 45
pin_motorA_Pin1 = mraa.Gpio(motorA_Pin1)
pin_motorA_Pin1.dir(mraa.DIR_OUT)

motorA_Pin2 = 46
pin_motorA_Pin2 = mraa.Gpio(motorA_Pin2)
pin_motorA_Pin2.dir(mraa.DIR_OUT)


#MOTOR B = direction
motorB_Pwm = 14
pin_motorB_Pwm = mraa.Pwm(motorB_Pwm)
pin_motorB_Pwm.enable(True)

motorB_Pin1 = 47
pin_motorB_Pin1 = mraa.Gpio(motorB_Pin1)
pin_motorB_Pin1.dir(mraa.DIR_OUT)

motorB_Pin2 = 48
pin_motorB_Pin2 = mraa.Gpio(motorB_Pin2)
pin_motorB_Pin2.dir(mraa.DIR_OUT)


standby = 15
pin_standby = mraa.Gpio(standby)
pin_standby.dir(mraa.DIR_OUT) 




##initialization of values according to the table of the instructions
pin_motorA_Pin1.write(0)
pin_motorA_Pin2.write(0)

pin_motorB_Pin1.write(0)
pin_motorB_Pin2.write(0)

pin_standby.write(1)




#threshold for the joysticks, if you put it in the table it may give noise values
threshold= 0.15

#actually is forward AND backward
def forward(speed):
    if speed < -threshold:
        pin_motorA_Pin1.write(0)
        pin_motorA_Pin2.write(1)
        pin_motorA_Pwm.write(speed * -1)
    elif speed > threshold:
        pin_motorA_Pin1.write(1)
        pin_motorA_Pin2.write(0)
        pin_motorA_Pwm.write(speed)
    else:
        pin_motorA_Pin1.write(0)
        pin_motorA_Pin2.write(0)
        pin_motorA_Pwm.write(0)

def direction(value):
    if value < -threshold:
        pin_motorB_Pin1.write(1)
        pin_motorB_Pin2.write(0)
        pin_motorB_Pwm.write(value * -1)
    elif value > threshold:
        pin_motorB_Pin1.write(0)
        pin_motorB_Pin2.write(1)
        pin_motorB_Pwm.write(value)
    else:        
	pin_motorB_Pin1.write(0)
        pin_motorB_Pin2.write(0)
        pin_motorB_Pwm.write(0)


def incremental():       
    pin_motorA_Pin1.write(1)
    pin_motorA_Pin2.write(0)
    
    count = 0
    speed = threshold + 0.00005
    while count < 10000:	
        pin_motorA_Pwm.write(speed)       
	speed = speed + 0.00005
	
	if speed > 1:
	    speed = 1
	print('Speed: ' + str(speed))
        count = count +1

    short_brake()


def stop_back():       
    pin_motorA_Pin1.write(1)
    pin_motorA_Pin2.write(0)
    
    count = 0
    while count < 7500:
        pin_motorA_Pwm.write(0.8)
        count = count + 1
    
    pin_motorA_Pin1.write(0)
    pin_motorA_Pin2.write(0)
    pin_motorA_Pwm.write(1)

def stop():       
    pin_motorA_Pin1.write(0)
    pin_motorA_Pin2.write(1)
    
    count = 0
    while count < 7500:
        pin_motorA_Pwm.write(0.8)
        count = count + 1
    
    pin_motorA_Pin1.write(0)
    pin_motorA_Pin2.write(0)
    pin_motorA_Pwm.write(1)
    
def short_brake():   
    pin_motorA_Pin1.write(0)
    pin_motorA_Pin2.write(1)
    pin_motorA_Pwm.write(0)
    time.sleep(0.1)
    pin_standby.write(0)
    time.sleep(0.1)
    pin_standby.write(1)


def main():   
    speed_X = 0.16    
    for event in ps4Controller.read_loop():
	            
        if event.type == ecodes.EV_ABS:            
            if event.code == ecodes.ABS_X:
                direction_value = 1.0 - (event.value / 128.0)
                #print ("LEFT joystick: " + str(event.value) + " // " + str(direction_value))
                direction_value = direction_value * 0.4
                direction(direction_value)
            elif event.code == ecodes.ABS_RZ:    
                forward_value = 1.0 - (event.value / 128.0)
                #print ("RIGHT joystick: " + str(event.value) + " // " + str(forward_value))
                forward_value = forward_value * 0.4
                forward(forward_value)                
        elif event.type == ecodes.EV_KEY:            
            if event.code == 307: # Triangle                
                incremental()               
            elif event.code == 306: # Circle
                stop()
            elif event.code == 305: # X
		stop_back()
            elif event.code == 304: # Square
                short_brake()
	    elif event.code == 313: # Square
                sys.exit()
               
if __name__ == "__main__":
    main()
