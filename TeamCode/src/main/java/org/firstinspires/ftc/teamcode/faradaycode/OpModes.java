package org.firstinspires.ftc.teamcode.faradaycode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.faradaycode.components.ArmServos;
import org.firstinspires.ftc.teamcode.faradaycode.components.IntakeServo;
import org.firstinspires.ftc.teamcode.faradaycode.components.ArmMotor;
import org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrainTeleOp;
import org.firstinspires.ftc.teamcode.faradaycode.components.NerfSlow;
import org.firstinspires.ftc.teamcode.faradaycode.components.ServoSave;
import org.firstinspires.ftc.teamcode.faradaycode.components.Slide;

public abstract class OpModes extends LinearOpMode{

    public ServoSave servoSave;
    public IntakeServo intakeServo;
    public ArmMotor armMotor;
    public DriveTrainTeleOp driveTrainTeleOp;
    public Slide slide;
    public ArmServos armServos;

    public org.firstinspires.ftc.teamcode.faradaycode.components.NerfSlow NerfSlow = new NerfSlow();
    public ElapsedTime timer = new ElapsedTime();

    public boolean stopped = false;
    public static double nerf = 1;
    public static boolean isSlow = false;
    public static double slowAmnt = 1.0;

    public void turnOn() {
        driveTrainTeleOp = new DriveTrainTeleOp(hardwareMap);
        servoSave = new ServoSave(hardwareMap);
        intakeServo = new IntakeServo(hardwareMap);
        armMotor = new ArmMotor(hardwareMap);
        slide = new Slide(hardwareMap);
        armServos = new ArmServos(hardwareMap);



    }

}
