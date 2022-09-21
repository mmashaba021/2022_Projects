package za.co.wethinkcode.examples.client.player;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.examples.client.command.LaunchCommand;
import za.co.wethinkcode.examples.client.command.StateCommand;
import za.co.wethinkcode.examples.client.turtle2.StdDraw2;
import za.co.wethinkcode.examples.client.turtle2.Turtle2;
import za.co.wethinkcode.examples.client.world.DrawInterface;
import za.co.wethinkcode.examples.client.world.FirstWorld;
import za.co.wethinkcode.examples.client.world.Position;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


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


    @Test
    void CheckRobotTypeLongRange() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        player.setDirection("NORTH");
        parseJson(player,LaunchRobotJson());
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
        player.setRobotType(RobotType.ROBOT_LONG_RANGE);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        LaunchCommand test = new LaunchCommand("long_range",player);
        test.setFirstWorld(firstWorld);

        test.setDrawInterface(drawInterface);
        test.execute(player);


        assertEquals(5, player.getShots());
        assertEquals(30, player.getShot_distance());

    }


    @Test
    void CheckRobotTypeShortRange() {
        FirstWorld firstWorld = new FirstWorld();
        Player player = new Player(firstWorld);
        player.setDirection("NORTH");
        parseJson(player,LaunchRobotJson());
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
        player.setRobotType(RobotType.ROBOT_SHORT_RANGE);
        player.setTurtle(turtle);
        player.setColor(Color.red);
        firstWorld.setPlayer(player);
        firstWorld.setDrawInterface(drawInterface);
        LaunchCommand test = new LaunchCommand("short_range",player);
        test.setFirstWorld(firstWorld);

        test.setDrawInterface(drawInterface);
        test.execute(player);


        assertEquals(10, player.getShots());
        assertEquals(20, player.getShot_distance());

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
    public String LaunchRobotJson(){
        String forward ="{\"result\":\"OK\",\"data\":{\"repair\":3,\"shields\":10,\"reload\":3,\"visibility\":30,\"position\":[-8.0,35.0]},\"state\":{\"shields\":10,\"position\":[-8.0,35.0],\"shots\":5,\"direction\":\"NORTH\",\"status\":\"NORMAL\"}}";
        return forward;
    }

    public String createTurnedRightJson(){

        String turn ="{\"result\":\"OK\",\"data\":{\"message\":\"Done\"},\"state\":{\"shields\":10,\"position\":[10.0,20.0],\"shots\":5,\"direction\":\"EAST\",\"status\":\"NORMAL\"}}";

        return turn;
    }

}