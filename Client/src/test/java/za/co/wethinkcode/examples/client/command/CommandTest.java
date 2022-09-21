package za.co.wethinkcode.examples.client.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.turtle2.StdDraw2;
import za.co.wethinkcode.examples.client.turtle2.Turtle2;
import za.co.wethinkcode.examples.client.world.DrawInterface;
import za.co.wethinkcode.examples.client.world.FirstWorld;
import za.co.wethinkcode.examples.client.world.Position;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void getForwardName() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        ForwardCommand test = new ForwardCommand("100",player);
        assertEquals("forward", test.getName());
        assertEquals("100", test.getArgument());
    }


    @Test
    void getBackName() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        BackCommand test = new BackCommand("100",player);
        assertEquals("back", test.getName());
        assertEquals("100", test.getArgument());
    }

    @Test
    void getLaunchName() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        LaunchCommand test = new LaunchCommand("100",player);
        assertEquals("launch", test.getName());
        assertEquals("100", test.getArgument());
    }


    @Test
    void getLookName() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        LookCommand test = new LookCommand(player);
        assertEquals("look", test.getName());

    }


    @Test
    void getReloadName() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        ReloadCommand test = new ReloadCommand("",player);
        assertEquals("reload", test.getName());

    }



    @Test
    void getFireName() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        FireCommand test = new FireCommand(player);
        assertEquals("fire", test.getName());

    }
    /*
    *checks that update state class
     *  updates client variables
     */
    @Test
    void getUpdateState() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);

        String server_response = createSuccessfulForwardJson();


        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(server_response);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        player.setJsonObject(jsonObject);

        String result = (String) jsonObject.get("result");
        JSONObject state = (JSONObject) jsonObject.get("state");
        JSONObject data = (JSONObject) jsonObject.get("data");

        if (result.equals("OK")) {

            UpdatePlayerState update = new UpdatePlayerState();
            update.setPlayer(player);
            update.updatePlayerState(state);

        }
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        player.setTurtle(turtle);
        player.setColor(Color.red);

        assertEquals(10.0, player.getPosition().getX());

    }



    /*
     *checks that forward
     *  updates state
     *
     *checks that forward
     *  updates state
     */
    @Test
    void getForwardSuccessfulJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        parseJson(player,createSuccessfulForwardJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("forward");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        ForwardCommand test = new ForwardCommand("10",player);
        test.setFirstWorld(firstWorld);


        test.setDrawInterface(drawInterface);
        test.execute(player);
        assertEquals(10.0, player.getPosition().getX());

    }


    /*
     *A json response has a message "missed"
     * when fire is run, a messsage must be output
     * to convey that message
     *
     *
     *checks that message is correct
     * for "fire missed"
     *
     */
    @Test
    void getFireMissJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        parseJson(player,createFireMissJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("forward");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        FireCommand test = new FireCommand(player);
        test.setFirstWorld(firstWorld);


        test.setDrawInterface(drawInterface);
        test.execute(player);
        assertEquals("FIRED A SHOT:THE SHOT MISSED", firstWorld.getMost_recent_action());

    }


    /*
     *
     *
     *Checks that back command is successful
     *
     *when back command is run the state should be update
     *and position should be x steps lower than the previous state
     *
     *A message expressing the action has taken place must also be outputted and stored
     */
    @Test
    void backMovementSuccessfulJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        parseJson(player,createSuccessfulForwardJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("forward");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        BackCommand test = new BackCommand("10",player);
        test.setFirstWorld(firstWorld);


        test.setDrawInterface(drawInterface);
        test.execute(player);
        assertEquals(10.0, player.getPosition().getX());
        assertEquals("Robot moved 10 steps back", firstWorld.getMost_recent_action());


    }

    /*
    checks that obstructed is returned from String Most recent actions
    when forward movement is used
     */
    @Test
    void getForwardFailedJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        parseJson(player,createObstructedForwardJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("forward");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        ForwardCommand test = new ForwardCommand("10",player);
        test.setFirstWorld(firstWorld);


        test.setDrawInterface(drawInterface);

        //shuts window down after 3 seconds
        long start = System.currentTimeMillis();
        long end = start +  3000;
        while (System.currentTimeMillis() < end) {
            test.execute(player);
        }

        assertEquals("ROBOT OBSTRUCTED - try a different move", firstWorld.getMost_recent_action());

    }

    /*
        this checks that obstructed is returned from String Most recent actions
        when back movement is used
         */
    @Test
    void getBackMovementFailedJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        parseJson(player,createObstructedForwardJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("back");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        BackCommand test = new BackCommand("10",player);
        test.setFirstWorld(firstWorld);


        test.setDrawInterface(drawInterface);

        //shuts window down after 3 seconds
        long start = System.currentTimeMillis();
        long end = start +  3000;
        while (System.currentTimeMillis() < end) {
            test.execute(player);
        }

        assertEquals("ROBOT OBSTRUCTED - try a different move", firstWorld.getMost_recent_action());

    }
    /*
        checks that direction is changed to west when leftCommand is used
         */
    @Test
    void getTurnedLeftJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        player.setDirection("NORTH");
        parseJson(player,createSuccessfulForwardJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("back");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        TurnLeftCommand test = new TurnLeftCommand(player);
        test.setFirstWorld(firstWorld);


        test.setDrawInterface(drawInterface);
        test.execute(player);


        assertEquals("WEST", player.getDirection());

    }

    /*
     checks that direction is changed to east when leftCommand is used
      */
    @Test
    void getTurnedRightJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        player.setDirection("NORTH");
        parseJson(player,createTurnedRightJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("back");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        TurnRightCommand test = new TurnRightCommand(player);
        test.setFirstWorld(firstWorld);


        test.setDrawInterface(drawInterface);

        test.execute(player);
        assertEquals("EAST", player.getDirection());
        assertEquals("Robot turned right", firstWorld.getMost_recent_action());

    }

    @Test
    void getStateJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        player.setDirection("NORTH");
        parseJson(player,createTurnedRightJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("back");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        StateCommand test = new StateCommand("",player);
        test.setFirstWorld(firstWorld);


        test.setDrawInterface(drawInterface);
        test.execute(player);


        assertEquals("EAST", player.getDirection());
        assertEquals(10, player.getPosition().getX());
        assertEquals(20, player.getPosition().getY());
        assertEquals(10, player.getSheild());
        assertEquals(5, player.getShots());
        assertEquals("NORMAL", player.getOperational_state());


    }

/*
checks that state hasnt changed since command was not recognised
there should be no changes
 */
    @Test
    void UnrecognisedCommandJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        player.setDirection("NORTH");
        parseJson(player,createTurnedRightJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("back");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        StateCommand state = new StateCommand("",player);
        state.setFirstWorld(firstWorld);

        //first state command is run to set state
        state.setDrawInterface(drawInterface);
        state.execute(player);

        //then the unrecognised command is run to test state didnt change/
        parseJson(player, UrecognisedCommandResponse());
        UnrecognisedCommand unrecognisedCommand= new UnrecognisedCommand(player);
        unrecognisedCommand.setFirstWorld(firstWorld);


        unrecognisedCommand.setDrawInterface(drawInterface);
        unrecognisedCommand.execute(player);

        assertEquals("EAST", player.getDirection());
        assertEquals(10, player.getPosition().getX());
        assertEquals(20, player.getPosition().getY());
        assertEquals(10, player.getSheild());
        assertEquals("NORMAL", player.getOperational_state());
        assertEquals("Invalid command. Please choose a valid command: press \\ for help", firstWorld.getMost_recent_action());


    }
    @Test
    void getRepairJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        player.setDirection("NORTH");
        parseJson(player,createTurnedRightJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("back");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        RepairCommand test = new RepairCommand("", player);
        test.setFirstWorld(firstWorld);

        test.setDrawInterface(drawInterface);
        test.execute(player);


        assertEquals("EAST", player.getDirection());
        assertEquals("Robot repaired. Shield at maximum", firstWorld.getMost_recent_action());

    }

    @Test
    void getReloadJson() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        player.setDirection("NORTH");
        parseJson(player,createTurnedRightJson());
        DrawInterface drawInterface = new DrawInterface();
        Turtle2 turtle = new Turtle2(10.0,10.0,90.0);
        Position position = new Position(1,1);
        player.setPosition(position);
        drawInterface.setPlayer(player);
        firstWorld.setRobot_image("ring2.png");
        drawInterface.setFirstWorld(firstWorld);
        firstWorld.setMost_recent_action("back");
        StdDraw2 stdDraw2 = new StdDraw2();
        firstWorld.setStdDraw(stdDraw2);
        player.setShots(10);
        player.setSheild(10);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        ReloadCommand test = new ReloadCommand("", player);
        test.setFirstWorld(firstWorld);


        test.setDrawInterface(drawInterface);

        test.execute(player);


        assertEquals("EAST", player.getDirection());
        assertEquals("Robot reloaded. Max amo available", firstWorld.getMost_recent_action());

    }


    private void parseJson(Player player,String server_response) {



        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(server_response);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        player.setJsonObject(jsonObject);
    }



// json for foward command
    public String createSuccessfulForwardJson(){
String forward ="{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"shields\":10,\"position\":[10.0,20.0],\"shots\":5,\"direction\":\"WEST\",\"status\":\"NORMAL\"}}";
        return forward;
    }



    public String createFireMissJson(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonMessage = new JSONObject();
        JSONObject jsonState = new JSONObject();
        JSONArray positionArray = new JSONArray();

        positionArray.add(10.0);
        positionArray.add(20.0);

        jsonMessage.put( "message", "Miss");


        jsonState.put( "position", positionArray);
        jsonState.put("direction","WEST");
        jsonState.put( "shields", 10);
        jsonState.put("shots", 10);
        jsonState.put("status", "Normal");

        jsonMain.put("result", "OK");
        jsonMain.put("data", jsonMessage);
        jsonMain.put("state", jsonState);

String fire = "{\"result\":\"OK\",\"data\":{\"message\":\"Miss\"},\"state\":{\"shields\":10,\"position\":[10.0,20.0],\"shots\":4,\"direction\":\"WEST\",\"status\":\"NORMAL\"}}";
        return fire;
    }

    // json for foward command
    public String createTurnedRightJson(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonMessage = new JSONObject();
        JSONObject jsonState = new JSONObject();
        JSONArray positionArray = new JSONArray();

        positionArray.add(10.0);
        positionArray.add(20.0);

        jsonMessage.put( "message", "Done");


        jsonState.put( "position", positionArray);
        jsonState.put("direction","EAST");
        jsonState.put( "shields", 10);
        jsonState.put("shots", 10);
        jsonState.put("status", "Normal");

        jsonMain.put("result", "OK");
        jsonMain.put("data", jsonMessage);
        jsonMain.put("state", jsonState);

        String turn ="{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"shields\":10,\"position\":[10.0,20.0],\"shots\":5,\"direction\":\"EAST\",\"status\":\"NORMAL\"}}";

        return turn;
    }


    public String createObstructedForwardJson(){

        JSONObject jsonMain = new JSONObject();
        JSONObject jsonMessage = new JSONObject();
        JSONObject jsonState = new JSONObject();
        JSONArray positionArray = new JSONArray();

        positionArray.add(10.0);
        positionArray.add(20.0);

        jsonMessage.put(  "message", "Obstructed");


        jsonState.put( "position", positionArray);
        jsonState.put("direction","NORTH");
        jsonState.put( "shields", 10);
        jsonState.put("shots", 10);
        jsonState.put("status", "Normal");

        jsonMain.put("result", "OK");
        jsonMain.put("data", jsonMessage);
        jsonMain.put("state", jsonState);

        String obstructed ="{\"result\":\"OK\",\"data\":{\"message\":\"Obstructed\"},\"state\":{\"shields\":10,\"position\":[10.0,20.0],\"shots\":4,\"direction\":\"NORTH\",\"status\":\"NORMAL\"}}";

        return obstructed;
    }

    public String UrecognisedCommandResponse() {
        JSONObject jsonMain = new JSONObject();
        JSONObject data = new JSONObject();


        data.put("message", "Unsupported command");
        jsonMain.put("result", "ERROR");
        jsonMain.put("data", data);
        return jsonMain.toString();
    }

    public JSONObject createJson2(){

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