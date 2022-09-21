package za.co.wethinkcode.examples.client.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.world.Notifications;

import java.util.ArrayList;
import java.util.List;

public class LookCommand extends Command {

    public LookCommand( Player player) {
        super("look",player);
    }

    @Override
    public boolean execute(Player player) {
        JSONObject jsonObject = player.getJsonObject();

        String result= (String) jsonObject.get("result");
        JSONObject state = (JSONObject) jsonObject.get("state");
        JSONObject data = (JSONObject) jsonObject.get("data");
        if (result.equals("OK")){

            UpdatePlayerState update = new UpdatePlayerState();
            update.setPlayer(player);
            update.updatePlayerState(state);

            JSONObject stored_map = data;
            JSONArray look_found_objects = (JSONArray) stored_map.get("objects");

           addToLog();

            List full_obstacles= checkLookFoudObjects(look_found_objects);
            if (full_obstacles.size()>0) {
                startNotificationsWindow(full_obstacles);

            }else{

                firstWorld.setMost_recent_action("Looked in all directions: No obstacles or robots found");
            }

        }

        drawInterface.reset(player.getTurtle());
        return true;
    }

    private void addToLog() {
        AddtoActionsLog addToActionsLog = new AddtoActionsLog();
        addToActionsLog.setFirstWorld(firstWorld);
        addToActionsLog.setPlayer(player);
        String entry2 = "Looked in all directions";
        String entry1 = "";
        addToActionsLog.addToActionsLog(entry1,entry2);
    }


    private void startNotificationsWindow(List full_obstacles) {
        firstWorld.setNotificaton("look");
        firstWorld.setObstacles_found(full_obstacles);
        firstWorld.CheckFoundObstacles();
        Runnable r =  new Notifications(firstWorld);
        Thread thread = new Thread(r);
        thread.start();
    }

    private List checkLookFoudObjects(JSONArray look_found_objects) {

        List full_obstacles = new ArrayList<>();

        for (int i = 0; i< look_found_objects.size();i++) {
            JSONObject each_found_entry = (JSONObject) look_found_objects.get(i);
            List obstacle_found =new ArrayList<>();

            String type = (String) each_found_entry.get("type");
            String distance = (String) each_found_entry.get("distance");
            String direction_obj = (String) each_found_entry.get("direction");

            try {
                if(type.equals("Robot")){

                }
                if (type.equals("Obstacles") || type.equals("Robot")) {

                    obstacle_found.add(type);
                    obstacle_found.add(distance);
                    obstacle_found.add(direction_obj);
                    full_obstacles.add(obstacle_found);
                }
            }catch (Exception v){

            }
        }
        return  full_obstacles;
    }


}
