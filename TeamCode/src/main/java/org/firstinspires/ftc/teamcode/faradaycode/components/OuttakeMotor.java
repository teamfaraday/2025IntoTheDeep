package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class OuttakeMotor implements deviceNames {

    //speeds for rotation
    public double power1 = .3;
    public double power2 = -.3;

    //amnt to slow smth down by
    public double slowConst = 0.2;

    public double powerInside = -.00045;
    public double powerOutside = .00045;

    //inits servo objects
    public DcMotor outtakeMotor;

    //inits object and assigns servo names
    public OuttakeMotor(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.dcMotor.get(outtake);
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //when called, parse through for motion
    public void iterate(boolean up, boolean down) {
        if (up) {
            activate();
        } else if (down) {
            reverse();
        } else{
            deactivate();
        }
    }

    //functions
    public void activate() {
        setPos(-250);
    }
    public void deactivate() {
        outtakeMotor.setPower(0);
    }
    public void reverse() {
        setPos(-4);
    }

    //for Auto
    public void activate(double speed) {
        outtakeMotor.setPower(speed);
    }

    public void setPos(int ticks) {
        outtakeMotor.setTargetPosition(ticks);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while ( outtakeMotor.isBusy()) {
            outtakeMotor.setPower(Math.abs(power1));
        }

        outtakeMotor.setPower(0);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
