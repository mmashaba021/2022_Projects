package za.co.wethinkcode.examples.client.command;

import za.co.wethinkcode.examples.client.Client;
import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.world.Notifications;

import java.util.List;

public class EndGame extends Command {
    public EndGame(String name, Player player) {
        super(name, player);
    }

    @Override
    public boolean execute(Player player) {

        firstWorld.setMost_recent_action("ROBOT DEAD - GAME OVER - Game will disconnect");
        firstWorld.setNotificaton("end");
        drawInterface.reset(player.getTurtle());
        drawInterface.drawEndGame();

        Runnable r = new Notifications(firstWorld);
        Thread thread = new Thread(r);
        thread.start();

return false;
    }
}