package org.firstinspires.ftc.teamcode.modes;

import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.teamcode.entites.*;
import org.firstinspires.ftc.teamcode.utils.Collector;
import org.firstinspires.ftc.teamcode.utils.Distancer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Autonomous(name = "The Autonomous", group = "Autonomous")
public class TheAutonomous extends OpModeBase {

    private final Distancer distancer;
    private final Collector collector;
    private final HashMap<Integer, Double> limits;
    private final Path[] paths = new Path[] {
            Path.setPower(0.95),
            Path.fromDirection(1350,DriveMode.FORWARD),
            Path.setPower(1),
            Path.fromAngle(-84, false),
            Path.setPower(0.5),
            Path.fromForward(2875),
            Path.setPower(1),
            Path.fromAngle(90,false),
            Path.fromForward(900),
            Path.fromAngle(90,false),
            Path.fromForward(1300),
            Path.fromAngle(89,false),
            Path.fromDirection(850,DriveMode.BACK),
            Path.fromDirection(1000,DriveMode.STOP),
            Path.makePane(true),
            Path.fromDirection(2500,DriveMode.STOP),
            Path.fromForward(3300)


    };

    public TheAutonomous(){
        limits = new HashMap<>();
        limits.put(1, 10.0);
        limits.put(2, 10.0);

        distancer = new Distancer(this, limits) {
            @Override
            protected void onLimitExceeded(int index) {
                TheAutonomous.this.onLimitExceeded(index);
            }
        };
        collector = new Collector(this);
    }

    @Override
    protected void preRun() {
        super.preRun();
        service.setPower(1);
        //distancer.start();
        collector.start();
    }

    boolean done = false;

    @Override
    protected void stopInit() {
        distancer.stop();
        collector.stop();
    }

    @Override
    protected void repeat() throws InterruptedException {
        if (done)
            return;

        for (Path i : paths){
            i.execute(service);
        }


        /*double target = 0;
        double firstYaw = 0;

        //service.turnToAngle(0);

        moveForSeconds(true, 188); // ileri

        service.turnToAngle(target = service.addYaw(firstYaw, 85)); // sol

        service.setPower(0.3);
        moveForSeconds(true, 550); // ileri
        service.setPower(1);

        service.turnToAngle(target = service.addYaw(target, -73)); // sağ

        moveForSeconds(true, 140); // ileri

        service.turnToAngle(target = service.addYaw(target, -90)); // sağ

        moveForSeconds(true, 172); // ileri

        service.turnToAngle(target = service.addYaw(target, -95)); // sağ

        moveForSeconds(false, 53); // geri

        service.stop(); // dur
        sleep(1000);
        service.pane(true); // kapak aç
        sleep(4000); // 3 saniye bekle
        service.pane(false); // kapak kapat

        moveForSeconds(true, 420); // ileri
        */
        /*moveForSeconds(true, 189); // ileri
x e basınca ıntake geri çalışsın 1 gücünde
        service.turnToAngle(target = service.addYaw(firstYaw, 85)); // sol

        service.setPower(0.3);
        moveForSeconds(true, 550); // ileri
        service.setPower(1);

        service.turnToAngle(target = service.addYaw(target, -71)); // sağ

        moveForSeconds(true, 140); // ileri

        service.turnToAngle(target = service.addYaw(target, -94)); // sağ

        moveForSeconds(true, 175); // ileri

        service.turnToAngle(target = service.addYaw(target, -95)); // sağ

        moveForSeconds(false, 49); // geri

        service.stop(); // dur
        sleep(1000);
        service.pane(true); // kapak aç
        sleep(3000); // 3 saniye bekle
        service.pane(false); // kapak kapat

        util.log(LogType.INFO, service.getYaw());
        service.forwardStraight(450); // ileri*/

        service.stop();
        stop();

        done = true;
    }

    private void onLimitExceeded(int index){
        util.log(LogType.WARNING, index + " numaralı sensöre bir cisim yaklaşıyor efenim.");
    }
}
