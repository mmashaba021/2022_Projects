package za.co.wethinkcode.examples.client.command;

import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.client.player.Player;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class FireCommand extends Command{

    public FireCommand(Player player) {
        super("fire",player);
    }

    @Override
    public boolean execute(Player player) {


        JSONObject jsonObject = player.getJsonObject();


        String result = (String) jsonObject.get("result");
        JSONObject state = (JSONObject) jsonObject.get("state");
        JSONObject data = (JSONObject) jsonObject.get("data");
        if (result.equals("OK")) {

            firstWorld.fire(player.getShot_distance(), player.getTurtle());
        }

            UpdatePlayerState update = new UpdatePlayerState();
            update.setPlayer(player);
            update.updatePlayerState(state);
            String message = (String) data.get("message");
            List actions = addCommandToLog();
            setRecentActions(message);
            player.setActions_list(actions);

        return true;
    }



    private void setRecentActions(String message) {
        if (message.equals("Miss")) {
            firstWorld.setMost_recent_action("FIRED A SHOT:THE SHOT MISSED");
        }

        if (message.equals("Hit")) {
            firstWorld.setMost_recent_action("FIRED A SHOT - THE SHOT HIT!!");
        }

        if (message.equals("Miss: Robot was teammate")) {
            firstWorld.setMost_recent_action("FIRED A SHOT - MISS: ROBOT WAS A TEAMMATE");
        }
        if (message.equals("No ammo")) {
            firstWorld.setMost_recent_action("NO SHOTS LEFT - PLEASE RELOAD");
        }
    }


    private List addCommandToLog() {
        List actions = player.getActions_list();
        String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
        actions.add("");
        actions.add("("+timeStamp+")"+"Fire");
        if (actions.size()>12){
            actions.remove(0);
            actions.remove(1);
        }return  actions;
    }
}