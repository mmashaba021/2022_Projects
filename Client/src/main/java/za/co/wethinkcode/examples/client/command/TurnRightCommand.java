package za.co.wethinkcode.examples.client.command;


import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.client.player.Player;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


public class TurnRightCommand extends Command {

    public TurnRightCommand(Player player) {
        super("right",player);
    }

    @Override
    public boolean execute(Player player) {
        JSONObject jsonObject = player.getJsonObject();
        String result = (String) jsonObject.get("result");
        JSONObject state = (JSONObject) jsonObject.get("state");
        JSONObject data = (JSONObject) jsonObject.get("data");
        if (result.equals("OK")){

            UpdatePlayerState update = new UpdatePlayerState();
            update.setPlayer(player);
            update.updatePlayerState(state);

            String message = (String) data.get("message");

        if (message.equals("Done")) {
           firstWorld.right(player.getTurtle());
           List actions = player.getActions_list();
           String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
           actions.add("");
           actions.add("("+timeStamp+")"+"turned right");
           if (actions.size()>12){
               actions.remove(0);
               actions.remove(1);
                }
                player.setActions_list(actions);
                firstWorld.setMost_recent_action("Robot turned right");
                drawInterface.reset(player.getTurtle());
            }

        }

        drawInterface.reset(player.getTurtle());
        return true;
    }

}
