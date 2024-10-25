package org.firstinspires.ftc.teamcode.faradaycode.components;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class Slide implements deviceNames{

    public double slidePower = 1;
    public double antiGravPower = -0.06;
    public double slowConst = 0.45;
    public double slowSlidePower = -0.1;
    public double posSlide;

    public DcMotor slideL;
    public DcMotor slideR;




    public Slide(HardwareMap hardwareMap) {
        slideR = hardwareMap.dcMotor.get(slideRight);
        slideL = hardwareMap.dcMotor.get(slideLeft);
        slideR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideR.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void iterate(boolean up, boolean down, boolean intakes) {
        if (down){
            down();
        } else if (up) {
            up();
        } else {
            antiGrav();
        }
        if (intakes) {
            slowDown();
        }
    }

    public void up() {
        if (!OpModes.isSlow) {
            slideL.setPower(slidePower * OpModes.nerf);
            slideR.setPower(slidePower * OpModes.nerf);
        } else {
            slideL.setPower(slidePower * slowConst * OpModes.nerf);
            slideR.setPower(slidePower * slowConst * OpModes.nerf);
        }
    }
    public void down() {
        if (!OpModes.isSlow) {
            slideL.setPower(-slidePower * OpModes.nerf);
            slideR.setPower(-slidePower * OpModes.nerf);
        } else {
            slideL.setPower(-slidePower * slowConst * 0.6 * OpModes.nerf);
            slideR.setPower(-slidePower * slowConst * 0.6 * OpModes.nerf);
        }
    }
    public void slowDown() {
        slideL.setPower(slowSlidePower);
        slideR.setPower(slowSlidePower);
    }
    public void antiGrav() {
        slideL.setPower(antiGravPower);
        slideR.setPower(antiGravPower);
    }
    public void slowUp() {
        slideL.setPower(0.2);
        slideR.setPower(0.2);
    }
    public void deactivate() {
        slideL.setPower(0);
        slideR.setPower(0);
    }

    public Action moveSlides(double amnt) {
        return new Action() {
            public boolean run(@NonNull TelemetryPacket packet) {
                slideL.setPower(amnt);
                slideR.setPower(amnt);
                return false;
            }
        };
    }

}
