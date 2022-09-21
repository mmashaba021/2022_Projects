package za.co.wethinkcode.examples.client.command;

import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.client.player.Player;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class RepairCommand extends Command {

    public RepairCommand(String argument, Player player) {
        super("repair", argument,player);
    }

    @Override
    public boolean execute(Player player) {
        JSONObject jsonObject = player.getJsonObject();

        JSONObject state = (JSONObject) jsonObject.get("state");

        UpdatePlayerState update = new UpdatePlayerState();
        update.setPlayer(player);
        update.updatePlayerState(state);

        List actions = player.getActions_list();
        String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));

        actions.add(getArgument()+"");
        actions.add("("+timeStamp+")"+"Repaired");
        if (actions.size()>12){
                    actions.remove(0);
                    actions.remove(1);
                }


        firstWorld.setMost_recent_action("Repairing");
        drawInterface.repair();
        firstWorld.setMost_recent_action("Robot repaired. Shield at maximum");
        player.setActions_list(actions);
        player.setOperational_state("NORMAL");
        drawInterface.reset(player.getTurtle());


        return true;
    }


}
