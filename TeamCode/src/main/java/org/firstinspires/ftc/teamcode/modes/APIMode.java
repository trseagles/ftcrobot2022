package org.firstinspires.ftc.teamcode.modes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.entites.LogType;
import org.firstinspires.ftc.teamcode.entites.OpModeBase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

@TeleOp(name = "Socket API")
public class APIMode extends OpModeBase {

    @Override
    public void setup(){
        util.log(LogType.INFO, "Waiting to socket start.");
    }

    @Override
    public void repeat() {
        try(
                ServerSocket socket = new ServerSocket(9294);
                Socket a = socket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(a.getInputStream()))
        ){
            util.log(LogType.INFO, "OK");
            while (a.isConnected() && opModeIsActive()){
                if (!reader.ready())
                    continue;
                String line = reader.readLine();
                if (line == null || line.isEmpty())
                    break;
                util.log(LogType.INFO, line);
                convertApi(line);
            }
            util.log(LogType.WARNING,"RESTART");
        }
        catch(Exception e){
            util.log(LogType.WARNING,"ERROR, RESTARTING", "EX: " + e.getMessage());
        }

        sleep(500);
    }

    public void convertApi(String data){
        if (data.equals("forward"))
            service.forward();
        else if (data.equals("backward"))
            service.backward();
        else if (data.equals("right"))
            service.right();
        else if (data.equals("left"))
            service.left();
        else if (data.startsWith("speed"))
            service.setPower((double) Integer.parseInt(data.substring(5))/100);
        else if (data.startsWith("spx")){
            String[] spl = data.substring(3).split("\\|");
            service.setMotorPower(Double.parseDouble(spl[1]), Double.parseDouble(spl[0]));
        }
        else if (data.equals("turn"))
            service.turn(true);
        else if (data.equals("action1"))
            service.intake(true);
        else if (data.equals("action2"))
            service.intake(false);
        else if (data.startsWith("hex")){
            String[] q = data.substring(3).split("\\|");
            service.setIntake(Double.parseDouble(q[0]), Double.parseDouble(q[1])); // Ön Arka
        }
        else
            service.stop();
    }
}
