package za.co.wethinkcode.examples.server.command;

import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.State;

import java.util.concurrent.TimeUnit;

public class Reloading implements Runnable {
    Player player;
    public Reloading(Player player1) {
        player = player1;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(player.getReloadTime()*1000);
        } catch (Exception k) {
        }
          player.reload_shots();
        player.setOperational_state(State.NORMAL);
    }



}
