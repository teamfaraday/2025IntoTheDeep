package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.faradaycode.*;

public class Slides implements  deviceNames{

    //speeds for rotation
    public double power1 = 1;
    public double power2 = -0.8;
    public double antiGravPower = 0.11;

    public double up;
    public double down;

    //amnt to slow smth down by
    public double slowConst = 0.5;

    //inits motor objects
    //public DcMotor dummyDCMotor;
    public DcMotor leftSlide;
    public DcMotor rightSlide;

    //inits object and assigns servo names
    public Slides(HardwareMap hardwareMap) {
        leftSlide = hardwareMap.dcMotor.get(slideLName);
        rightSlide = hardwareMap.dcMotor.get(slideRName);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    //when called, parse through for motion
    public void iterate(double down, double up) {
        if (up>= 0.1) {
            this.up = up;
            activate();
        } else if (down >= 0.1) {
            this.down=down;
            reverse();
        } else{
            deactivate();
        }
    }

    //functions
    public void activate() {
        leftSlide.setPower(power1 * up * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
        rightSlide.setPower(power1 * up * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));

    }
    public void deactivate() {
        leftSlide.setPower(antiGravPower);
        rightSlide.setPower(antiGravPower);
    }
    public void reverse() {
        leftSlide.setPower(power2 * down * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
        rightSlide.setPower(power2 * down * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
    }
    public void setPower(double power) {
        leftSlide.setPower(power * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
        rightSlide.setPower(power * OpModes.nerf * ((OpModes.isSlow) ? slowConst: OpModes.slowAmnt));
    }
}
