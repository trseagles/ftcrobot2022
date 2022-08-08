package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.RoboColor;

public class RobotUtils {
    private final Telemetry telemetry;
    public RobotUtils(Telemetry telemetry){
        this.telemetry = telemetry;
    }
    private static String getPrefix(LogType t){
        switch (t){
            default:
            case INFO:
                return "i";
            case ERROR:
                return "!!";
            case WARNING:
                return "!";
        }
    }
    public void log(LogType type, Object... messages){
        String prefix = getPrefix(type);
        for (Object i : messages)
            telemetry.addData(prefix, i);
        telemetry.update();
    }
    public double getDistance(double distance){
        return Math.round(distance < 2 ? 0 : (distance-7 < 0 ? 0 : distance-7));
    }
    public RoboColor getColor(NormalizedRGBA rgb){
        double red = rgb.red*1000;
        double green = rgb.green*1000;
        double blue = rgb.blue*1000;

        if (red > green && red > blue)
            return RoboColor.RED;
        else if (blue > red && blue > green)
            return RoboColor.BLUE;
        else if (red > blue && green > blue)
            return RoboColor.YELLOW;
        else if (red < 100 && green < 100 && blue < 100)
            return RoboColor.BLACK;
        else
            return RoboColor.WHITE;
    }

}