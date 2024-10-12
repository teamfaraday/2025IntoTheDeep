package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class IntakeServo implements deviceNames {

    //speeds for rotation
    public double power1 = 1;
    public double power2 = -1;

    //amnt to slow smth down by
    public double slowConst = 0.5;

    //inits servo objects
    public CRServo intakeServo;

    //inits object and assigns servo names
    public IntakeServo(HardwareMap hardwareMap) {
        intakeServo = hardwareMap.crservo.get(intake);
    }

    //when called, parse through for motion
    public void iterate(boolean forward, boolean reverse) {
        if (forward) {
            activate();
        } else if (reverse) {
            reverse();
        } else{
            deactivate();
        }
    }

    //functions
    public void activate() {
        intakeServo.setPower(power1 * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
    }
    public void deactivate() {
        intakeServo.setPower(0);
    }
    public void reverse() {
        intakeServo.setPower(power2 * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
    }

    //for Auto
    public void activate(double speed) {
        intakeServo.setPower(speed);
    }
}
