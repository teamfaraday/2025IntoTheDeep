package org.firstinspires.ftc.teamcode.faradaycode.components;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.faradaycode.deviceNames;

public class IntakeArm implements deviceNames {

//consts
    //speeds for motor rotation
    public final double power = .3;
    public final double gravPowerInside = -.00045;
    public final double gravPowerOutside = .00045;

    public final int inPos = -7;
    public final int outPos = -694;

    public final int rotateServoOutPos = -300;
    public final int rotateServoInPos = -400;

    //amnt to slow smth down by
    public final double slowConst = 0.2;

    //servo positions
    public final double parPos = 0.623;
    public final double perpPos = 0.295;


    //bools
    public boolean movingIn;
    public boolean movingOut;

    public boolean insideGrav;
    public boolean outsideGrav;

    public boolean encodering;

    public boolean isIn;

    //inits servo objects
    public DcMotor motor;
    public Servo servo;

    //inits object and assigns servo names
    public IntakeArm(HardwareMap hardwareMap) {
        motor = hardwareMap.dcMotor.get(arm);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        servo = hardwareMap.servo.get(rotate);

        movingOut = false;
        movingIn = false;
        isIn = true;
    }

    //when called, parse through for motion
    public void iterate(boolean move) {

        if (encodering) {
            if (motor.isBusy()) {
                motor.setPower(Math.abs(power * ((OpModes.isSlow) ? slowConst : 1)));
            } else {
                setMotorPos2();
            }

            if (movingOut && motor.getCurrentPosition() < rotateServoOutPos) {
                moveServoPar();
                movingOut = false;
            } else if (movingIn && motor.getCurrentPosition() > rotateServoInPos) {
                moveServoPerp();
                movingIn = false;
            }
        } else {
            deactivate();

            if (move) {
                if (isIn) {
                    movingOut = true;
                    setMotorPos(outPos);
                    insideGrav = false;
                    outsideGrav = true;
                    isIn = false;
                } else {
                    movingIn = true;
                    setMotorPos(inPos);
                    insideGrav = true;
                    outsideGrav = false;
                    isIn=true;
                }
            }

        }

    }

    //functions

    public void deactivate() {
        if (insideGrav) {
            motor.setPower(gravPowerInside);
        } else if (outsideGrav) {
            motor.setPower(gravPowerOutside);
        } else {
            motor.setPower(0);
        }
    }

    public void setMotorPos(int ticks) {
        encodering = true;
        motor.setTargetPosition(ticks);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setMotorPos2(){
        encodering = false;
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveServoPar() {
        servo.setPosition(parPos);
    }
    public void moveServoPerp() {
        servo.setPosition(perpPos);
    }

    public class MoveOut implements Action {
        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {

            moveServoPar();
            //setMotorPos(-700);
            return false;
            /*if (!initialized) {
                motor.setPower(0.4);
                initialized = true;
            }
            double pos = motor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos > -694) {
                return true;
            } else {
                if (pos < -300) {
                    moveServoPerp();
                }
                motor.setPower(0);
                return false;
            }*/
        }

    }
    public Action moveOut() {
        return new MoveOut();
    }


}