package za.co.wethinkcode.examples.server.command;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.obstacles.*;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.Position;
import java.io.IOException;
import java.util.*;

public class LookCommand extends Command implements Response{

    public LookCommand(Player player) {
        super("look", player);
    }
    List objects;

    @Override
    public boolean execute(Player player) {
        if (player.isLaunched()) {
                List obstacles1 = firstWorld.getObstacles();
                List obstacles = obstacles1;
                LookObstacle lookObstacle = new LookObstacle();
                lookObstacle.setManager(manager);

            try {
                lookObstacle.setObstacles(obstacles);
            } catch (IOException e) {
                e.printStackTrace();
            }

                JSONArray objects =createJsonObjectStateClass.createObstacleMaps(lookObstacle);
                List players= manager.getPlayers();
                List<Obstacle> player_sqaure = new ArrayList<>();
                player_sqaure =createSqaureObstaclePlayer(player_sqaure,players);


            try {
                lookObstacle.setObstacles(player_sqaure);
            } catch (IOException e) {
                e.printStackTrace();
            }

          this.objects = createJsonObjectStateClass.createPlayerMaps(lookObstacle,objects);
          player.setResponse(successfulResponse());


        }else{

        }
        return true;
    }

    public List createSqaureObstaclePlayer(List<Obstacle> player_sqaure, List players) {
        for (int i=0; i< players.size();i++){
            Player play = (Player) players.get(i);
            if (!play.equals(player)){
                Position player_position = play.getPosition();
                SquareObstaclePlayers squareObstacle = new SquareObstaclePlayers(player_position.getX(),player_position.getY());
                player_sqaure.add(squareObstacle);}
        }
        return player_sqaure;
    }

    @Override
    public JSONObject successfulResponse() {
        JSONObject look_response = new JSONObject();
        JSONObject jsonState= new JSONObject();
        JSONObject all_objects = new JSONObject();

        JSONArray positionArray =createJsonObjectStateClass.createJsonPositionArray();
         jsonState =createJsonObjectStateClass.createJsonObjectState(jsonState, positionArray);
        all_objects.put("objects",objects);
        look_response.put( "result", "OK");
        look_response.put("data", all_objects);
        look_response.put("state", jsonState);

        return  look_response;
    }

    @Override
    public JSONObject notSuccessfulResponse() {
        return null;
    }


}
