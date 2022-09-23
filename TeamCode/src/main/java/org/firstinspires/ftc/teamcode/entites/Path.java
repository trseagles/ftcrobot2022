package org.firstinspires.ftc.teamcode.entites;

import org.firstinspires.ftc.teamcode.utils.RobotService;

public class Path {
    private final int angle;
    private final boolean toOrAdd;
    private final long since;
    private final boolean straight;
    private final DriveMode mode;
    private final boolean pane;
    private final double power;

    private Path(int angle, long since, DriveMode mode, boolean straight, boolean toOrAdd, boolean pane, double power){
        this.angle = angle;
        this.since = since;
        this.mode = mode;
        this.straight = straight;
        this.toOrAdd = toOrAdd;
        this.pane = pane;
        this.power = power;
    }
    public static Path fromAngle(int angle, boolean toOrAdd){
        return new Path(angle, -1, DriveMode.STOP, false, toOrAdd, false, -1);
    }
    public static Path setPower(double power){
        return new Path(-1, -1, DriveMode.STOP, false, false, false, power);
    }
    public static Path fromDirection(long since, DriveMode mode){
        return new Path(-1, since, mode, false, false, false, -1);
    }
    public static Path makePane(boolean open){
        return new Path(-1, -1, DriveMode.STOP, false, false, open, -1);
    }

    public static Path fromForward(long since){
        return new Path(-1, since, DriveMode.FORWARD, true, false, false, -1);
    }
    public void execute(RobotService service){
        if (angle != -1){
            if (toOrAdd)
                service.turnToAngle(angle);
            else
                service.turn(angle);
        }
        else if (straight)
            service.forwardStraight(since);
        else if (since != -1){
            if (mode == DriveMode.FORWARD)
                service.forward();
            else if (mode == DriveMode.BACK)
                service.backward();
            else if (mode == DriveMode.LEFT)
                service.left();
            else if (mode == DriveMode.RIGHT)
                service.right();
            else if (mode == DriveMode.TURNR)
                service.turn(true);
            else if (mode == DriveMode.TURNL)
                service.turn(false);
            else if (mode == DriveMode.STOP)
                service.stop();
            try {
                Thread.sleep(since);
            } catch (InterruptedException ignored) {

            }
        }
        else
            service.stop();
        service.pane(pane);
        if (power != -1)
            service.setPower(power);
    }
}
