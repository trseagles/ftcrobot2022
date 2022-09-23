package org.firstinspires.ftc.teamcode.modes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;

@TeleOp(name = "One Second Test")
public class SecondTest extends OpModeBase {
    @Override
    protected void repeat() throws InterruptedException {
        service.setPower(1);
        service.forward();
        sleep(1000);
        service.stop();
        stop();
    }
}
