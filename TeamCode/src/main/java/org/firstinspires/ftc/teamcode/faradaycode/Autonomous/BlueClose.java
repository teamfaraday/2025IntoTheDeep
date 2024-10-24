package org.firstinspires.ftc.teamcode.faradaycode.Autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;




@Autonomous(name = "BlueClose", group = "Autonomous")
public class BlueClose extends OpModes {

    public Action pause(int milis) {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                sleep(milis);
                return false;
            }
        };
    }


    public void runOpMode() {
        super.turnOn(false);
        Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);


        Action traj1 =  drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(0, 30))
                .build();

        Action traj2 =  drive.actionBuilder(new Pose2d(0,30,Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 0))
                .build();

        Action traj3 =  drive.actionBuilder(new Pose2d(0,0,Math.toRadians(90)))
                .strafeTo(new Vector2d(0, -20))
                .build();

        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addData("Position during Init", 2);
            telemetry.update();
        }


        waitForStart();
        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        traj1,
                        intakeArm.moveOut(),
                        intakeServo.spinIn(),
                        pause(1000),
                        intakeServo.spinStop(),
                        traj2,
                        intakeArm.moveIn(),
                        intakeServo.spinOut(),
                        pause(1000),
                        intakeServo.spinStop(),
                        traj3
                )
        );


    }
}