package org.firstinspires.ftc.teamcode.modes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;
import org.firstinspires.ftc.teamcode.utils.Configuration;

@TeleOp(name = "Gamepad Drive")
public class GamepadDriveMode extends OpModeBase {

    private double power = Configuration.DEFAULT_POWER;
    private boolean intake = false;

    @Override
    public void repeat() throws InterruptedException {
        service.convertGamepad(-gamepad1.left_stick_y, gamepad1.right_stick_x);

        if (gamepad1.left_bumper)
            service.turn(true);
        else if (gamepad1.right_bumper)
            service.turn(false);
        else if (gamepad1.x)
            service.separate(true);
        else if (gamepad1.b)
            service.separate(false);
        else if (gamepad1.left_stick_button)
            intake = true;
        else if (gamepad1.right_stick_button)
            intake = false;
        else if (gamepad1.y && power < 1.0)
            power += 0.05;
        else if (gamepad1.a && power > 0)
            power -= 0.05;
        service.setPower(power);
        service.intake(intake);

        util.log(LogType.INFO,
                "Power: " + power,
                "Intake: " + intake);
    }
}
