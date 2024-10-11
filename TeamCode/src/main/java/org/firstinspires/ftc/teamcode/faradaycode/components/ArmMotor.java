package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class ArmMotor implements deviceNames {

    //speeds for rotation
    public double power1 = .3;
    public double power2 = -.3;

    //amnt to slow smth down by
    public double slowConst = 0.2;

    public boolean insideGrav = false;
    public boolean outsideGrav = false;

    public double powerInside = -.00045;
    public double powerOutside = .00045;

    //inits servo objects
    public DcMotor armMotor;

    //inits object and assigns servo names
    public ArmMotor(HardwareMap hardwareMap) {
        armMotor = hardwareMap.dcMotor.get(arm);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
        armMotor.setPower(power1 /** OpModes.nerf*/ * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
    }
    public void deactivate() {
        if (insideGrav) {
            armMotor.setPower(powerInside);
        } else if (outsideGrav) {
            armMotor.setPower(powerOutside);
        } else {
            armMotor.setPower(0);
        }
    }
    public void reverse() {
        armMotor.setPower(power2 /** OpModes.nerf */* ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
    }

    //for Auto
    public void activate(double speed) {
        armMotor.setPower(speed);
    }

    public void test(int ticks) {
        int encoderPos;
        encoderPos = armMotor.getCurrentPosition();
        encoderPos -= (ticks);

        armMotor.setTargetPosition(encoderPos);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while ( armMotor.isBusy()) {
            armMotor.setPower(Math.abs(power1));
        }

        armMotor.setPower(0);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setPos(int ticks) {
        int encoderPos;
        encoderPos = armMotor.getCurrentPosition();
        encoderPos -= (ticks);

        armMotor.setTargetPosition(ticks);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while ( armMotor.isBusy()) {
            armMotor.setPower(Math.abs(power1));
        }

        armMotor.setPower(0);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
