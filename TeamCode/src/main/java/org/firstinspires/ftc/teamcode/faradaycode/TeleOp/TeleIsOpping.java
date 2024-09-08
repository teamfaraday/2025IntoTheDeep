package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;

@TeleOp(name = "TeleIsOpping")
public class TeleIsOpping extends OpModes {
    public void runOpMode(){
        super.turnOn();


        waitForStart();

        claw.openClaw();

        while (opModeIsActive() && !(gamepad1.start || gamepad2.start)){
            telemetry.addData("nerf", OpModes.nerf);
            telemetry.addData("slowAmnt", OpModes.slowAmnt);
            telemetry.update();

            NerfSlow.iterate(gamepad1.left_bumper, gamepad1.dpad_right, gamepad1.dpad_left);

            claw.iterate(gamepad1.x, gamepad1.y);
            slides.iterate(gamepad1.left_trigger, gamepad1.right_trigger);
            driveTrainTeleOp.iterate(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        }
    }
}