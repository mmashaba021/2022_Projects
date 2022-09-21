package za.co.wethinkcode.examples.server.player;

import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.Manager;
import za.co.wethinkcode.examples.server.GameServer;
import za.co.wethinkcode.examples.server.obstacles.LookObstacle;
import za.co.wethinkcode.examples.server.obstacles.Obstacle;
import za.co.wethinkcode.examples.server.obstacles.SquareObstaclePlayers;
import za.co.wethinkcode.examples.server.world.*;
import org.turtle.Turtle;
import org.turtle.StdDraw;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static java.awt.Color.*;

public class Player {
    private Direction currentDirection;
    private String status;
    private String robotName;
    private Position position ;
    private Position CENTRE = new Position(0,0);
    private GameServer server;
    private  Turtle turtle;
    private FirstWorld firstWorld;
    private Color color;
    private JSONObject response;
    private boolean launched = false;
    double x_random_pos;
    double y_random_pos;
    boolean message_success = true;
    State operational_state = State.NORMAL;

    //configurations
    RobotType type;
    int sheild ;
    int shots;
    int shot_distance;

    String team;
    Manager manager;


     Position Border_TOP_LEFT = new Position(-100,200);
     Position Border_BOTTOM_RIGHT = new Position(100,-200);
    ArrayList <String> operational_states = new ArrayList<String> (Arrays.asList("RELOAD", "REPAIR", "NORMAL", "DEAD"));

    public Player(String robotNamelocal, GameServer simpleServer, FirstWorld firstWorld){
        currentDirection = Direction.NORTH;
        position = new Position(0,0);
        this.robotName = robotNamelocal;
        this.server = simpleServer;
        this.firstWorld = firstWorld;

    }

    public Player() {

    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setBorder_BOTTOM_RIGHT(Position border_BOTTOM_RIGHT) {
        Border_BOTTOM_RIGHT = border_BOTTOM_RIGHT;
    }

    public void setBorder_TOP_LEFT(Position border_TOP_LEFT) {
        Border_TOP_LEFT = border_TOP_LEFT;
    }

    public  Position getBorder_BOTTOM_RIGHT() {
        return Border_BOTTOM_RIGHT;
    }

    public RobotType getType() {
        return type;
    }

    public void setFirstWorld(FirstWorld firstWorld) {
        this.firstWorld = firstWorld;
    }

    public void setType(RobotType type) {
        this.type = type;
    }

    public  Position getBorder_TOP_LEFT() {
        return Border_TOP_LEFT;
    }

    public int getShots(){
        return  shots;
    }

    public String getTeam(){
        return team;
    }
    public int getRepairTime() {
        return firstWorld.getDefault_repair_time();
    }

    public int getReloadTime() {
        return firstWorld.getDefault_reload_time();
    }


    public void resetShield(){
        sheild = firstWorld.getDefault_sheild();
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getSheild() {
        return sheild;
    }

    public void setSheild(int sheild) {
        this.sheild = sheild;
    }

    public double getDistance() {
        return shot_distance;
    }

    public void setDistance(int distance) {
        this.shot_distance = distance;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public void reload_shots(){
        if (type.equals(RobotType.ROBOT_LONG_RANGE)){
        shots = firstWorld.getDefault_shots_amount_type_longrange();
        }
        else{
            shots = firstWorld.getDefault_shots_amount_type_shortrange();
        };
    }

    public JSONObject getResponse(){
        return this.response;
    }

    public State getOperational_state() {
        return operational_state;
    }

    public void setMessage_success(boolean success){
        message_success = success;
    }

    public void setTurtle(Turtle turtle1){
        turtle = turtle1;
    }
    public boolean getMessageSuccess(){
        return message_success;
    }
    public void launch(){
        launched = true;
        Random random ;
        int random_number;
        randomNumber();
        double x = x_random_pos*0.002;
        double y = y_random_pos*0.002;
        turtle = new Turtle(0.5+x,0.5+y,90.0D);
        position = new Position(x_random_pos,y_random_pos);

        String[] robot_info = robotName.split(" ",2);
        team = robot_info[0];
        this.robotName = robot_info[1];


        manager.addPlayers(this);

        List<java.awt.Color> colors = Arrays.asList( blue, red, green, orange, yellow, black);
        random = new Random();
        random_number = random.nextInt(5);


        Color color1 = colors.get(random_number);
        turtle.setColor(color1);
        this.color =color1;
        StdDraw.filledCircle(0.5+x,0.5+y,0.01);
        StdDraw.show();
    }

    public boolean isLaunched() {
        return launched;
    }

    public Color getColor() {
        return color;
    }

    public void randomNumber() {

        LookObstacle lookObstacle = new LookObstacle();

        try {
            lookObstacle.setObstacles(firstWorld.getObstacles());

        } catch (IOException e) {
            e.printStackTrace();
        }


        boolean check_random_position = false;

        while (!check_random_position) {

            Random random = new Random();

            int random_numberx = random.nextInt((int) firstWorld.getWidth() );
            random_numberx = random_numberx - (int) (firstWorld.getWidth() / 2) ;

            int random_numbery = random.nextInt((int) firstWorld.getLength());
            random_numbery = random_numbery - (int) (firstWorld.getLength() / 2) ;

            x_random_pos = random_numberx;
            y_random_pos = random_numbery;

            Position newPosition = new Position(x_random_pos, y_random_pos);

            if (!lookObstacle.blocksPosition(newPosition)){
               check_random_position =  true;

            }
        }
    }

    public String getRobotName(){
        return robotName;
    }
    public GameServer getServer() {
        return server;
    }

    public boolean updatePosition(double nrSteps) {
        double newX = this.position.getX();
        double newY = this.position.getY();

        if (Direction.NORTH.equals(currentDirection)) {
            newY = newY + nrSteps;
        }
        if (Direction.EAST.equals(currentDirection)) {
            newX = newX + nrSteps;

        }
        if (Direction.SOUTH.equals(currentDirection)) {
            newY = newY - nrSteps;
        }
        if (Direction.WEST.equals(currentDirection)) {
            newX = newX - nrSteps;
        }
        Position newPosition = new Position(newX, newY);
        if (!newPosition.isIn(firstWorld.getTOP_LEFT(),firstWorld.getBOTTOM_RIGHT())   ) {

            this.setStatus("Sorry, I cannot go outside the world's limits. It is an unknown realm");
            return  false;
        }

        LookObstacle lookObstacle = new LookObstacle();

        try {
            lookObstacle.setObstacles(firstWorld.getObstacles());

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (lookObstacle.blocksPath(position,newPosition).size()>0){
            return  false;

        }

        LookObstacle lookObstacle_forPlayers = new LookObstacle();
        lookObstacle_forPlayers.setManager(manager);
        List players= manager.getPlayers();
        List<Obstacle> player_sqaure = new ArrayList<>();

        for (int i=0; i< players.size();i++){
            Player play = (Player) players.get(i);
            if (!play.equals(this)){
                Position player_position = play.getPosition();


                SquareObstaclePlayers squareObstacle = new SquareObstaclePlayers(player_position.getX(),player_position.getY());
                player_sqaure.add(squareObstacle);}
        }


        try {
            lookObstacle_forPlayers.setObstacles(player_sqaure);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lookObstacle_forPlayers.blocksPathPlayers(position,newPosition).size()>0)    {

            this.setStatus("");
            return  false;
        }

        this.position = newPosition;
        return true;
    }


    public Map<String, SquareObstaclePlayers> fire(double nrSteps) {
        double newX = this.position.getX();
        double newY = this.position.getY();

        if (Direction.NORTH.equals(currentDirection)) {
            newY = newY + nrSteps;
        }
        if (Direction.EAST.equals(currentDirection)) {
            newX = newX + nrSteps;

        }
        if (Direction.SOUTH.equals(currentDirection)) {
            newY = newY - nrSteps;
        }
        if (Direction.WEST.equals(currentDirection)) {
            newX = newX - nrSteps;
        }
        Position newPosition = new Position(newX, newY);


        LookObstacle lookObstacle = new LookObstacle();
        try {
            lookObstacle.setObstacles(firstWorld.getObstacles());
        } catch (IOException e) {
            e.printStackTrace();
        }



        LookObstacle lookObstacle_forPlayers = new LookObstacle();
        List players= manager.getPlayers();
        List<Obstacle> player_sqaure = new ArrayList<>();

        for (int i=0; i< players.size();i++){
            Player play = (Player) players.get(i);
            if (!play.equals(this)){
                Position player_position = play.getPosition();


                SquareObstaclePlayers squareObstacle = new SquareObstaclePlayers(player_position.getX(),player_position.getY());
                squareObstacle.setManager(manager);
                player_sqaure.add(squareObstacle);}
        }


        try {
            lookObstacle_forPlayers.setObstacles(player_sqaure);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lookObstacle_forPlayers.firePathPlayers(position,newPosition);





    }

    public void setOperational_state(State operational_state) {
        this.operational_state = operational_state;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position1) {
        position = position1;
    }

    public Turtle getTurtle(){
        return turtle;
    }
    public void reset() {
        position = CENTRE;
        currentDirection = Direction.NORTH;

    }

    public void updateDirection(boolean turnRight) {
        if(turnRight){
            int index = Direction.valueOf(String.valueOf(currentDirection)).ordinal();
            index = index +1;

            if (index>3){
                index = 0;
            }

            setCurrentDirection(Direction.values()[index]);

        }
        else{
            int index = Direction.valueOf(String.valueOf(currentDirection)).ordinal();

            index = index -1;

            if (index<0){
                index = 3;
            }

            setCurrentDirection(Direction.values()[index]);

        }
    }


    public void setCurrentDirection(Direction dir){
        currentDirection = dir;
    }

    public Direction getCurrentDirection() {

        return  currentDirection;
    }

    public String toString() {
        return "[" + this.position.getX() + "," + this.position.getY() + "] "
                + this.robotName + "> " + this.status;
    }

    public FirstWorld getFirstWorld() {
        return firstWorld;
    }
}

