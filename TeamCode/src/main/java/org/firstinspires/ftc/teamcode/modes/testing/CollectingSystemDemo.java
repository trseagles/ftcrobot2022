package org.firstinspires.ftc.teamcode.modes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;
import org.firstinspires.ftc.teamcode.entites.RoboColor;

@TeleOp(name = "Collecting Demo")
public class CollectingSystemDemo extends OpModeBase {

    @Override
    public void repeat() throws InterruptedException {
        RoboColor clr = readTopColor();
        service.separate(clr == RoboColor.YELLOW);
        sleep(300);
    }

    @Override
    public void setup(){
        service.pane(true, false);
        service.pane(false, false);
    }

    @Override
    public void preRun(){
        service.intake(true);
    }
}
