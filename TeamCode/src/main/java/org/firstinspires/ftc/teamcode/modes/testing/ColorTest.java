package org.firstinspires.ftc.teamcode.modes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;
import org.firstinspires.ftc.teamcode.entites.RoboColor;

@TeleOp(name = "Color Test")
public class ColorTest extends OpModeBase {

    @Override
    public void repeat() {
        RoboColor color = readTopColor();
        switch (color) {
            case RED:
                util.log(LogType.INFO, "Color: RED");
                break;
            case GREEN:
                util.log(LogType.INFO, "Color: GREEN");
                break;
            case BLUE:
                util.log(LogType.INFO, "Color: BLUE");
                break;
            case CYAN:
                util.log(LogType.INFO, "Color: CYAN");
                break;
            case MAGENTA:
                util.log(LogType.INFO, "Color: MAGENTA");
                break;
            case YELLOW:
                util.log(LogType.INFO, "Color: YELLOW");
                break;
            case BLACK:
                util.log(LogType.INFO, "Color: BLACK");
                break;
            case WHITE:
                util.log(LogType.INFO, "Color: WHITE");
                break;
        }
        sleep(100);
    }
}
