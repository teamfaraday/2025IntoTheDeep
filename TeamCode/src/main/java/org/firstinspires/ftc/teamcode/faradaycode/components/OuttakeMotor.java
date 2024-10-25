package org.firstinspires.ftc.teamcode.faradaycode.components;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
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

    //inits servo objects
    public DcMotor outtakeMotor;

    //inits object and assigns servo names
    public OuttakeMotor(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.dcMotor.get(outtake);
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        encodering = false;
        isUp = false;
    }

    //when called, parse through for motion
    public void iterate(boolean move) {

        if (encodering) {
            if (outtakeMotor.isBusy()) {
                outtakeMotor.setPower(Math.abs(power1));
            } else {
                setPos2();
            }
        }

        if (move && !encodering) {
            if(isUp) {
                setPos(downPos);
                isUp=false;
            } else {
                setPos(upPos);
                isUp=true;
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
    }

    public Action moveUp() {
        return new Action() {
            public boolean run(@NonNull TelemetryPacket packet) {
                //moving from in to inter !!
                double pos = outtakeMotor.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos > upPos) {
                    outtakeMotor.setPower(-power1);
                    return true;
                } else {
                    outtakeMotor.setPower(0);
                    return false;
                }
            }
        };
    }

    public Action moveDown() {
        return new Action() {
            public boolean run(@NonNull TelemetryPacket packet) {
                //moving from in to inter !!
                double pos = outtakeMotor.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < downPos) {
                    outtakeMotor.setPower(power1);
                    return true;
                } else {
                    outtakeMotor.setPower(0);
                    return false;
                }
            }
        };
    }



}
