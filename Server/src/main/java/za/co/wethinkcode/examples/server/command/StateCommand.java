package za.co.wethinkcode.examples.server.command;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.Position;

public class StateCommand extends Command implements Response {

    public StateCommand(Player player) {
        super("launch", player);
    }


    @Override
    public boolean execute(Player player) {

        if (player.isLaunched()) {
            player.setResponse(successfulResponse());

        }

        else{
        }

        return true;
    }

    public JSONObject successfulResponse(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonState = new JSONObject();

        JSONArray positionArray =createJsonObjectStateClass.createJsonPositionArray();
        jsonState =createJsonObjectStateClass.createJsonObjectState(jsonState, positionArray);
        jsonMain.put("state", jsonState);


        return jsonMain;
    }

    @Override
    public JSONObject notSuccessfulResponse() {
        return null;
    }


}
