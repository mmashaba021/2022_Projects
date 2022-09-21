package za.co.wethinkcode.examples.server.command;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.Direction;
import za.co.wethinkcode.examples.server.world.Position;

public class TurnRightCommand extends Command implements Response {

    public TurnRightCommand(Player player) {
        super("right",player);
    }

    @Override
    public boolean execute(Player player) {


          try {
              firstWorld.right(player.getTurtle());
              player.updateDirection(true);
          }catch (Exception e){}
        player.setStatus("Turned right.");
        player.setResponse(successfulResponse());
        return true;
    }

    public JSONObject successfulResponse(){

        JSONObject jsonMain= new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject jsonState = new JSONObject();
        data.put( "message", "Done");

        JSONArray positionArray =createJsonObjectStateClass.createJsonPositionArray();
        jsonState =createJsonObjectStateClass.createJsonObjectState(jsonState, positionArray);
        jsonState =createJsonObjectStateClass.createJsonObjectState(jsonState, positionArray);

        jsonMain.put("result", "OK");
        jsonMain.put("data", data);
        jsonMain.put("state", jsonState);


        return jsonMain;
    }

    @Override
    public JSONObject notSuccessfulResponse() {
        return null;
    }
}
