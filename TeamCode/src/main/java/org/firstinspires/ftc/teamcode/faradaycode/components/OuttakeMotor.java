package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class OuttakeMotor implements deviceNames {

    //speeds for rotation
    public double power1 = 0.3;
    public double gravDown = 0.01;
    public double gravUp = -0.01;

    public int upPos = -280;
    public int upPosCorrected = -279;
    public int downPos = -60;

    public boolean encodering;
    public boolean isUp;
    public boolean isUpCorrected;

    //inits servo objects
    public DcMotor outtakeMotor;

    //inits object and assigns servo names
    public OuttakeMotor(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.dcMotor.get(outtake);
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encodering = false;
        isUp = false;
        isUpCorrected = false;
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
                setPos(downPos);
                isUp=false;
            } else {
                setPos(upPos);
                isUp=true;
                isUpCorrected = false;
            }
        } else if (!encodering) {
            outtakeMotor.setPower(0);

        }
    }

    public void setPos(int ticks) {
        outtakeMotor.setTargetPosition(ticks);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        encodering = true;
    }

    public void setPos2() {
        outtakeMotor.setPower(0);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        encodering=false;
        if (!isUpCorrected && isUp) {
            setPos(upPosCorrected);
            isUpCorrected=true;
        }
    }
}
