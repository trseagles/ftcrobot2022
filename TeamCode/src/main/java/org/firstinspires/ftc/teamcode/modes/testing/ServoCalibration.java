package org.firstinspires.ftc.teamcode.modes.testing;

import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;

@TeleOp(name = "Servo Calibration")
public class ServoCalibration extends OpModeBase {
    double a = 0;

    @Override
    protected void repeat() throws InterruptedException {
        service.sPane1.setPosition(a+=0.05);
        util.log(LogType.INFO, "Açı: " + a);
        sleep(1000);
    }

    @Override
    protected void preRun(){
        service.sPane1.setPosition(0);
        sleep(5000);
    }
}
