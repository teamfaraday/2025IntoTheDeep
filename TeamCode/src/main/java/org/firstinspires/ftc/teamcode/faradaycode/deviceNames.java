package org.firstinspires.ftc.teamcode.faradaycode;

 /*
    Control Hub
        Motors
            0: arm
            1: rightFront
            2:  leftRear
            3: leftFront
        Servos
            0: intake
            1: rotate
    Expansion Hub
        Motors
            0: slideRight
            1: slideLeft
            2: rightRear
            3:
        Servos
            0:as1
            1:as2
            2:
            3:
            4:           */

public interface deviceNames {

    //servos
    String rotate = "rotate";

    //motors
    String slideRight = "slideRight"; //Expansion Hub
    String slideLeft = "slideLeft"; //Expansion Hub

    //crservos
    String intake = "intake";

    //dc motor
    String arm = "arm";

    //slide rotation
    String armServo1 = "as1";
    String armServo2 = "as2";


    // drivetrain
    String leftFrontName = "leftFront";
    String leftRearName = "leftRear";
    String rightRearName = "rightRear";
    String rightFrontName = "rightFront";

    //encoders
    String leftEncoderName = "slide2";
    String rightEncoderName = "hang";
    String frontEncoderName = "intakeA";

    //tFod
    String webCamName = "Webcam 1";
    String blueLabel = "b";
    String redLabel = "r";
    String blueAsset = "bb.tflite";
    String redAsset = "rr.tflite";

}