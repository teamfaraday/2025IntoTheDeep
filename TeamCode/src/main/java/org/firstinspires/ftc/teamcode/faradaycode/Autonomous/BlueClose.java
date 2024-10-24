package org.firstinspires.ftc.teamcode.faradaycode.Autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

class Shooter {
    private DcMotorEx motor;

    public Shooter(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "arm");
    }

    public Action spinUp() {
        return new Action() {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    motor.setPower(0.8);
                    initialized = true;
                }

                double vel = motor.getVelocity();
                packet.put("shooterVelocity", vel);
                return vel < 10_000.0;
            }
        };
    }
}


@Autonomous(name = "BlueClose", group = "Autonomous")
public class BlueClose extends OpModes {

    public class Sleep implements Action {
        public int milis;
        public Sleep(int milis) {
            this.milis = milis;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            sleep(milis);
            return false;
        }

    }
    public Action pause(int milis) {
        return new Sleep(milis);
    }


    public void runOpMode() {
        super.turnOn(false);
        Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Shooter shooter = new Shooter(hardwareMap);


        Action traj1 =  drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(0, 30))
                .build();

        Action traj2 =  drive.actionBuilder(new Pose2d(0,30,Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 0))
                .build();


        waitForStart();



        Actions.runBlocking(
                new SequentialAction(
                        traj1,
                        shooter.spinUp(),
                        traj2
                )
        );






        if (isStopRequested()) return;

    }
}