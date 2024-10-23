package org.firstinspires.ftc.teamcode.faradaycode.Autonomous;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "BlueClose", group = "Autonomous")
public class BlueClose extends OpModes {
    public void runOpMode() {
        super.turnOn(false);
        Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);



        Action traj1 =  drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(0, 30))
                .build();

        Action traj2 =  drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(0, 0))
                .build();

        waitForStart();


        intakeServo.activate();



        Actions.runBlocking(
                new SequentialAction(
                        traj1
                )
        );
        intakeServo.deactivate();
        Actions.runBlocking(
                new SequentialAction(
                        traj2
                )
        );



        if (isStopRequested()) return;

    }
}