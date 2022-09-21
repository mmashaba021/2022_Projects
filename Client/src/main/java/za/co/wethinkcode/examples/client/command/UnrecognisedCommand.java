package za.co.wethinkcode.examples.client.command;

import za.co.wethinkcode.examples.client.player.Player;

import java.util.List;

public class UnrecognisedCommand extends Command {

    public UnrecognisedCommand(Player player) {
        super("unrecognised",player);
    }

    @Override
    public boolean execute(Player player) {

        List actions = player.getActions_list();
        actions.add("command error");
        if (actions.size()>12){
            actions.remove(0);
        }
        player.setActions_list(actions);
        firstWorld.setMost_recent_action("Invalid command. Please choose a valid command: press \\ for help");
        drawInterface.reset(player.getTurtle());
        return true;
    }


}
