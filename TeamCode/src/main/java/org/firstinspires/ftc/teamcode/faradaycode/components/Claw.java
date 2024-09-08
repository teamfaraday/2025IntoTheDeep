package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.faradaycode.*;

public class Claw implements deviceNames {

    //values for servo pos, modify as needed after testing
    public double clawLOpen = 0.974;
    public double clawLClose = 0.8;
    public double clawROpen = 0.6;
    public double clawRClose = 0.798;


    //inits servo objects
    public Servo clawL;
    public Servo clawR;

    //inits object and assigns servo names
    public Claw(HardwareMap hardwareMap) {
        clawL = hardwareMap.get(Servo.class, clawLName);
        clawR = hardwareMap.get(Servo.class, clawRName);
    }

    //what gets called
    public void iterate(boolean open, boolean close) {
        if (open) openClaw();
        if (close) closeClaw();
    }

    //functions
    //use 'OpModes.nerf' to grab nerf value for servo testing
    public void openClaw() {
        clawL.setPosition(clawLOpen);
        clawR.setPosition(clawROpen);
    }
    public void closeClaw() {
        clawL.setPosition(clawLClose);
        clawR.setPosition(clawRClose);
    }
    public void setPos(double pos) {
        clawL.setPosition(pos);
        clawR.setPosition(pos);
    }
}
