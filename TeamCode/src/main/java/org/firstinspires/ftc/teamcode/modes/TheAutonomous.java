package org.firstinspires.ftc.teamcode.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;
import org.firstinspires.ftc.teamcode.utils.Collector;
import org.firstinspires.ftc.teamcode.utils.Distancer;

import java.util.HashMap;

@Autonomous(name = "The Autonomous", group = "Autonomous")
public class TheAutonomous extends OpModeBase {

    private final Distancer distancer;
    private static final int gold = 1; // How many centimeter in one second
    private final Collector collector;
    private final HashMap<Integer, Double> limits;

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
        distancer.start();
        collector.start();
    }

    boolean done = false;

    @Override
    protected void repeat() throws InterruptedException {
        double since = getRuntime();
        Acceleration accel = service.imu.getAcceleration();

        if (done)
            return;

        /*
            * 2 metre ileri
            * sola doğru 90 derece
            * 1 metre ileri
            * sağa doğru 90 derece
            * 1 metre geri
         */

        service.forward();
        sleepForMove(200);
        service.turn(90);
        service.forward();
        sleepForMove(100);
        service.turn(0);
        service.backward();
        sleepForMove(100);
        done = true;
        /*distancer.stop();
        collector.stop();
        stop();*/
    }

    private void sleepForMove(int centimeter){
        sleep(centimeter / gold * 1000);
    }

    private void onLimitExceeded(int index){
        util.log(LogType.WARNING, index + " numaralı sensöre bir cisim yaklaşıyor efenim.");
    }
}
