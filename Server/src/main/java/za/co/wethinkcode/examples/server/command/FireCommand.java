package za.co.wethinkcode.examples.server.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.obstacles.SquareObstaclePlayers;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.Direction;
import za.co.wethinkcode.examples.server.world.Position;
import za.co.wethinkcode.examples.server.world.State;

import java.util.Map;


public class FireCommand extends Command implements Response{

    public FireCommand(Player player) {
        super("left",player);
    }

    @Override
    public boolean execute(Player player) {

        if (player.getShots() > 0) {

            player.setShots(player.getShots() - 1);

             Map checkHitPlayers = player.fire(player.getDistance());
             if (checkHitPlayers.size()>0) {
            boolean check_entry = false;

                     Map.Entry<String, SquareObstaclePlayers> entry = (Map.Entry<String, SquareObstaclePlayers>) checkHitPlayers.entrySet().stream().findFirst().get();
                     String key = entry.getKey();
                     SquareObstaclePlayers found_obstsacles  = entry.getValue();
                     Position test_position = new Position(found_obstsacles.getBottomLeftX(), found_obstsacles.getBottomLeftY());
                 Player found_player = player;
                 for(int i = 0; i<manager.getPlayers().size();i++) {
                          Player players1 = (Player) manager.getPlayers().get(i);
                          if (players1.getPosition().getX() == test_position.getX()
                          && players1.getPosition().getY() == test_position.getY()){
                              found_player = players1;
                              break;
                          }

                         }

                         if (!found_player.getTeam().equals(player.getTeam())) {
                             found_player.setSheild(found_player.getSheild() - 1);

                             if (found_player.getSheild() < 1) {
                                 found_player.setOperational_state(State.DEAD);
                             }
                             try {
                                 player.setResponse(hitResponse());
                             } catch (Exception e) {
                             }
                             return true;
                         }
                         else{ try {
                             player.setResponse(missTeamResponse());
                         } catch (Exception e) {
                         }
                             return true;
                         }
             }
             else{player.setResponse(missResponse());
                 return true;
             }
        }
        else {
            player.setStatus("You're out of ammo. Reload");
            player.setResponse(noAmmoResponse());
        }return true;
    }

    public JSONObject hitResponse(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put( "message", "Hit");

        jsonMain.put("result", "OK");
        jsonMain.put("data", jsonMessage);
        jsonMain.put("state", response());

        return jsonMain;
    }

    public JSONObject missResponse(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put( "message", "Miss");

        jsonMain.put("result", "OK");
        jsonMain.put("data", jsonMessage);
        jsonMain.put("state", response());

        return jsonMain;
    }

    public JSONObject noAmmoResponse(){
        JSONObject jsonMain = new JSONObject();
        JSONObject jsonMessage = new JSONObject();

        jsonMessage.put( "message", "No ammo");

        jsonMain.put("result", "failed");
        jsonMain.put("data", jsonMessage);
        jsonMain.put("state", response());

        return jsonMain;
    }

    public JSONObject response(){
        JSONObject jsonState = new JSONObject();
        JSONArray positionArray =createJsonObjectStateClass.createJsonPositionArray();
        jsonState =createJsonObjectStateClass.createJsonObjectState(jsonState, positionArray);

        return jsonState;

    }

    public JSONObject missTeamResponse(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonMessage = new JSONObject();

        jsonMessage.put( "message", "Miss: Robot was teammate");

        jsonMain.put("result", "OK");
        jsonMain.put("data", jsonMessage);
        jsonMain.put("state", response());

        return jsonMain;
    }

    @Override
    public JSONObject successfulResponse() {
        return null;
    }

    @Override
    public JSONObject notSuccessfulResponse() {
        return null;
    }
}