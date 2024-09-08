package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.faradaycode.OpModes;
import org.firstinspires.ftc.teamcode.faradaycode.trash.DCMotorSave;
import org.firstinspires.ftc.teamcode.faradaycode.trash.ServoSave;

@Disabled
@TeleOp(name = "tester")
public class tester extends OpModes {
    public DCMotorSave dcMotorSave;
    public ServoSave servoSave;

    public void runOpMode(){
        dcMotorSave = new DCMotorSave(hardwareMap);
        servoSave = new ServoSave(hardwareMap);

        waitForStart();

        //dcMotorSave.encoderRun(100);
        while (opModeIsActive() && !gamepad1.start){
            NerfSlow.iterate(gamepad1.left_bumper, gamepad1.dpad_right, gamepad1.dpad_left);
            dcMotorSave.iterate(gamepad1.a, gamepad1.b);
            //servoSave.iterate(gamepad1.dpad_left, gamepad1.dpad_right);
        }
    }
}