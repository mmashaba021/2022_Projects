package za.co.wethinkcode.examples.server.command;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.player.Player;

public class OrientationCommand extends Command implements Response {
    public OrientationCommand(Player player) {
        super("orientation",player);
    }


    @Override
    public boolean execute(Player player) {
        player.setResponse(successfulResponse());
        return true;
    }
    public JSONObject successfulResponse(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonMessage= new JSONObject();
        JSONObject jsonState = new JSONObject();

        jsonMessage.put( "message", "Done");

        JSONArray positionArray =createJsonObjectStateClass.createJsonPositionArray();
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
