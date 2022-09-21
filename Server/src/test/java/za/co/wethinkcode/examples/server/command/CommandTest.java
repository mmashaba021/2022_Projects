package za.co.wethinkcode.examples.server.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.examples.server.Manager;
import za.co.wethinkcode.examples.server.obstacles.SimpleObstacle;
import za.co.wethinkcode.examples.server.obstacles.SquareObstacle;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.Direction;
import za.co.wethinkcode.examples.server.world.FirstWorld;
import za.co.wethinkcode.examples.server.world.Position;
import za.co.wethinkcode.examples.server.world.State;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void getStateJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player();
        player.setLaunched(true);
        CreateJsonObjects createJsonObjects = new CreateJsonObjects();
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setShots(10);
        player.setSheild(10);
        player.setCurrentDirection(Direction.NORTH);
        player.setOperational_state(State.NORMAL);
        StateCommand test = new StateCommand(player);
        test.setCreateJsonObjectStateClass(createJsonObjects);
        createJsonObjects.setPlayer(player);
        test.setFirstWorld(firstWorld);
        test.execute(player);

        String jsonTostring = "{\"state\":{\"shields\":10,\"position\":[1.0,1.0],\"shots\":10,\"direction\":\"NORTH\",\"status\":\"NORMAL\"}}";
        assertEquals(jsonTostring, player.getResponse().toString());

    }



    @Test
    void FireCommandJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player();
        player.setLaunched(true);
        CreateJsonObjects createJsonObjects = new CreateJsonObjects();
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setShots(10);
        player.setSheild(10);
        Manager manager = new Manager();
        List players = new ArrayList<>();
        players.add(player);
        manager.setServers((ArrayList) players);
        player.setCurrentDirection(Direction.NORTH);
        player.setOperational_state(State.NORMAL);
        firstWorld.setLength(400.0);
        firstWorld.setWidth(200.0);
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();
         player.setFirstWorld(firstWorld);
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(2);
        simpleObstacle.setFirstWorld(firstWorld);
        player.setManager(manager);
        firstWorld.setObstacles(simpleObstacle.getObstacles());
        FireCommand test = new FireCommand(player);
        test.setFirstWorld(firstWorld);
        test.setCreateJsonObjectStateClass(createJsonObjects);
        createJsonObjects.setPlayer(player);
        test.execute(player);

        String jsonTostring = "{\"result\":\"OK\",\"data\":{\"message\":\"Miss\"},\"state\":{\"shields\":10,\"position\":[1.0,1.0],\"shots\":9,\"direction\":\"NORTH\",\"status\":\"NORMAL\"}}";
        assertEquals(jsonTostring, player.getResponse().toString());

    }

    @Test
    void getFireJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player();
        player.setLaunched(true);
        CreateJsonObjects createJsonObjects = new CreateJsonObjects();
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setShots(10);
        player.setSheild(10);
        Manager manager = new Manager();
        List players = new ArrayList<>();
        players.add(player);
        manager.setServers((ArrayList) players);
        player.setCurrentDirection(Direction.NORTH);
        player.setOperational_state(State.NORMAL);
        firstWorld.setLength(400.0);
        firstWorld.setWidth(200.0);
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();
        player.setFirstWorld(firstWorld);
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(2);
        simpleObstacle.setFirstWorld(firstWorld);
        player.setManager(manager);
        firstWorld.setObstacles(simpleObstacle.getObstacles());
        FireCommand test = new FireCommand(player);
        test.setFirstWorld(firstWorld);
        test.setCreateJsonObjectStateClass(createJsonObjects);
        createJsonObjects.setPlayer(player);
        test.execute(player);

        String jsonTostring = "{\"result\":\"OK\",\"data\":{\"message\":\"Miss\"},\"state\":{\"shields\":10,\"position\":[1.0,1.0],\"shots\":9,\"direction\":\"NORTH\",\"status\":\"NORMAL\"}}";
        assertEquals(jsonTostring, player.getResponse().toString());

    }

/*
places obstacles on either side of player and checks that both are found
one is east and the other is west
 */
    @Test
    void getLookJsonTwoObstacles() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player();
        player.setLaunched(true);
        CreateJsonObjects createJsonObjects = new CreateJsonObjects();
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setShots(10);
        player.setSheild(10);
        Manager manager = new Manager();
        List players = new ArrayList<>();
        players.add(player);
        manager.setServers((ArrayList) players);
        player.setCurrentDirection(Direction.NORTH);
        player.setOperational_state(State.NORMAL);
        firstWorld.setLength(400.0);
        firstWorld.setWidth(200.0);
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();
        firstWorld.setDefault_visibility(40);
        player.setFirstWorld(firstWorld);
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(2);
        simpleObstacle.setFirstWorld(firstWorld);
        player.setManager(manager);

        List obstacles1 = new ArrayList<>();

        SquareObstacle obs = new SquareObstacle(10, 1);
        SquareObstacle obs2 = new SquareObstacle(-10, 1);
        obstacles1.add(obs);
        obstacles1.add(obs2);

        firstWorld.setObstacles(obstacles1);
        LookCommand test = new LookCommand(player);
        test.setFirstWorld(firstWorld);
        test.setManager(manager);
        test.setCreateJsonObjectStateClass(createJsonObjects);
        createJsonObjects.setPlayer(player);
        test.execute(player);

        String jsonTostring = "{\"result\":\"OK\",\"data\":{\"objects\":[{},{},{\"type\":\"Obstacles\",\"distance\":\"7.0\",\"direction\":\"west\"},{\"type\":\"Obstacles\",\"distance\":\"9.0\",\"direction\":\"east\"},{},{},{},{}]},\"state\":{\"shields\":10,\"position\":[1.0,1.0],\"shots\":10,\"direction\":\"NORTH\",\"status\":\"NORMAL\"}}";
        assertEquals(jsonTostring, player.getResponse().toString());

    }

/*
checks that no obstacles are found
when beyond visibility
 */
    @Test
    void getLookJsonZEROObstacles() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player();
        player.setLaunched(true);
        CreateJsonObjects createJsonObjects = new CreateJsonObjects();
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setShots(10);
        player.setSheild(10);
        Manager manager = new Manager();
        List players = new ArrayList<>();
        players.add(player);
        manager.setServers((ArrayList) players);
        player.setCurrentDirection(Direction.NORTH);
        player.setOperational_state(State.NORMAL);
        firstWorld.setLength(400.0);
        firstWorld.setWidth(200.0);
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();
        firstWorld.setDefault_visibility(40);
        player.setFirstWorld(firstWorld);
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(2);
        simpleObstacle.setFirstWorld(firstWorld);
        player.setManager(manager);

        List obstacles1 = new ArrayList<>();

        SquareObstacle obs = new SquareObstacle(100, 1);
        SquareObstacle obs2 = new SquareObstacle(-100, 1);
        obstacles1.add(obs);
        obstacles1.add(obs2);

        firstWorld.setObstacles(obstacles1);
        LookCommand test = new LookCommand(player);
        test.setFirstWorld(firstWorld);
        test.setManager(manager);
        test.setCreateJsonObjectStateClass(createJsonObjects);
        createJsonObjects.setPlayer(player);
        test.execute(player);

        String jsonTostring = "{\"result\":\"OK\",\"data\":{\"objects\":[{},{},{},{},{},{},{},{}]},\"state\":{\"shields\":10,\"position\":[1.0,1.0],\"shots\":10,\"direction\":\"NORTH\",\"status\":\"NORMAL\"}}";
        assertEquals(jsonTostring, player.getResponse().toString());

    }
/*
checks that state is included when state command is run
 */
    @Test
    void OrientationCommand() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player();
        player.setLaunched(true);
        CreateJsonObjects createJsonObjects = new CreateJsonObjects();
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setShots(10);
        player.setSheild(10);
        Manager manager = new Manager();
        List players = new ArrayList<>();
        players.add(player);
        manager.setServers((ArrayList) players);
        player.setCurrentDirection(Direction.NORTH);
        player.setOperational_state(State.NORMAL);
        firstWorld.setLength(400.0);
        firstWorld.setWidth(200.0);
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();
        firstWorld.setDefault_visibility(40);
        player.setFirstWorld(firstWorld);
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(2);
        simpleObstacle.setFirstWorld(firstWorld);
        player.setManager(manager);

        List obstacles1 = new ArrayList<>();

        SquareObstacle obs = new SquareObstacle(100, 1);
        SquareObstacle obs2 = new SquareObstacle(-100, 1);
        obstacles1.add(obs);
        obstacles1.add(obs2);

        firstWorld.setObstacles(obstacles1);
        OrientationCommand test = new OrientationCommand(player);
        test.setFirstWorld(firstWorld);
        test.setManager(manager);
        test.setCreateJsonObjectStateClass(createJsonObjects);
        createJsonObjects.setPlayer(player);
        test.execute(player);

        String jsonTostring = "{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"shields\":10,\"position\":[1.0,1.0],\"shots\":10,\"direction\":\"NORTH\",\"status\":\"NORMAL\"}}";
        assertEquals(jsonTostring, player.getResponse().toString());

    }



    /*
checks that all fields are present in the json file of left command
 */
    @Test
    void LeftCommand() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player();
        player.setLaunched(true);
        CreateJsonObjects createJsonObjects = new CreateJsonObjects();
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setShots(10);
        player.setSheild(10);
        Manager manager = new Manager();
        List players = new ArrayList<>();
        players.add(player);
        manager.setServers((ArrayList) players);
        player.setCurrentDirection(Direction.NORTH);
        player.setOperational_state(State.NORMAL);
        firstWorld.setLength(400.0);
        firstWorld.setWidth(200.0);
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();
        firstWorld.setDefault_visibility(40);
        player.setFirstWorld(firstWorld);
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(2);
        simpleObstacle.setFirstWorld(firstWorld);
        player.setManager(manager);

        List obstacles1 = new ArrayList<>();

        SquareObstacle obs = new SquareObstacle(100, 1);
        SquareObstacle obs2 = new SquareObstacle(-100, 1);
        obstacles1.add(obs);
        obstacles1.add(obs2);

        firstWorld.setObstacles(obstacles1);
        TurnLeftCommand test = new TurnLeftCommand(player);
        test.setFirstWorld(firstWorld);
        test.setManager(manager);
        test.setCreateJsonObjectStateClass(createJsonObjects);
        createJsonObjects.setPlayer(player);
        test.execute(player);

        String jsonTostring = "{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"shields\":10,\"position\":[1.0,1.0],\"shots\":10,\"direction\":\"NORTH\",\"status\":\"NORMAL\"}}";
        assertEquals(jsonTostring, player.getResponse().toString());

    }


    /*
checks that all fields are present in the json file of right command
*/
    @Test
    void RightCommand() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player();
        player.setLaunched(true);
        CreateJsonObjects createJsonObjects = new CreateJsonObjects();
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setShots(10);
        player.setSheild(10);
        Manager manager = new Manager();
        List players = new ArrayList<>();
        players.add(player);
        manager.setServers((ArrayList) players);
        player.setCurrentDirection(Direction.NORTH);
        player.setOperational_state(State.NORMAL);
        firstWorld.setLength(400.0);
        firstWorld.setWidth(200.0);
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();
        firstWorld.setDefault_visibility(40);
        player.setFirstWorld(firstWorld);
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(2);
        simpleObstacle.setFirstWorld(firstWorld);
        player.setManager(manager);

        List obstacles1 = new ArrayList<>();

        SquareObstacle obs = new SquareObstacle(100, 1);
        SquareObstacle obs2 = new SquareObstacle(-100, 1);
        obstacles1.add(obs);
        obstacles1.add(obs2);

        firstWorld.setObstacles(obstacles1);
        TurnRightCommand test = new TurnRightCommand(player);
        test.setFirstWorld(firstWorld);
        test.setManager(manager);
        test.setCreateJsonObjectStateClass(createJsonObjects);
        createJsonObjects.setPlayer(player);
        test.execute(player);

        String jsonTostring = "{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"shields\":10,\"position\":[1.0,1.0],\"shots\":10,\"direction\":\"NORTH\",\"status\":\"NORMAL\"}}";
        assertEquals(jsonTostring, player.getResponse().toString());

    }

    /*
checks that all fields are present in the json file of Unrecognised command
*/
    @Test
    void UnrecognisedCommand() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player();
        player.setLaunched(true);
        CreateJsonObjects createJsonObjects = new CreateJsonObjects();
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setShots(10);
        player.setSheild(10);
        Manager manager = new Manager();
        List players = new ArrayList<>();
        players.add(player);
        manager.setServers((ArrayList) players);
        player.setCurrentDirection(Direction.NORTH);
        player.setOperational_state(State.NORMAL);
        firstWorld.setLength(400.0);
        firstWorld.setWidth(200.0);
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();
        firstWorld.setDefault_visibility(40);
        player.setFirstWorld(firstWorld);
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(2);
        simpleObstacle.setFirstWorld(firstWorld);
        player.setManager(manager);

        List obstacles1 = new ArrayList<>();

        SquareObstacle obs = new SquareObstacle(100, 1);
        SquareObstacle obs2 = new SquareObstacle(-100, 1);
        obstacles1.add(obs);
        obstacles1.add(obs2);

        firstWorld.setObstacles(obstacles1);
        UnrecognisedCommand test = new UnrecognisedCommand(player);
        test.setFirstWorld(firstWorld);
        test.setManager(manager);
        test.setCreateJsonObjectStateClass(createJsonObjects);
        createJsonObjects.setPlayer(player);
        test.execute(player);

        String jsonTostring = "{\"result\":\"ERROR\",\"data\":{\"message\":\"Unsupported command\"}}";
        assertEquals(jsonTostring, player.getResponse().toString());

    }


    /*
checks that all fields are present in the json file of Unrecognised command
*/
    @Test
    void UnavailableCommand() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player();
        player.setLaunched(true);
        CreateJsonObjects createJsonObjects = new CreateJsonObjects();
        Position position = new Position(1,1);
        player.setPosition(position);
        player.setShots(10);
        player.setSheild(10);
        Manager manager = new Manager();
        List players = new ArrayList<>();
        players.add(player);
        manager.setServers((ArrayList) players);
        player.setCurrentDirection(Direction.NORTH);
        player.setOperational_state(State.NORMAL);
        firstWorld.setLength(400.0);
        firstWorld.setWidth(200.0);
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();
        firstWorld.setDefault_visibility(40);
        player.setFirstWorld(firstWorld);
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(2);
        simpleObstacle.setFirstWorld(firstWorld);
        player.setManager(manager);

        List obstacles1 = new ArrayList<>();

        SquareObstacle obs = new SquareObstacle(100, 1);
        SquareObstacle obs2 = new SquareObstacle(-100, 1);
        obstacles1.add(obs);
        obstacles1.add(obs2);

        firstWorld.setObstacles(obstacles1);
        UnavailableCommand test = new UnavailableCommand(player);
        test.setFirstWorld(firstWorld);
        test.setManager(manager);
        test.setCreateJsonObjectStateClass(createJsonObjects);
        createJsonObjects.setPlayer(player);
        test.execute(player);

        String jsonTostring = "{\"state\":{\"shields\":10,\"position\":[1.0,1.0],\"shots\":10,\"direction\":\"NORTH\",\"status\":\"NORMAL\"},\"message\":\"state not NORMAL\"}";
        assertEquals(jsonTostring, player.getResponse().toString());

    }

    public JSONObject createJson(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonMessage = new JSONObject();
        JSONObject jsonState = new JSONObject();



        JSONArray positionArray = new JSONArray();

        positionArray.add(10.0);
        positionArray.add(20.0);

        jsonMessage.put( "message", "Done");


        jsonState.put( "position", positionArray);
        jsonState.put("direction","NORTH");
        jsonState.put( "shields", 10.0);
        jsonState.put("shots", 10.0);
        jsonState.put("status", "Normal");

        jsonMain.put("result", "OK");
        jsonMain.put("data", jsonMessage);
        jsonMain.put("state", jsonState);


        return jsonMain;
    }
}