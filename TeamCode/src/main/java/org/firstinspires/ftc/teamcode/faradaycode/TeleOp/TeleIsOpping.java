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
            telemetry.addData("pshak", dcMotorSave.armDCMotor.getCurrentPosition());

            telemetry.update();

            //sets up slow vals and nerf vals + creates failsafe exit code
            NerfSlow.iterate(gamepad1.left_trigger, gamepad2.dpad_right, gamepad2.dpad_left);
            stopped = (gamepad1.left_bumper && gamepad1.left_trigger > 0.6 && gamepad1.right_bumper && gamepad1.right_trigger > 0.6) || gamepad1.start;

            //iterators
            servoSave.iterate(gamepad1.dpad_left, gamepad1.dpad_right);
            dcMotorSave.iterate(gamepad1.a, gamepad1.b);
            crServoSave.iterate(gamepad1.dpad_up, gamepad1.dpad_down);
            armServos.iterate(gamepad1.x, gamepad1.y);
            driveTrainTeleOp.iterate((gamepad1.left_stick_y), (gamepad1.left_stick_x), (gamepad1.right_stick_x));
            slide.iterate(gamepad1.left_bumper, gamepad1.right_bumper, gamepad1.a);

            if(gamepad1.y) {
                dcMotorSave.setPos(-250);
                servoSave.moveUp();
                dcMotorSave.setPos(-742);
                dcMotorSave.insideGrav = false;
                dcMotorSave.outsideGrav = true;
            }
            if(gamepad1.x) {
                dcMotorSave.setPos(-543);
                servoSave.moveDown();
                dcMotorSave.setPos(-40);
                dcMotorSave.insideGrav = true;
                dcMotorSave.outsideGrav = false;
            }
        }
    }
}

