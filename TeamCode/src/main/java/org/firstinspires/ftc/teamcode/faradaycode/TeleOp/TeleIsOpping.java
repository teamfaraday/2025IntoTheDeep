package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;

@TeleOp(name = "TeleIsOpping")
public class TeleIsOpping extends OpModes {

    public void runOpMode(){
        //code here is setup like constants
        super.turnOn();

        //code past here will run once you start, so an init
        waitForStart();

        servoSave.moveUp();

        //all code here will be continuously run during the execution
        while (opModeIsActive() && !stopped){
            //creates telem for nerf
            //telemetry.addData("nerf", nerf);
            telemetry.addData("pshak", armMotor.armMotor.getCurrentPosition());

            telemetry.update();

            //sets up slow vals and nerf vals + creates failsafe exit code
            NerfSlow.iterate(gamepad1.left_trigger, gamepad2.dpad_right, gamepad2.dpad_left);
            stopped = (gamepad1.left_bumper && gamepad1.left_trigger > 0.6 && gamepad1.right_bumper && gamepad1.right_trigger > 0.6) || gamepad1.start;

            //iterators
            servoSave.iterate(gamepad1.dpad_left, gamepad1.dpad_right);
            armMotor.iterate(gamepad1.a, gamepad1.b);
            crServoSave.iterate(gamepad1.dpad_up, gamepad1.dpad_down);
            armServos.iterate(gamepad1.x, gamepad1.y);
            driveTrainTeleOp.iterate((gamepad1.left_stick_y), (gamepad1.left_stick_x), (gamepad1.right_stick_x));
            slide.iterate(gamepad1.left_bumper, gamepad1.right_bumper, gamepad1.a);

            if(gamepad1.y) {
                armMotor.setPos(-250);
                servoSave.moveUp();
                armMotor.setPos(-742);
                armMotor.insideGrav = false;
                armMotor.outsideGrav = true;
            }
            if(gamepad1.x) {
                armMotor.setPos(-543);
                servoSave.moveDown();
                armMotor.setPos(-40);
                armMotor.insideGrav = true;
                armMotor.outsideGrav = false;
            }
        }
    }
}

