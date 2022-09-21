package za.co.wethinkcode.examples.client.command;

import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.turtle2.StdDraw2;
import za.co.wethinkcode.examples.client.turtle2.Turtle2;

import java.util.List;

public class UnavailableCommand extends Command {

    public UnavailableCommand(Player player) {
        super("unavailable",player);
    }

    @Override
    public boolean execute(Player player) {
        List actions = player.getActions_list();
        actions.add("waiting for robot");
        if (actions.size()>12){
            actions.remove(0);
        }
        player.setActions_list(actions);
        Turtle2 turtle2 = new Turtle2(0,0,90);

        drawInterface.reset(turtle2);
        StdDraw2.show();
        return true;
    }


}
