package org.firstinspires.ftc.teamcode.modes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;

@TeleOp(name = "IMU Angle Test")
public class AngleTest extends OpModeBase {

    @Override
    protected void repeat() throws InterruptedException {
        service.setPower(0.3);
        service.turn(true);
        util.log(LogType.INFO, service.getYaw());
    }
}
