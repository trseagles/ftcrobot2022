package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.teamcode.entites.OpModeBase;

import java.util.HashMap;

public abstract class Distancer implements Runnable{

    private final Thread thread;
    private HashMap<Integer, Double> map;
    private final OpModeBase op;
    private boolean isStopped;

    public Distancer(OpModeBase op, HashMap<Integer, Double> map){
        thread = new Thread(this);
        this.op = op;
        this.map = map;
    }

    @Override
    public void run() {
        while (!isStopped){
            for (int key : map.keySet()){
                double distance = op.getFinalDistance(key);
                if (distance < map.get(key))
                    onLimitExceeded(key);
            }
        }
    }

    protected abstract void onLimitExceeded(int index);

    public void start(){
        thread.start();
        isStopped = false;
    }

    public void stop(){
        isStopped = true;
    }

}
