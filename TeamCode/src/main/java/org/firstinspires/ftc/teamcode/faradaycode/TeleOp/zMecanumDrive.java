package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;

@TeleOp(name = "MecanumDrive", group = "bEXTRAS")
public class zMecanumDrive extends OpModes {
    public void runOpMode(){
        super.turnOnDT();
        waitForStart();
        while (opModeIsActive() && !stopped){
            NerfSlow.iterate(gamepad1.left_trigger + gamepad1.right_trigger + gamepad2.left_trigger + gamepad2.right_trigger, gamepad2.dpad_right, gamepad2.dpad_left);
            stopped = (gamepad1.left_bumper && gamepad1.left_trigger > 0.6 && gamepad1.right_bumper && gamepad1.right_trigger > 0.6) || (gamepad2.left_bumper && gamepad2.left_trigger > 0.6 && gamepad2.right_bumper && gamepad2.right_trigger > 0.6) || gamepad1.start || gamepad2.start;
            driveTrainTeleOp.iterate(gamepad1.left_stick_y + gamepad2.left_stick_y, gamepad1.left_stick_x+gamepad2.left_stick_x, gamepad1.right_stick_x + gamepad2.right_stick_x);
        }
    }
}