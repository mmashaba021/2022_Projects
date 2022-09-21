package za.co.wethinkcode.examples.server.command;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.Position;
import za.co.wethinkcode.examples.server.world.RobotType;


public class LaunchCommand extends Command implements Response{
    public LaunchCommand(String argument,Player player) {
        super("launch",argument, player);
    }


    @Override
    public boolean execute(Player player) {
        try{
        if (manager.getPlayers().size()> firstWorld.getMaxPlayers()) {
            player.setResponse(noFreeSpace());
            manager.removePlayers(player);
            return  true;
        }

            setRobotType();
            setPlayerShieldAndShots();
            if(kickIfNameUsed()){
                return  true;
            }


        }catch(Exception k){

        }

            if (!player.isLaunched()) {

                player.setStatus("robot launched");
                    player.launch();
                    player.setLaunched(true);
                    player.setResponse(successfulResponse());
                    manager.checkTeamsPresent();

                return true;
            }

        return true;
    }

    private boolean kickIfNameUsed() {
        boolean check_name_used = checkNameUsed();
        if(check_name_used) {
            player.setResponse(nameAlreadyTaken());
            manager.removePlayers(player);

            return true;
        }
        return false;
    }

    private void setPlayerShieldAndShots() {
        if(player.getType().equals(RobotType.ROBOT_LONG_RANGE)){
            player.setShots(firstWorld.getDefault_shots_amount_type_longrange());
            player.setDistance((int) firstWorld.getDefault_shot_distance_type_longrange());

        }
        else{
            player.setShots(firstWorld.getDefault_shots_amount_type_shortrange());
            player.setDistance((int) firstWorld.getDefault_shot_distance_type_shortrange());
        }
        player.setSheild(manager.getFirstWorld().getDefault_sheild());
    }

    private void setRobotType() {

        switch (getArgument()) {

            case "long_range":
                player.setType(RobotType.ROBOT_LONG_RANGE);
                break;

            case "short_range":

                player.setType(RobotType.ROBOT_SHORT_RANGE);
                break;

            default:
                player.setType(RobotType.ROBOT_SHORT_RANGE);
                break;
        }
    }

    public boolean checkNameUsed(){
        double count_matches = 0;
        for (int i= 0; i<manager.getPlayers().size(); i++){
            String name = player.getRobotName();
            Player listed_player = (Player) manager.getPlayers().get(i);
            String listed_name = listed_player.getRobotName();

            if(listed_name.equalsIgnoreCase(name)){
                count_matches = count_matches +1;
            }
        }
        if (count_matches>1){
            return  true;
        }else{
            return  false;
        }
    }

    @Override
    public JSONObject successfulResponse(){

        JSONObject jsonMain = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject jsonState = new JSONObject();

        JSONArray positionArray =createJsonObjectStateClass.createJsonPositionArray();
        jsonState =createJsonObjectStateClass.createJsonObjectState(jsonState, positionArray);

        data.put( "position", positionArray);
        data.put("visibility", firstWorld.getDefault_visibility());
        data.put("reload", firstWorld.getDefault_reload());
        data.put("repair", firstWorld.getDefault_repair_time());
        data.put("shields", firstWorld.getDefault_sheild());


        jsonMain.put("result", "OK");
        jsonMain.put("data",data);
        jsonMain.put("state", jsonState);


        return jsonMain;
        }

    @Override
    public JSONObject notSuccessfulResponse() {
        return null;
    }

    public JSONObject noFreeSpace(){


            JSONObject jsonMain = new JSONObject();
            JSONObject data = new JSONObject();

            data.put( "message", "No more space in this world");
            jsonMain.put("result", "ERROR");
            jsonMain.put("data", data);

            return jsonMain;

        }




    public JSONObject nameAlreadyTaken(){
        JSONObject jsonMain = new JSONObject();
        JSONObject data = new JSONObject();
        data.put( "message", "Too many of you in this world");


        jsonMain.put("result", "ERROR");
        jsonMain.put("data", data);


        return jsonMain;

    }


}
