package za.co.wethinkcode.examples.client.player;

import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.client.turtle2.StdDraw2;
import za.co.wethinkcode.examples.client.turtle2.Turtle2;
import za.co.wethinkcode.examples.client.world.Position;
import za.co.wethinkcode.examples.client.world.FirstWorld;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.awt.Color.*;

/*
Manages the player variables
 */
public class Player {

    private String status;
    private String robotName;
    private Position position ;
    private Position CENTRE = new Position(0,0);
    private double shot_distance;
    private  Turtle2 turtle;
    private FirstWorld firstWorld;
    private boolean player_launched = false;
    double x_pos;
    double y_pos;
    RobotType robotType;
    String operational_state = "NORMAL";
    double sheild;
    double shots;
    JSONObject jsonObject;
    private Color color;
    String direction = "NORTH";
    List actions_list = new ArrayList<>();
    private String teamChoice;
    private String winner;




    public Player(FirstWorld firstWorld){

        position = new Position(0,0);
        this.firstWorld = firstWorld;

    }

    public RobotType getRobotType() {
        return robotType;
    }

    public void setRobotType(RobotType robotType) {
        this.robotType = robotType;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public double getShot_distance() {
        return shot_distance;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public void setShot_distance(double shot_distance) {
        this.shot_distance = shot_distance;
    }

    public List getActions_list() {
        return actions_list;
    }

    public void setActions_list(List actions_list) {
        this.actions_list = actions_list;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public  void setTurtle(Turtle2 turtle2) {
        turtle = turtle2;
    }

    public String getOperational_state() {
        return operational_state;
    }

    public void setOperational_state(String operational_state1) {
        operational_state = operational_state1;
    }

    public double getShots() {
        return shots;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setShots(double shots) {
        this.shots = shots;
    }

    public void setSheild(double sheild) {
        this.sheild = sheild;
    }

    public double getSheild() {
        return sheild;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void launchRobot(){
       setPlayerPosition();
       createTurtle();
       firstWorld.launch_robot(turtle,black);
       addToActionsLog();

    }

    private void createTurtle() {
        StdDraw2.setPenColor(white);
        setColor(black);
        turtle = new Turtle2(0.5+x_pos,y_pos+0.5,90.0D);
        turtle.show();
    }

    private void addToActionsLog() {
        String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));

        actions_list.add("");
        actions_list.add("("+timeStamp+")"+"launched robot");
    }

    private void setPlayerPosition() {
        x_pos = position.getX()*0.002;
        y_pos =position.getY()*0.002;
    }

    public boolean isPlayer_launched() {
        return player_launched;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPlayer_launched(Boolean launched) {
        player_launched = launched;
    }


    public String getRobotName(){
        return robotName;
    }


    public Position getPosition() {
        return this.position;
    }

    public Turtle2 getTurtle(){
        return turtle;
    }

    public String toString() {
        return "[" + this.position.getX() + "," + this.position.getY() + "] "
                + this.robotName + "> " + this.status;
    }


}

