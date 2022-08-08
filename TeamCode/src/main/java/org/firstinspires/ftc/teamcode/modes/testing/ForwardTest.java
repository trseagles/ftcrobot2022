package org.firstinspires.ftc.teamcode.modes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;

@TeleOp(name = "Forward Test")
public class ForwardTest extends OpModeBase {

    @Override
    public void repeat() {
        service.forward();

        sleep(100);
        idle();
    }
}
