
package org.firstinspires.ftc.teamcode.faradaycode.trash;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class ServoSave implements  deviceNames{

    //speeds for rotation
    public double pos1 = 0;
    public double pos2 = 1;

    //inits servo objects
    public Servo dummyServo;

    //inits object and assigns servo names
    public ServoSave(HardwareMap hardwareMap) {
        dummyServo = hardwareMap.servo.get(dummyServoName);
    }

    //when called, parse through for motion
    public void iterate(boolean pos1, boolean pos2) {
        if (pos1) {
            setPos1();
        } if (pos2){
            setPos2();
        }
    }

    //functions
    public void setPos1() {
        dummyServo.setPosition(pos1);
    }
    public void setPos2() {
        dummyServo.setPosition(pos2);
    }

    //for Auto
    public void setPos(double pos) {
        dummyServo.setPosition(pos);
    }
}
