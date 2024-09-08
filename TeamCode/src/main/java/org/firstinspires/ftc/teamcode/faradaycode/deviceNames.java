package org.firstinspires.ftc.teamcode.faradaycode;

/*
    Control Hub
        Motors
            0: rightRear
            1: leftRear
            2: rightFront
            3: leftFront
        Servos
            0:
            1:
    Expansion Hub
        Motors
            0: rightSlide
            1: leftSlide
            2:
            3:
        Servos
            0: clawL
            1: clawR
            2:
            3:
            4:
  */


public interface deviceNames {

    //slides
    String slideLName = "leftSlide";
    String slideRName = "rightSlide";


    //claws
    String clawLName = "leftClaw";
    String clawRName = "rightClaw";

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

    //trash
    String dummyDCMotorName = "skibidiMotor"; //Control hub 1
    String dummyServoName = "skibidiServo";

}
