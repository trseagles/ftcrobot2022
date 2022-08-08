package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.teamcode.entites.OpModeBase;
import org.firstinspires.ftc.teamcode.entites.RoboColor;

import java.util.HashMap;

public class Collector implements Runnable {
    private final Thread thread;
    private final OpModeBase op;
    private boolean isStopped;

    public Collector(OpModeBase op){
        thread = new Thread(this);
        this.op = op;
    }

    @Override
    public void run() {
        while (!isStopped){
            RoboColor color = op.readTopColor();
            op.service.separate(color == RoboColor.YELLOW);
            op.sleep(100);
        }
    }

    public void start(){
        thread.start();
        isStopped = false;
    }

    public void stop(){
        isStopped = true;
    }
}
