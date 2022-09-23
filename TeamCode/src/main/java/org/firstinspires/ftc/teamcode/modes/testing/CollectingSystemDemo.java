package org.firstinspires.ftc.teamcode.modes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;
import org.firstinspires.ftc.teamcode.entites.RoboColor;
import org.firstinspires.ftc.teamcode.utils.Collector;

@TeleOp(name = "Collecting Demo")
public class CollectingSystemDemo extends OpModeBase {

    private Collector collector;

    @Override
    public void repeat() throws InterruptedException {
        /*RoboColor clr = readTopColor();
        util.log(LogType.INFO, clr);
        if (clr == RoboColor.YELLOW || clr == RoboColor.BLACK){
            service.separate(clr == RoboColor.YELLOW, true);
            service.separate(clr != RoboColor.YELLOW, false);
        }
        else{
            service.separate(true, false);
            service.separate(false, false);
        }
        sleep(300);*/
        collector.run();
        //service.separate(true, false);
    }

    @Override
    public void setup(){
        collector = new Collector(this);
        service.pane(false);
    }

    @Override
    public void preRun(){
        service.intake(true);
    }
}
