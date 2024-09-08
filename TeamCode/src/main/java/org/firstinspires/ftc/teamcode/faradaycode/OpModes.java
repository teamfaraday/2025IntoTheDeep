package org.firstinspires.ftc.teamcode.faradaycode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.faradaycode.components.*;

public abstract class OpModes extends LinearOpMode{


    //public Claw claw;
    public Slides slides;
    public DriveTrainTeleOp driveTrainTeleOp;
    public Claw claw;

    public NerfSlow NerfSlow = new NerfSlow();
    //public ElapsedTime timeSpent = new ElapsedTime();

    public static double nerf = 1;
    public static boolean isSlow = false;
    public static double slowAmnt = 1.0;

    public void turnOn() {
        driveTrainTeleOp = new DriveTrainTeleOp(hardwareMap);
        claw = new Claw(hardwareMap);
        slides = new Slides(hardwareMap);

    }

}
