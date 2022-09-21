package za.co.wethinkcode.examples.client.command;

import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.world.FirstWorld;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


public class AddtoActionsLog {

    FirstWorld firstWorld;
    Player player;

    public void setFirstWorld(FirstWorld firstWorld) {
        this.firstWorld = firstWorld;
    }
    public void addToActionsLog(String entry1, String entry2) {
        List actions = player.getActions_list();
        String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));

        actions.add(entry1);
        actions.add("("+timeStamp+")"+entry2);
        firstWorld.setMost_recent_action("Looked in all directions");
        if (actions.size()>12){
            actions.remove(0);
            actions.remove(1);
        }
        player.setActions_list(actions);
        if (actions.size()>12){
            actions.remove(0);
        }
    }

    public void setPlayer(Player playerNew) {
        player = playerNew;
    }
}
