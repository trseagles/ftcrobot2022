package org.firstinspires.ftc.teamcode.modes;

import android.annotation.SuppressLint;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;

@TeleOp(name = "Measurement", group = "Testing")
public class AdvancedMeasurement extends OpModeBase {
    @SuppressLint("DefaultLocale")
    @Override
    protected void repeat() throws InterruptedException {


        Acceleration accel = service.imu.getAcceleration();

        util.log(LogType.INFO,
                String.format("İvme (x,y,z,t): %f %f %f %d", accel.xAccel, accel.yAccel, accel.zAccel, accel.acquisitionTime),
                String.format("Rotasyon (yaw,roll,pitch,t): %f %f %f", service.getYaw(), service.getRoll(), service.getPitch()),
                "Orijinal Uzaklık 1: " + service.getOriginalDistance(1),
                "Uzaklık 1: " + service.getOriginalDistance(1),
                "Uzaklık 2: " + service.getOriginalDistance(2),
                "Orijinal Uzaklık 2: " + service.getOriginalDistance(2),
                "Ayırma Renk: " + readTopColor(),
                "Alt Renk: " + readBottomColor(),
                "Sağlıcakla Kalın Canlar :))");

    }

    @Override
    protected void preRun() {
        service.imu.startAccelerationIntegration(null, null, 10);
    }
}
