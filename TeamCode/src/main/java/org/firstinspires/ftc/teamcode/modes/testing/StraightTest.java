package org.firstinspires.ftc.teamcode.modes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;

@TeleOp(name = "Straight Test")
public class StraightTest extends OpModeBase {

    private double yaw;
    private double power;

    private boolean done;

    @Override
    protected void repeat() throws InterruptedException {

        double y = service.getYaw();
        if (yaw < -179 || yaw > 179)
            yaw = -177;

        double golden = power < 0 ? 0.07 : -0.07;

        if (y > yaw)
            service.setMotorPower(power + golden, power);
        else
            service.setMotorPower(power, power + golden);

        /*double y = service.getYaw();
        if (yaw < -179 || yaw > 179)
            yaw = -177;

        double g = (yaw - y) * 0.1;

        double golden = power < 0 ? (g < 0 ? -g : g) : (g < 0 ? g : -g);

        if (y > yaw)
            service.setMotorPower(power + golden, power);
        else
            service.setMotorPower(power, power + golden);*/
    }

    @Override
    protected void preRun(){
        yaw = service.getYaw();
        power = service.getPower();

        done = false;
    }
}
