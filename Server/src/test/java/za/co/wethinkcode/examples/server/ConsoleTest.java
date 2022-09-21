package za.co.wethinkcode.examples.server;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.examples.server.obstacles.SimpleObstacle;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.Direction;
import za.co.wethinkcode.examples.server.world.FirstWorld;
import za.co.wethinkcode.examples.server.world.Position;
import za.co.wethinkcode.examples.server.world.State;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    @Test
    void RobotsCommand() {
        Manager manager = new Manager();
        Player player = new Player();
        List players = new ArrayList<>();
        players.add(player);
        Socket socket = new Socket();
        StartProgram startProgram = new StartProgram();

        FirstWorld firstWorld =new FirstWorld();
        manager.setFirstWorld(firstWorld);
        manager.setServers((ArrayList) players);
        player.setRobotName("Jim");
        GameServer gameServer = null;
        try {
           gameServer = new GameServer(socket, manager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setCurrentDirection(Direction.NORTH);
        player.setSheild(5);
        player.setShots(7);
        player.setOperational_state(State.NORMAL);


        gameServer.setManager(manager);
        startProgram.setManager(manager);
        startProgram.setFirstWorld(firstWorld);
        Console console = new Console();
        console.setServers((ArrayList<Runnable>) players);
        console.setMultiServers(startProgram);
        console.robots();
        assertEquals("There are 1 robots in the robot world", console.getOutput());
    }



    @Test
    void DumpCommand() {
        Manager manager = new Manager();
        Player player = new Player();
        List players = new ArrayList<>();
        players.add(player);
        Socket socket = new Socket();
        StartProgram startProgram = new StartProgram();

        FirstWorld firstWorld =new FirstWorld();
        manager.setFirstWorld(firstWorld);

        manager.setServers((ArrayList) players);
        player.setRobotName("Jim");
        GameServer gameServer = null;
        try {
            gameServer = new GameServer(socket, manager);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Position position = new Position(1,1);
        player.setPosition(position);
        player.setCurrentDirection(Direction.NORTH);
        player.setSheild(5);
        player.setShots(7);
        player.setOperational_state(State.NORMAL);
        SimpleObstacle simpleObstacle = new SimpleObstacle();


        gameServer.setManager(manager);
        startProgram.setManager(manager);
        startProgram.setFirstWorld(firstWorld);
        Console console = new Console();
        console.setServers((ArrayList<Runnable>) players);
        console.setMultiServers(startProgram);
        firstWorld.setLength(100.0);
        firstWorld.setWidth(100.0);
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();
        simpleObstacle.setObs_amount(5);
        simpleObstacle.setFirstWorld(firstWorld);
        firstWorld.setObstacles(simpleObstacle.getObstacles());
        console.dump();
        assertEquals("There are 5 obstacles", console.getOutput());
    }

}