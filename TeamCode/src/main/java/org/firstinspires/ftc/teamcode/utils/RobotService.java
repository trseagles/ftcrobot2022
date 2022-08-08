package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.hardware.adafruit.AdafruitBNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class RobotService {
    public DcMotor right;
    public DcMotor left;
    public DcMotor backHex;
    public DcMotor frontHex;
    public RevColorSensorV3 topColor;
    public RevColorSensorV3 botColor;
    public Rev2mDistanceSensor distance1;
    public Rev2mDistanceSensor distance2;
    public Servo sPane1;
    public Servo sPane2;
    public Servo pane1;
    public Servo pane2;
    public AdafruitBNO055IMU imu;

    private double power = Configuration.DEFAULT_POWER;
    private double intakePower = Configuration.DEFAULT_INTAKE_POWER;

    public RobotService(HardwareMap hwMap){
        left = hwMap.get(DcMotor.class, "left_drive");
        right = hwMap.get(DcMotor.class, "right_drive");
        backHex = hwMap.get(DcMotor.class, "back_hex");
        frontHex = hwMap.get(DcMotor.class, "front_hex");
        topColor = hwMap.get(RevColorSensorV3.class, "color_1");
        botColor = hwMap.get(RevColorSensorV3.class, "color_1");
        distance1 = hwMap.get(Rev2mDistanceSensor.class, "distance_1");
        distance2 = hwMap.get(Rev2mDistanceSensor.class, "distance_2");
        sPane1 = hwMap.get(Servo.class, "servo_1");
        sPane2 = hwMap.get(Servo.class, "servo_2");
        pane1 = hwMap.get(Servo.class, "servo_3");
        pane2 = hwMap.get(Servo.class, "servo_4");
        imu = hwMap.get(AdafruitBNO055IMU.class, "imu");

        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        params.mode = BNO055IMU.SensorMode.IMU;
        params.accelPowerMode = BNO055IMU.AccelPowerMode.NORMAL;
        params.gyroPowerMode = BNO055IMU.GyroPowerMode.NORMAL;
        params.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        params.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(params);

        backHex.setDirection(DcMotorSimple.Direction.FORWARD);
        frontHex.setDirection(DcMotorSimple.Direction.FORWARD);
        left.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public double getPower(){
        return power;
    }
    public void setPower(double power){
        this.power = power < 0 ? -power : power;
    }
    public void setIntakePower(double intakePower){
        this.intakePower = intakePower;
    }

    public void setMotorPower(double rp, double lp){
        right.setPower(rp);
        left.setPower(lp);
    }
    public void forward(){
        setMotorPower(power, power);
    }
    public void backward()
    {
        setMotorPower(-power, -power);
    }
    public void right() {
        setMotorPower(0, power * Configuration.ROTATION_SPEED_PER);
    }
    public void left() {
        setMotorPower(power * Configuration.ROTATION_SPEED_PER, 0);
    }
    public void stop(){
        setMotorPower(0, 0);
    }
    public void turn(boolean rotation) {
        if (rotation)
            setMotorPower(power, -power);
        else
            setMotorPower(-power, power);
    }

    public void intake(boolean open){
        double power = open ? intakePower : 0;
        backHex.setPower(power * 3 / 4);
        frontHex.setPower(power);
    }
    public void setIntake(double f, double b){
        backHex.setPower(b);
        frontHex.setPower(f);
    }
    public void pane(boolean direction, boolean open){
        if (direction){
            if (open)
                pane1.setPosition(90);
            else
                pane1.setPosition(0);
        }
        else{
            if (open)
                pane2.setPosition(90);
            else
                pane2.setPosition(0);
        }
    }
    public void separate(boolean direction) {
        try {
            if (direction){
                sPane1.setPosition(0);
                Thread.sleep(300);
                sPane1.setPosition(50);
            }
            else{
                sPane2.setPosition(50);
                Thread.sleep(300);
                sPane2.setPosition(0);
            }
        }
        catch (InterruptedException ignored){

        }
    }

    public void convertGamepad(double y, double x){
        if (x == 0){
            setMotorPower(y, y);
            return;
        }
        x += 0.5;
        double rp = y * x / 10;
        setMotorPower(rp, y - rp);
    }

    public double getOriginalDistance(int index){
        if (index == 1)
            return distance1.getDistance(DistanceUnit.CM);
        else
            return distance2.getDistance(DistanceUnit.CM);
    }

    public double getYaw(){
        return imu.getAngularOrientation().firstAngle;
    }
    public double getRoll(){
        return imu.getAngularOrientation().thirdAngle;
    }

    public double getPitch(){
        return imu.getAngularOrientation().secondAngle;
    }

    public void turn(int angle){
        double pow = power;
        power = 0.5;
        if (-getYaw() > angle)
            while (-getYaw() > angle)
                turn(false);
        if (-getYaw() < angle)
            while (-getYaw() < angle)
                turn(true);
        stop();
        power = pow;
    }
}

