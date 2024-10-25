package org.firstinspires.ftc.teamcode.faradaycode.components;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
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

    //auto

    public Action spin(double amnt) {
        return new Action() {
            public boolean run(@NonNull TelemetryPacket packet) {
                intakeServo.setPower(amnt * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
                return false;
            }
        };
    }

    public Action spinOut() {
        return new Action() {
            public boolean run(@NonNull TelemetryPacket packet) {
                activate();
                return false;
            }
        };
    }

    public Action spinIn() {
        return new Action() {
            public boolean run(@NonNull TelemetryPacket packet) {
                reverse();
                return false;
            }
        };
    }

    public Action spinStop() {
        return new Action() {
            public boolean run(@NonNull TelemetryPacket packet) {
                deactivate();
                return false;
            }
        };
    }

}

