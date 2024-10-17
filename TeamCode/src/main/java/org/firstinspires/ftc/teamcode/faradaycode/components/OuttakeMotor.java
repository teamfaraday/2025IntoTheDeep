package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class OuttakeMotor implements deviceNames {

    //speeds for rotation
    public double power1 = 1;
    public double gravDown = 0.01;
    public double gravUp = -0.01;

    public int upPos = -561;
    public int downPos = -274;

    public boolean encodering;
    public boolean humanMove;
    public boolean isUp;

    //inits servo objects
    public DcMotor outtakeMotor;

    //inits object and assigns servo names
    public OuttakeMotor(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.dcMotor.get(outtake);
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        encodering = false;
        isUp = false;
    }

    //when called, parse through for motion
    public void iterate(boolean up, boolean down) {

        if (encodering) {
            if (outtakeMotor.isBusy()) {
                outtakeMotor.setPower(Math.abs(power1));
            } else {
                setPos2();
            }
        }

        if (up && !encodering) {
            if(isUp) {
                humanMove = true;
                setPos(downPos);
            } else {
                humanMove = true;
                setPos(upPos);
            }
        } else if (!encodering && !humanMove) {
            //setPos((isUp) ? upPos : downPos);
            if (outtakeMotor.getCurrentPosition() < -534 && outtakeMotor.getCurrentPosition() > -544) {
                outtakeMotor.setPower(gravUp);
            } else if (outtakeMotor.getCurrentPosition() < -548) {
                outtakeMotor.setPower(gravDown);
            }
        }
    }

    public void setPos(int ticks) {
        outtakeMotor.setTargetPosition(ticks);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        encodering = true;
    }

    public void setPos2() {
        encodering=false;
        outtakeMotor.setPower(-1);
        outtakeMotor.setPower(0);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        /*if (humanMove) {
           isUp = !isUp;
           humanMove = false;
        }*/
    }
}
