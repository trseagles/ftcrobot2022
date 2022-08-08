package org.firstinspires.ftc.teamcode.modes.testing;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.*;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;

@TeleOp(name = "Gyro Test")
public class GyroTest extends OpModeBase {

    @Override
    public void setup(){
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;

        service.imu.initialize(parameters);
    }

    @Override
    public void preRun(){
        //service.imu.startAccelerationIntegration(new Position(), new Velocity(), 100);
    }

    @Override
    public void repeat() {
        //Acceleration a = service.imu.getAcceleration().toUnit(DistanceUnit.CM);

        Orientation o = service.imu.getAngularOrientation();

        util.log(LogType.INFO,
                "Acquisition: " + o.acquisitionTime,
                "A1: " + o.firstAngle,
                "A2: " + o.secondAngle,
                "A3: " + o.thirdAngle);

        sleep(100);
    }
}
