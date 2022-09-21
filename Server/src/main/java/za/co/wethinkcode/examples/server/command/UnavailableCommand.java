package za.co.wethinkcode.examples.server.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.Position;


public class UnavailableCommand extends Command implements Response{
    public UnavailableCommand(Player player) {
        super("unavailable",player);
    }

    @Override
    public boolean execute(Player player) {

            JSONObject jsonMain = new JSONObject();
            JSONObject data = new JSONObject();

            data.put("message", "Unavailable command");
            jsonMain.put("result", "ERROR");
            jsonMain.put("data", data);
            player.setResponse(successfulResponse());
            return true;

    }


    public JSONObject successfulResponse(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonState = new JSONObject();

        JSONArray positionArray =createJsonObjectStateClass.createJsonPositionArray();
        jsonState =createJsonObjectStateClass.createJsonObjectState(jsonState, positionArray);

        jsonMain.put("state", jsonState);
        jsonMain.put("message", "state not NORMAL");
        return jsonMain;
    }

    @Override
    public JSONObject notSuccessfulResponse() {
        return null;
    }

}
