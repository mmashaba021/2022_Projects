package za.co.wethinkcode.examples.client.command;//checks if robot is launced/ deciphers json file

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.player.RobotType;
import za.co.wethinkcode.examples.client.world.Notifications;
import za.co.wethinkcode.examples.client.world.Position;

public class LaunchCommand extends Command {

    public LaunchCommand(String argument, Player player) {
        super("launch", argument,player);
    }

    @Override
    public boolean execute(Player player) {

        checkJson(player.getJsonObject());
        return  true;
    }



    private void checkJson(JSONObject jsonObject){

        String result = (String) jsonObject.get("result");
        JSONObject state = (JSONObject) jsonObject.get("state");
        JSONObject data = (JSONObject) jsonObject.get("data");

        if (result.equals("OK")){

            JSONArray positionArray = (JSONArray) state.get("position");
            long visibility = (long) data.get("visibility");
            long repair = (long) data.get("repair");
            long reload = (long) data.get("reload");
            long shield = (long) data.get("shields");


             Double x1 = (Double) positionArray.get(0);
             Double y1 = (Double) positionArray.get(1);

             Position position = new Position( x1, y1);
             player.setPosition(position);

             drawInterface.makeWorld();
             firstWorld.setVisbility((int) visibility);
             firstWorld.setReload_time((int) reload);
             firstWorld.setRepair_time((int) repair);
             firstWorld.setDefault_shield((int) shield);


            if (getArgument().equals("long_range")){
                player.setRobotType(RobotType.ROBOT_LONG_RANGE);
                player.setShots(firstWorld.getDefault_shots_amount_long_range());
                player.setShot_distance(firstWorld.getDefault_shots_distance_long_range());
                firstWorld.setRobot_image(firstWorld.getLong_range_image());
                firstWorld.setWidth(firstWorld.getLong_range_width());
                firstWorld.setHeight(firstWorld.getLong_range_height());
                firstWorld.setNotificaton("tank");
        }

            if (getArgument().equals("short_range")){
                player.setRobotType(RobotType.ROBOT_SHORT_RANGE);
                player.setShots(firstWorld.getDefault_shots_amount_short_range());
                player.setShot_distance(firstWorld.getDefault_shots_distance_short_range());
                firstWorld.setRobot_image(firstWorld.getShort_range_image());
                firstWorld.setWidth(firstWorld.getShort_range_width());
                firstWorld.setHeight(firstWorld.getShort_range_height());
                firstWorld.setNotificaton("spaceship");
            }

            player.setSheild(firstWorld.getDefault_shield());
            player.setPlayer_launched(true);
            player.launchRobot();

        }
        }


        }