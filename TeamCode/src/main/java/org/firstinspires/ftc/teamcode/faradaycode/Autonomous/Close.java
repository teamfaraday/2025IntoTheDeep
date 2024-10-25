package org.firstinspires.ftc.teamcode.faradaycode.Autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;




@Autonomous(name = "Close", group = "Autonomous")
public class Close extends OpModes {

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
            //
        }


        waitForStart();
        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        traj1,
                        intakeArm.moveOut(),
                        intakeServo.spinIn(),
                        pause(2000),
                        intakeServo.spinStop(),
                        intakeArm.moveIn(),
                        intakeServo.spinOut(),
                        traj2,
                        intakeServo.spinStop(),
                        intakeArm.moveInterFromIn(),
                        slide.moveSlides(-1),
                        pause(1500),
                        slide.moveSlides(-0.06),
                        outtakeMotor.moveUp(),
                        pause(500),
                        outtakeMotor.moveDown(),
                        pause(100)
                )
        );


    }
}