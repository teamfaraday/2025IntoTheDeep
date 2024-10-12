package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class ServoSave implements deviceNames {

    //values for servo po
    //y = up, x= down
    public double upPos = 0.623;
    public double downPos = 0.295;

    //inits servo objects
    public Servo dummyServo;

    //inits object and assigns servo names
    public ServoSave(HardwareMap hardwareMap) {
        dummyServo = hardwareMap.get(Servo.class, rotate);
    }

    //what gets called
    public void iterate(boolean up, boolean down) {
        if (up) moveUp();
        if (down) moveDown();
    }

    //functions
    //use 'OpModes.nerf' to grab nerf value for servo testing
    public void moveUp() {
        dummyServo.setPosition(upPos);
    }
    public void moveDown() {
        dummyServo.setPosition(downPos);
    }
}
