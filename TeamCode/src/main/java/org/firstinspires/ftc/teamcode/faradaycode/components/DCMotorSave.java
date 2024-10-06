package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class DCMotorSave implements deviceNames {

    //speeds for rotation
    public double power1 = .3;
    public double power2 = -.3;

    //amnt to slow smth down by
    public double slowConst = 0.2;

    //inits servo objects
    public DcMotor armDCMotor;

    //inits object and assigns servo names
    public DCMotorSave(HardwareMap hardwareMap) {
        armDCMotor = hardwareMap.dcMotor.get(arm);
        armDCMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
        armDCMotor.setPower(power1 * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
    }
    public void deactivate() {
        armDCMotor.setPower(0);
    }
    public void reverse() {
        armDCMotor.setPower(power2 * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
    }

    //for Auto
    public void activate(double speed) {
        armDCMotor.setPower(speed);
    }

    public void intake(int ticks) {
        int encoderPos;
        encoderPos = armDCMotor.getCurrentPosition();
        encoderPos -= (ticks);

        armDCMotor.setTargetPosition(encoderPos);
        armDCMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while ( armDCMotor.isBusy()) {
            armDCMotor.setPower(Math.abs(power1));
        }

        armDCMotor.setPower(0);
        armDCMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void outtake(int ticks) {
        int encoderPos;
        encoderPos = armDCMotor.getCurrentPosition();
        encoderPos -= (ticks);

        armDCMotor.setTargetPosition(encoderPos);
        armDCMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while ( armDCMotor.isBusy()) {
            armDCMotor.setPower(Math.abs(power1));
        }

        armDCMotor.setPower(0);
        armDCMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
