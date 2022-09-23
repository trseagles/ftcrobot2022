package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;
import org.firstinspires.ftc.teamcode.entites.RoboColor;

import java.util.HashMap;

public class Collector implements Runnable {
    private final Thread thread;
    private final OpModeBase op;
    private double firstAlpha;
    private boolean isStopped;

    public Collector(OpModeBase op){
        thread = new Thread(this);
        this.op = op;
        isStopped = true;
        try{
            firstAlpha = op.service.topColor.getNormalizedColors().alpha * 255 - 1.5;
        }
        catch (Exception ignored){

        }
    }

    @Override
    public void run() {
        do {
            RoboColor clr = op.readTopColor();
            NormalizedRGBA f = op.service.topColor.getNormalizedColors();
            double alpha = f.alpha * 255;

            op.util.log(LogType.INFO, "Color: " + clr, "Red: " + f.red * 255, "Green: " + f.green * 255, "Blue: " + f.blue * 255, "Alpha: " + f.alpha * 255, "Distance: " + op.service.topColor.getDistance(DistanceUnit.CM), "First Alpha: " + firstAlpha);
            if (clr == RoboColor.YELLOW || (clr == RoboColor.BLACK && alpha < firstAlpha)){
                op.service.separate(clr != RoboColor.YELLOW, true);
                op.service.separate(clr == RoboColor.YELLOW, false);
            }
            else{
                op.service.separate(true, false);
                op.service.separate(false, false);
            }
            op.sleep(100);
        }
        while (!isStopped);
    }

    public void start(){
        firstAlpha = op.service.topColor.getNormalizedColors().alpha * 255 - 1.6;
        thread.start();
        op.service.intake(true);
        //op.service.intake(false);
        isStopped = false;
    }

    public void stop(){
        isStopped = true;
    }
}
