package org.firstinspires.ftc.teamcode.modes.autotesting;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;

@Autonomous(name = "Distance Escape", group = "Auto Testing")
public class DistanceEscapeTest extends OpModeBase {
    @Override
    protected void repeat() {
        double d1 = getFinalDistance(1);
        double d2 = getFinalDistance(2);

        if (d1 < 15 || d2 < 10)
            service.backward();

        if (d1 >= 10 && d2 >= 10)
            service.stop();

        sleep(100);
    }
}