package org.firstinspires.ftc.teamcode.entites;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.utils.RobotService;
import org.firstinspires.ftc.teamcode.utils.RobotUtils;

public abstract class OpModeBase extends LinearOpMode {
    public RobotService service;
    protected RobotUtils util;
    protected boolean stop = false;

    @Override
    public final void runOpMode() throws InterruptedException {
        service = new RobotService(hardwareMap);
        util = new RobotUtils(telemetry);
        setup();
        waitForStart();
        preRun();

        while (opModeIsActive()){
            if (stop)
                break;
            repeat();
        }
        stop();
    }

    protected void setup(){
        util.log(LogType.INFO, "Waiting for start.");
    }

    protected void preRun(){
        util.log(LogType.INFO, "Started.");
    }

    protected abstract void repeat() throws InterruptedException;

    public double getFinalDistance(int index){
        return util.getDistance(service.getOriginalDistance(index));
    }

    protected void move(DriveMode mode, long time){
        if (mode == DriveMode.BACK)
            service.backward();
        else if (mode == DriveMode.LEFT)
            service.left();
        else if (mode == DriveMode.RIGHT)
            service.right();
        else
            service.forward();
    }
    protected DriveMode getMode(DriveMode mode, boolean opposite){
        if (opposite){
            if (mode == DriveMode.BACK)
                return DriveMode.FORWARD;
            else if (mode == DriveMode.LEFT)
                return DriveMode.RIGHT;
            else if (mode == DriveMode.RIGHT)
                return DriveMode.LEFT;
            else
                return DriveMode.BACK;
        }
        else
            return mode;
    }

    protected void turn(long time, boolean direction){
        service.turn(direction);
        sleep(time);
    }

    @SafeVarargs
    protected final boolean isGamepadButtonPressed(Pointer<Boolean>... buttons){
        boolean a = false;
        for (Pointer<Boolean> button : buttons){
            if (!button.getObj())
                continue;
            a = true;
        }
        for (Pointer<Boolean> button : buttons){
            while (button.getObj())
                idle();
        }
        return a;
    }

    public RoboColor readTopColor(){
        return util.getColor(service.topColor.getNormalizedColors());
    }
    public RoboColor readBottomColor(){
        return util.getColor(service.botColor.getNormalizedColors());
    }
}
