package org.firstinspires.ftc.teamcode.faradaycode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrainTeleOp;
import org.firstinspires.ftc.teamcode.faradaycode.components.IntakeArm;
import org.firstinspires.ftc.teamcode.faradaycode.components.IntakeServo;
import org.firstinspires.ftc.teamcode.faradaycode.components.NerfSlow;
import org.firstinspires.ftc.teamcode.faradaycode.components.OuttakeMotor;
import org.firstinspires.ftc.teamcode.faradaycode.components.Slide;

public abstract class OpModes extends LinearOpMode{

    public IntakeServo intakeServo;
    public IntakeArm intakeArm;
    public DriveTrainTeleOp driveTrainTeleOp;
    public Slide slide;
    public OuttakeMotor outtakeMotor;


    public org.firstinspires.ftc.teamcode.faradaycode.components.NerfSlow NerfSlow = new NerfSlow();
    public ElapsedTime timer = new ElapsedTime();

    public boolean stopped = false;
    public static double nerf = 1;
    public static boolean isSlow = false;
    public static double slowAmnt = 1.0;

    public void turnOn() {
        intakeServo = new IntakeServo(hardwareMap);
        intakeArm = new IntakeArm(hardwareMap);
        slide = new Slide(hardwareMap);
        outtakeMotor = new OuttakeMotor(hardwareMap);
        turnOnDT();
    }

    public void turnOnDT() {
        driveTrainTeleOp = new DriveTrainTeleOp(hardwareMap);
    }

}
