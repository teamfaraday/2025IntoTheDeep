package org.firstinspires.ftc.teamcode.faradaycode;

 /*
    Control Hub
        Motors
            0: slideLeft && odomRight
            1: outtake && encoderOuttake
            2: arm && encoderArm
            3: leftRear && odomLeft
        Servos
            0: intake
            1: rotate
    Expansion Hub
        Motors
            0: slideRight && odomCenter
            1: rightRear
            2: rightFront
            3: leftFront //broken encoder port :(
        Servos
            0:
*/

public interface deviceNames {

    //intake system
    String arm = "arm";
    String rotate = "rotate";
    String intake = "intake";

    //motors
    String slideRight = "slideRight"; //Expansion Hub
    String slideLeft = "slideLeft"; //Expansion Hub

    //outtake
    String outtake = "outtake";

    // drivetrain
    String leftFrontName = "leftFront";
    String leftRearName = "leftRear";
    String rightRearName = "rightRear";
    String rightFrontName = "rightFront";

    //encoders
    String leftEncoderName = "slide2";
    String rightEncoderName = "hang";
    String frontEncoderName = "intakeA";

}