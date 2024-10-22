package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;

@TeleOp(name = "TeleIsOpping", group = "Acomp")
public class TeleIsOpping extends OpModes {

    public void runOpMode(){
        //code here is setup like constants
        super.turnOn();

        //code past here will run once you start, so an init
        waitForStart();

        intakeArm.moveServoPerp();

        //all code here will be continuously run during the execution
        while (opModeIsActive() && !stopped){
            //creates telem for nerf
            telemetry.addData("pshak", intakeArm.motor.getCurrentPosition());
            telemetry.addData("pshik", outtakeMotor.outtakeMotor.getCurrentPosition());
            telemetry.update();

            //sets up slow vals and nerf vals + creates failsafe exit code
            NerfSlow.iterate(gamepad1.left_trigger + gamepad1.right_trigger + gamepad2.left_trigger + gamepad2.right_trigger, gamepad2.dpad_right, gamepad2.dpad_left);
            stopped = (gamepad1.left_bumper && gamepad1.left_trigger > 0.6 && gamepad1.right_bumper && gamepad1.right_trigger > 0.6) || (gamepad2.left_bumper && gamepad2.left_trigger > 0.6 && gamepad2.right_bumper && gamepad2.right_trigger > 0.6) || gamepad1.start || gamepad2.start;

            //gp2
            outtakeMotor.iterate(gamepad1.a, gamepad1.b);
            slide.iterate(gamepad1.left_bumper, gamepad1.right_bumper, gamepad1.a);


            //gp1
            driveTrainTeleOp.iterate(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            intakeArm.iterate(gamepad1.y);
            intakeServo.iterate(gamepad1.dpad_up, gamepad1.dpad_down);

        }
    }
}