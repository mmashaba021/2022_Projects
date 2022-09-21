package za.co.wethinkcode.examples.client.command;


import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.client.player.Player;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class BackCommand extends Command {

    public BackCommand(String argument, Player player) {
        super("back", argument,player);
    }

    @Override
    public boolean execute(Player player) {
        JSONObject jsonObject = player.getJsonObject();
        double nrSteps = 0;
        try {
            nrSteps = Integer.parseInt(getArgument());
        }catch (Exception c){
            List actions = player.getActions_list();
            actions.add("");
            actions.add("command error");
            player.setActions_list(actions);
            return true;
        }

        String result = (String) jsonObject.get("result");
        JSONObject state = (JSONObject) jsonObject.get("state");
        JSONObject data = (JSONObject) jsonObject.get("data");

        if (result.equals("OK")){

            UpdatePlayerState update = new UpdatePlayerState();
            update.setPlayer(player);
            update.updatePlayerState(state);

            String message = (String) data.get("message");


            if (message.equals("Done")) {
                firstWorld.back(nrSteps, player.getTurtle(), player.getColor());
               List actions = player.getActions_list();
               String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));

               actions.add(getArgument()+" steps");
               actions.add("("+timeStamp+")"+"Backward move -");
               if (actions.size()>12){
                   actions.remove(0);
                   actions.remove(1);
               }
               player.setActions_list(actions);
               if(Integer.parseInt(getArgument())>1) {
                   firstWorld.setMost_recent_action("Robot moved " + getArgument() + " steps back");
               }
               else{ firstWorld.setMost_recent_action("Robot moved " + getArgument() + " step back");}

               drawInterface.reset(player.getTurtle());
            } else if (message.equals("Obstructed")) {

                List actions = player.getActions_list();
                String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));

                actions.add(getArgument()+" steps");
                actions.add("("+timeStamp+")"+"move obstructed");
                if (actions.size()>12){
                    actions.remove(0);
                    actions.remove(1);
                }
                player.setActions_list(actions);
                firstWorld.setMost_recent_action("ROBOT OBSTRUCTED - try a different move");
                drawInterface.obstructed();
                drawInterface.reset(player.getTurtle());

           }

        }


        return true;
    }

}
