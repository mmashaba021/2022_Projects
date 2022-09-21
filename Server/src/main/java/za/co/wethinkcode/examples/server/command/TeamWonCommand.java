package za.co.wethinkcode.examples.server.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.player.Player;


public class TeamWonCommand extends Command implements Response{
    public TeamWonCommand(Player player) {
        super("teamWon",player);
    }

    @Override
    public boolean execute(Player player) {

        JSONObject jsonMain = new JSONObject();
        JSONObject data = new JSONObject();
        String winner = getWinner();

        data.put("message", winner);
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
        jsonMain.put("message", getWinner());
        return jsonMain;
    }

        private String getWinner() {
            String winner = "";
            if(manager.isTeamOneHasWon()){
                winner = "TeamOneWon";

            }if(manager.isTeamTwoHasWon()){
                winner = "TeamTwoWon";
            }

            return  winner;

    }

        @Override
    public JSONObject notSuccessfulResponse() {
        return null;
    }
        }

