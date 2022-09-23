package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.*;
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
    public Servo pane;
    public BNO055IMU imu;

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
        pane = hwMap.get(Servo.class, "servo_3");
        imu = hwMap.get(BNO055IMU.class, "imu");

        try{
            BNO055IMU.Parameters params = new BNO055IMU.Parameters();
            params.mode = BNO055IMU.SensorMode.IMU;
            /*params.accelPowerMode = BNO055IMU.AccelPowerMode.NORMAL;
            params.gyroPowerMode = BNO055IMU.GyroPowerMode.NORMAL;*/
            params.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            params.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            imu.initialize(params);
        }
        catch (Exception e){
            int a = 0;
        }

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
        setMotorPower(0, power);
    }
    public void left() {
        setMotorPower(power, 0);
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
    public void pane(boolean open){
        if (open)
            pane.setPosition(0.57);
        else
            pane.setPosition(0.35);
    }
    public void separate(boolean direction, boolean open) {
        if (direction)
            sPane1.setPosition(open ? 0.18 : 0);
        else
            sPane2.setPosition(open ? 0 : 0.16);
    }

    public void convertGamepad(double y, double x){
        if (x < 0){
            x *= y < 0 ? 1 : -1;
            setMotorPower(x, x/2);
        }
        else if (x > 0){
            x *= y < 0 ? -1 : 1;
            setMotorPower(x / 2, x);
        }

        else
            setMotorPower(y, y);

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
    public long centiToMillis(int centimeter){
        return 1000L * centimeter / 150;
    }

    public void turnToAngle(double angle){
        double pow = power;
        power /= 3;

        if (angle < -179 || angle > 179)
            angle = -177;

        if (getYaw() > angle)
            while (getYaw() > angle)
                turn(false);
        else
            while (getYaw() < angle)
                turn(true);

        stop();


        power = pow;
    }

    public double addYaw(double yaw, int angle){
        double nx = angle + yaw;
        return nx > 180 ? -180 + (nx - 180) : (nx < -180 ? 180 + (nx + 180) : nx);
    }

    public void turn(int angle){
        turnToAngle(addYaw(getYaw(), angle));
    }

    public void forwardStraight(long to){

        long sysTime = System.currentTimeMillis();
        double yaw = getYaw();
        double power = getPower();
        while (System.currentTimeMillis() - sysTime < to){
            double y = getYaw();
            if (yaw < -179 || yaw > 179)
                yaw = -177;

            double golden = power < 0 ? 0.07 : -0.07;

            if (y > yaw)
                setMotorPower(power + golden, power);
            else
                setMotorPower(power, power + golden);
        }
    }

}

