package za.co.wethinkcode.examples.client.command;

import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.turtle2.StdDraw2;
import za.co.wethinkcode.examples.client.turtle2.Turtle2;

import java.util.List;

public class TeamWonCommand extends Command {

    public TeamWonCommand(Player player) {
        super("",player);
    }

    @Override
    public boolean execute(Player player) {
        List actions = player.getActions_list();

        checkJson();

        actions.add("Game Over: a team has won");
        if (actions.size()>12){
            actions.remove(0);
        }
        player.setActions_list(actions);
        Turtle2 turtle2 = new Turtle2(0,0,90);
        firstWorld.setMost_recent_action("Game Over: A team has won");
        drawInterface.reset(turtle2);
        drawInterface.Winner();
        StdDraw2.show();
        return true;
    }

    private String checkJson() {
        JSONObject jsonObject = player.getJsonObject();
        String message= (String) jsonObject.get("message");

        if(message.equals("TeamOneWon")){
            if (player.getRobotName().contains("One")){
                player.setWinner("YOUR TEAM WON");
            }
            return "Team One Won The game";
        }
        if(message.equals("TeamTwoWon")){
            if (player.getRobotName().contains("Two")){
                player.setWinner("YOUR TEAM WON");
            }
            return "Team Two Won The game";
        }


        return "";
    }


}
