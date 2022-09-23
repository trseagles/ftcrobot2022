package org.firstinspires.ftc.teamcode.modes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;
import org.firstinspires.ftc.teamcode.entites.RoboColor;

@TeleOp(name = "Color Test")
public class ColorTest extends OpModeBase {

    @Override
    public void repeat() {
        RoboColor color = readTopColor();
        NormalizedRGBA f = service.topColor.getNormalizedColors();

        util.log(LogType.INFO, "Color: " + color, "Red: " + f.red * 255, "Green: " + f.green * 255, "Blue: " + f.blue * 255, "Alpha: " + f.alpha * 255, "Distance: " + service.topColor.getDistance(DistanceUnit.CM));
        sleep(100);
    }
}
