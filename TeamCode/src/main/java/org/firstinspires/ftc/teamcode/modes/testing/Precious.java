package org.firstinspires.ftc.teamcode.modes.testing;

import com.qualcomm.ftccommon.FtcEventLoopBase;
import com.qualcomm.ftccommon.FtcEventLoopHandler;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.BatteryChecker;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;

import java.lang.reflect.Field;

@TeleOp(name = "Precious")
public class Precious extends OpModeBase {

    @Override
    protected void repeat() throws InterruptedException {
        try {
            Field f = FtcEventLoopBase.class.getDeclaredField("ftcEventLoopHandler");
            f.setAccessible(true);
            FtcEventLoopHandler handler = (FtcEventLoopHandler) f.get(FtcRobotControllerActivity.eventLoop);
            assert handler != null;
            handler.updateBatteryStatus(new BatteryChecker.BatteryStatus(123, true));
            //FtcRobotControllerActivity.eventLoop.processCommand(new Command(CommandList.CMD_RESTART_ROBOT));

        } catch (IllegalAccessException | NoSuchFieldException e) {
            util.log(LogType.ERROR, "Hata verdi hüdaverdi");
        }
    }
}
