package za.co.wethinkcode.examples.server.command;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.command.Command;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.Direction;
import za.co.wethinkcode.examples.server.world.Position;

public class TurnLeftCommand extends Command implements Response {
    public TurnLeftCommand(Player player) {
        super("left",player);
    }


    @Override
    public boolean execute(Player player) {

        try{
            firstWorld.left(player.getTurtle());
            player.updateDirection(false);
        } catch (Exception e){}
        player.setResponse(successfulResponse());
        player.setStatus("Turned left.");

        return true;
    }
    public JSONObject successfulResponse(){
        JSONObject jsonMain = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject jsonState = new JSONObject();


        data.put( "message", "Done");
        JSONArray positionArray =createJsonObjectStateClass.createJsonPositionArray();
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
