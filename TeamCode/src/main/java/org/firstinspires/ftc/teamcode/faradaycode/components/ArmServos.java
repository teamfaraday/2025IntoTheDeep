package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class ArmServos implements deviceNames {

    //values for servo pos
    public double upPos1 = .79;
    public double downPos1 = 0;
    public double upPos2 = 0;
    public double downPos2 = .82;

    //inits servo objects
    public Servo as1;
    public Servo as2;

    //inits object and assigns servo names
    public ArmServos(HardwareMap hardwareMap) {
        as1 = hardwareMap.get(Servo.class, armServo1);
        as2 = hardwareMap.get(Servo.class, armServo2);
    }

    //what gets called
    public void iterate(boolean up, boolean down) {
        if (up) moveUp();
        if (down) moveDown();
    }

    //functions
    //use 'OpModes.nerf' to grab nerf value for servo testing
    public void moveUp() {
        as1.setPosition(upPos1);
        as2.setPosition(upPos2);
    }
    public void moveDown() {
        as1.setPosition(downPos1);
        as2.setPosition(downPos2);
    }
}
