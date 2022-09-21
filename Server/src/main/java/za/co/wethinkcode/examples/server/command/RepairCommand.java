package za.co.wethinkcode.examples.server.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.State;

public class RepairCommand extends Command implements Response {


    public RepairCommand(String name, Player player) {
        super("repair", player);
    }



    @Override
    public boolean execute(Player player) {
        player.setOperational_state(State.REPAIR);
        player.setResponse(successfulResponse());
        Runnable r =  new Repairing(player);
        Thread thread = new Thread(r);
        thread.start();

        return true;
    }

    public JSONObject successfulResponse(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonMessage = new JSONObject();
        JSONObject jsonState = new JSONObject();

        JSONArray positionArray =createJsonObjectStateClass.createJsonPositionArray();
        jsonState =createJsonObjectStateClass.createJsonObjectState(jsonState, positionArray);


        jsonMessage.put( "message", "Done");


        jsonState =createJsonObjectStateClass.createJsonObjectState(jsonState, positionArray);
        jsonMain.put("result", "OK");
        jsonMain.put("data", jsonMessage);
        jsonMain.put("state", jsonState);


        return jsonMain;
    }

    @Override
    public JSONObject notSuccessfulResponse() {
        return null;
    }
}


