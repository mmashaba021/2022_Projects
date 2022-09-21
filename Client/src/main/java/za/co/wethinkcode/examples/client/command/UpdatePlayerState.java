package za.co.wethinkcode.examples.client.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.world.Position;

public class UpdatePlayerState {
    public void setPlayer(Player player) {
        this.player = player;
    }

    Player player;


    public void updatePlayerState(JSONObject state) {
        setPlayerPosition(state);
        setPlayerSheild(state);
        setPlayerShots(state);
        setPlayerStatus(state);
        setPlayerDirection(state);
    }

    private void setPlayerDirection(JSONObject state) {
        String direction = (String) state.get("direction");
        player.setDirection(direction);
    }

    private void setPlayerStatus(JSONObject state) {
        String operational = (String) state.get("status");

        player.setOperational_state(operational);

    }

    private void setPlayerShots(JSONObject state) {
        long shots = (long) state.get("shots");
        player.setShots((shots));
    }

    private void setPlayerSheild(JSONObject state) {

        long shield = (long) state.get("shields");
        player.setSheild((shield));
    }

    private void setPlayerPosition(JSONObject state) {
        JSONArray positionArray = (JSONArray) state.get("position");
        Double x1 = (Double) positionArray.get(0);
        Double y1 = (Double) positionArray.get(1);

        Position position = new Position( x1,y1);
        player.setPosition(position);
    }

}
