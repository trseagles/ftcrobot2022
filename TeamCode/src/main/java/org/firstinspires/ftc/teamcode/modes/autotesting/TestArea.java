package org.firstinspires.ftc.teamcode.modes.autotesting;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.entites.DriveMode;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;
import org.firstinspires.ftc.teamcode.utils.RobotService;
import org.firstinspires.ftc.teamcode.utils.RobotUtils;

@Autonomous(name = "Test Area")
public class TestArea extends OpModeBase {

    private boolean mode = false;

    @Override
    public void repeat() {
        move(DriveMode.FORWARD, 1200);
        move(getMode(DriveMode.RIGHT, mode), 750);
        service.setPower(service.getPower() * 3/4);
        move(DriveMode.FORWARD, 1500);
        service.setPower(service.getPower());
        move(getMode(DriveMode.LEFT, mode), 1000);
        move(DriveMode.FORWARD, 1000);
        move(getMode(DriveMode.LEFT, mode), 1000);
        move(DriveMode.FORWARD, 1400);
        move(getMode(DriveMode.RIGHT, mode), 1200);
        sleep(5000);
        turn(1500, true);
        move(DriveMode.FORWARD, 2000);
        stop = true;
    }

}
