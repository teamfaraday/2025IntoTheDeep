package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrainTeleOp;

@TeleOp(name = "driveTrainTest")
public class driveTrainTest extends OpModes {
    public DriveTrainTeleOp driveTrainTeleOp;

    public void runOpMode(){
        driveTrainTeleOp = new DriveTrainTeleOp(hardwareMap);

        waitForStart();

        while (opModeIsActive() && !(gamepad1.start || gamepad2.start)){
            NerfSlow.iterate(gamepad1.left_bumper, gamepad1.dpad_right, gamepad1.dpad_left);
            driveTrainTeleOp.iterate(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        }
    }
}