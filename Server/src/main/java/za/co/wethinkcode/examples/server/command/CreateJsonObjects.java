package za.co.wethinkcode.examples.server.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.obstacles.LookObstacle;
import za.co.wethinkcode.examples.server.obstacles.SquareObstacle;
import za.co.wethinkcode.examples.server.obstacles.SquareObstaclePlayers;
import za.co.wethinkcode.examples.server.player.Player;

import za.co.wethinkcode.examples.server.world.Position;

import java.util.*;

public class CreateJsonObjects {

List obstacle_path;
List duplicates;
    Player player;


    public void setPlayer(Player player) {
        this.player = player;
    }
    public JSONObject createJsonObjectState(JSONObject jsonState, JSONArray positionArray) {
        jsonState.put( "position", positionArray);
        jsonState.put("direction", player.getCurrentDirection().toString());
        jsonState.put( "shields", player.getSheild());
        jsonState.put("shots", player.getShots());
        jsonState.put("status", player.getOperational_state().toString());

        return jsonState;
    }

    public JSONArray createJsonPositionArray() {
        Position position =player.getPosition();
        double x = position.getX();
        double y = position.getY();
        JSONArray positionArray = new JSONArray();

        positionArray.add(x);
        positionArray.add(y);

        return positionArray;
    }


    public JSONObject jsonHashMap2(String direction,String type, Map<String, SquareObstaclePlayers> map1) {
        Map<String, String> jsonHashMap1 = new HashMap<String, String>();
        Map<String, String> jsonHashMap2 = new HashMap<String, String>();
        Map<String, String> jsonHashMap3 = new HashMap<String, String>();
        JSONObject listed = new JSONObject();
        if (map1.size()>0 ){
            int iterate = 0;
            for(SquareObstaclePlayers i : map1.values()){

                double distance = 0;


                SquareObstaclePlayers squareObstacle = i;
                List coordinates = (List)obstacle_path.get(iterate);
                double x1 = squareObstacle.getBottomLeftX();
                double y1 = squareObstacle.getBottomLeftY();
                double x_pos =(double) coordinates.get(0);
                double y_pos =(double) coordinates.get(1);


                if (direction.equals("north")){
                    double y_distance = player.getPosition().getY();
                    distance = y_pos - y_distance;
                }

               else if (direction.equals("south")){
                    double y_distance = player.getPosition().getY();
                    distance = y_distance- y_pos ;
                }

               else if (direction.equals("east")) {
                    double x_distance = player.getPosition().getX();
                    distance =  x_pos - x_distance;
                }

              else  if (direction.equals("west")) {
                    double x_distance = player.getPosition().getX();
                    distance = (x_distance) - x_pos ;
                }

                  jsonHashMap1.put("direction", direction);
                  jsonHashMap2.put("type", type);
                  jsonHashMap3.put("distance", String.valueOf(distance));

                  listed.putAll(jsonHashMap1);
                  listed.putAll(jsonHashMap2);
                  listed.putAll(jsonHashMap3);
              }



            }


        return listed;
    }



    public JSONObject jsonHashMap(String direction,String type, Map<String, SquareObstacle> map1) {
        Map<String, String> json_hash_map_direction = new HashMap<String, String>();
        Map<String, String> json_hash_map_type = new HashMap<String, String>();
        Map<String, String> json_hash_map_distance = new HashMap<String, String>();
        JSONObject listed = new JSONObject();
        if (map1.size() > 0) {
            for (SquareObstacle i : map1.values()) {

                double distance = 0;

                SquareObstacle squareObstacle = i;
                double x1 = squareObstacle.getBottomLeftX();
                double y1 = squareObstacle.getBottomLeftY();

                if (direction.equals("north")) {
                    double y_distance = player.getPosition().getY();
                    distance = y1 - y_distance;
                }

                else if (direction.equals("south")) {
                    double y_distance = player.getPosition().getY();
                    distance = (y_distance) - (y1 + 4);
                }

               else if (direction.equals("east")) {
                    double x_distance = player.getPosition().getX();
                    distance =  x1 - (x_distance) ;
                }

               else if (direction.equals("west")) {
                    double x_distance = player.getPosition().getX();
                    distance = (x_distance) -(x1+4) ;

                }

                json_hash_map_direction.put("direction", direction);
                json_hash_map_type.put("type", type);
                json_hash_map_distance.put("distance", String.valueOf(distance));


                listed.putAll(json_hash_map_direction);
                listed.putAll(json_hash_map_type);
                listed.putAll( json_hash_map_distance);




            }
        } return listed;
    }

    public JSONArray createPlayerMaps(LookObstacle lookObstacle, JSONArray objects) {
        Position position = player.getPosition();

        double x1= position.getX();
        double y1= position.getY();
        duplicates = new ArrayList<>();
        double visibility = player.getFirstWorld().getDefault_visibility();
        for (int i = 0; i<4; i++){
            List<String> directions=new ArrayList<String>(Arrays.asList("north", "south", "west","east"));
            List<Double> y_added=new ArrayList<Double>(Arrays.asList(y1+visibility,y1-visibility, y1,y1));
            List<Double> x_added=new ArrayList<Double>(Arrays.asList(x1,x1, x1-visibility,x1+visibility));

            Position direction_to_check = new Position(x_added.get(i),y_added.get(i));
            Map<String, SquareObstaclePlayers> checkDirection = lookObstacle.blocksPathPlayers(position, direction_to_check);
            obstacle_path = lookObstacle.getObstacles_path_blocked();

            JSONObject map1 = jsonHashMap2(directions.get(i),"Robot",checkDirection);
            objects.add(map1);

        }
        return  objects;
    }


    public JSONArray createObstacleMaps(LookObstacle lookObstacle) {
        Position position = player.getPosition();

        double x1= position.getX();
        double y1= position.getY();
        double visibility = player.getFirstWorld().getDefault_visibility();
        JSONArray objects = new JSONArray();
        for (int i = 0; i<4; i++){
            List<String> directions=new ArrayList<String>(Arrays.asList("north", "south", "west","east"));
            List<Double> y_added=new ArrayList<Double>(Arrays.asList(y1+visibility,y1-visibility, y1,y1));
            List<Double> x_added=new ArrayList<Double>(Arrays.asList(x1,x1, x1-visibility,x1+visibility));

            Position direction_to_check = new Position(x_added.get(i),y_added.get(i));
            Map<String, SquareObstacle> checkDirection = lookObstacle.blocksPath(position, direction_to_check);

            JSONObject map1 = jsonHashMap(directions.get(i),"Obstacles",checkDirection);
            objects.add(map1);

        }

        return  objects;
    }

}
