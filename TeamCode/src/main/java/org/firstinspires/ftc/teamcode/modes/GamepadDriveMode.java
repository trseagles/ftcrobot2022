package org.firstinspires.ftc.teamcode.modes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;
import org.firstinspires.ftc.teamcode.entites.Pointer;
import org.firstinspires.ftc.teamcode.utils.Collector;
import org.firstinspires.ftc.teamcode.utils.Configuration;

@TeleOp(name = "Gamepad Drive")
public class GamepadDriveMode extends OpModeBase {

    private Collector collector;
    private double power = Configuration.DEFAULT_POWER;
    private boolean type = true;
    private boolean intake = false;
    private boolean back = false;

    @Override
    public void repeat() throws InterruptedException {
        collector.run();
        service.convertGamepad(-gamepad1.left_stick_y, gamepad1.right_stick_x);

        if (gamepad1.left_bumper){
            service.setPower(power * 3 / 4);
            service.turn(true);
            service.setPower(power);
        }
        else if (gamepad1.right_bumper){
            service.setPower(power * 3 / 4);
            service.turn(false);
            service.setPower(power);
        }
        if (gamepad1.y){
            if (type){
                service.intake(intake = !intake);
                if (!intake)
                    type = false;
            }
            else{
                service.setIntakePower(-1);
                service.intake(intake = !intake);
                service.setIntakePower(1);
                if (!intake)
                    type = true;
            }


            while (gamepad1.y)
                idle();
        }
        if (gamepad1.a){
            service.pane(back = !back);
            while (gamepad1.a)
                idle();
        }
        if (gamepad1.left_trigger > 0){
            service.setMotorPower(gamepad1.left_trigger, 0);
        } else if (gamepad1.right_trigger > 0) {
            service.setMotorPower(0, gamepad1.right_trigger);
        }

        if (gamepad1.dpad_left){
            service.setPower(power * 2);
            service.turn(90);
        }
        else if (gamepad1.dpad_right){
            service.setPower(power * 2);
            service.turn(-90);
        }
        else if (gamepad1.dpad_up){
            power += 0.05;
            while (gamepad1.dpad_up)
                idle();
        }
        else if (gamepad1.dpad_down){
            power -= 0.05;
            while (gamepad1.dpad_down)
                idle();
        }

        service.setPower(power);

        util.log(LogType.INFO,
                "Power: " + power,
                "Intake: " + intake);
    }

    @Override
    public void preRun(){
        collector = new Collector(this);
        power = 1.0;
        service.pane(false);
    }
}
