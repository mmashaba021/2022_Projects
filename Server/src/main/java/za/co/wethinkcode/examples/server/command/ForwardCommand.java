package za.co.wethinkcode.examples.server.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.player.Player;

public class ForwardCommand extends Command implements Response {
    public ForwardCommand(String argument, Player player) {
        super("forward", argument,player);
    }


    @Override
    public boolean execute(Player player) {
        int nrSteps=0;
        try {
             nrSteps = Integer.parseInt(getArgument());
        }catch(Exception g){
            player.setResponse(notSuccessfulArgumentsResponse());
            return true;
        }
        if (player.updatePosition(nrSteps)) {

            player.setStatus("Moved forward by " + nrSteps + " steps.");

            try {
                player.getFirstWorld().forward(nrSteps, player.getTurtle(), player.getColor(),player);
                player.setResponse(successfulResponse());
            } catch (Exception e){}
            return true;
        }else{player.setResponse(notSuccessfulResponse());
            return true;
        }
    }

    public JSONObject response() {
        JSONObject jsonMain = new JSONObject();
        JSONObject jsonState = new JSONObject();

        JSONArray positionArray =createJsonObjectStateClass.createJsonPositionArray();
        jsonState =createJsonObjectStateClass.createJsonObjectState(jsonState, positionArray);

        jsonMain.put("result", "OK");
        jsonMain.put("state", jsonState);
        return jsonMain;

    }

    public JSONObject successfulResponse(){
        JSONObject jsonMain = response();
        JSONObject jsonMessage = new JSONObject();
        JSONObject data = new JSONObject();
        data.put( "message", "Done");
        jsonMain.put("data", data);
        return jsonMain;
    }


    public JSONObject notSuccessfulResponse(){
        JSONObject jsonMain = response();
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put( "message", "Obstructed");
        jsonMain.put("data", jsonMessage);
        return jsonMain;
    }


    public JSONObject notSuccessfulArgumentsResponse(){
        JSONObject jsonMain = response();
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put( "message", "Could not parse arguments");
        jsonMain.put("data", jsonMessage);
        return jsonMain;
    }

}
