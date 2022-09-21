package za.co.wethinkcode.examples.server.world;
import org.turtle.Turtle;
import org.turtle.StdDraw;
import za.co.wethinkcode.examples.server.obstacles.Obstacle;
import za.co.wethinkcode.examples.server.obstacles.SquareObstacle;
import za.co.wethinkcode.examples.server.obstacles.SquareObstaclePlayers;
import za.co.wethinkcode.examples.server.player.Player;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.awt.Color.*;

public class FirstWorld {
    static double width;
    static double length;
    static Position TOP_LEFT;
    static Position BOTTOM_RIGHT;
    List obstacles;
    double size = 0.1D;
    static double a0 = 90.0D;
    static Turtle drawing_pen = new Turtle(0.3D, 0.1D, a0);
    double maxPlayers = 12;
    static Position Border_TOP_LEFT;
    static Position Border_BOTTOM_RIGHT;
    int default_repair_time ;
    int default_reload_time;
    int default_visibility;
    int default_reload ;
    int default_shots_amount_type_shortrange;
    int default_shots_amount_type_longrange;
    int default_shot_distance_type_longrange;
    int default_shot_distance_type_shortrange;
    int default_sheild = 10;

    public int getDefault_shots_amount_type_longrange() {
        return default_shots_amount_type_longrange;
    }

    public void setDefault_shots_amount_type_longrange(int default_shots_amount_type_longrange) {
        this.default_shots_amount_type_longrange = default_shots_amount_type_longrange;
    }

    public int getDefault_shots_amount_type_shortrange() {
        return default_shots_amount_type_shortrange;
    }

    public void setDefault_shots_amount_type_shortrange(int default_shots_amount_type_shortrange) {
        this.default_shots_amount_type_shortrange = default_shots_amount_type_shortrange;
    }

    public double getDefault_shot_distance_type_longrange() {
        return default_shot_distance_type_longrange;
    }

    public void setDefault_shot_distance_type_longrange(int default_shot_distance_type_longrange) {
        this.default_shot_distance_type_longrange = default_shot_distance_type_longrange;
    }

    public double getDefault_shot_distance_type_shortrange() {
        return default_shot_distance_type_shortrange;
    }

    public void setDefault_shot_distance_type_shortrange(int default_shot_distance_type_shortrange) {
        this.default_shot_distance_type_shortrange = default_shot_distance_type_shortrange;
    }

    public void setDefault_repair_time(int default_repair_time) {
        this.default_repair_time = default_repair_time;
    }

    public void setDefault_reload_time(int default_reload_time) {
        this.default_reload_time = default_reload_time;
    }

    public void setDefault_visibility(int default_visibility) {
        this.default_visibility = default_visibility;
    }

    public void setDefault_reload(int default_reload) {
        this.default_reload = default_reload;
    }

    public void setDefault_sheild(int default_sheild) {
        this.default_sheild = default_sheild;
    }

    public int getDefault_visibility() {
        return default_visibility;
    }

    public int getDefault_reload() {
        return default_reload;
    }

    public int getDefault_sheild() {
        return default_sheild;
    }

    public void setObstacles(List obstacles){
        this.obstacles = obstacles;
    }
    public  void setBottomRight() {
        BOTTOM_RIGHT  = new Position(width/2,-length/2);;
    }

    public int getDefault_repair_time(){
        return  default_repair_time;
    }

    public int getDefault_reload_time() {
        return default_reload_time;
    }

    public void setTopLeft() {
        TOP_LEFT = new Position(-width/2,length/2);;
    }

    public  void setLength(double length) {
        FirstWorld.length = length;
    }

    public  void setWidth(double width) {
        FirstWorld.width = width;
    }

    public  double getLength() {
        return length;
    }

    public  double getWidth() {
        return width;
    }

    public double getMaxPlayers() {
        return maxPlayers;
    }

    public static Position getBOTTOM_RIGHT() {
        return BOTTOM_RIGHT;
    }

    public static Position getTOP_LEFT() {
        return TOP_LEFT;
    }

    public List getObstacles() {
        return obstacles;
    }

    public static void makeWorld(){

        StdDraw.rectangle(0.5,0.5,0.2,0.4);


    }

    public static void drawObstacles(List obstacles) {
        double xCentre = 0.5;
        double yCentre = 0.5;
        StdDraw.enableDoubleBuffering();
        if (obstacles.size() > 0) {

            for (int i = 0; i < obstacles.size(); i++) {
                Object obstacle_object = obstacles.get(i);
                SquareObstacle squareObstacle = (SquareObstacle) obstacle_object;


                double x = squareObstacle.getBottomLeftX();
                double y = squareObstacle.getBottomLeftY();


               Position Border_TOP_LEFT1 = new Position(-100,200);
                Position Border_BOTTOM_RIGHT1 = new Position(100,-200);


                if(squareObstacle.obstacle_inbounds(Border_TOP_LEFT1,Border_BOTTOM_RIGHT1)){
                    double x_border= Border_BOTTOM_RIGHT1.getX();
                    double y_border = Border_BOTTOM_RIGHT1.getY();

                    /*the obstalce must be drawn realtive to the border window that is moving
                    so it needs to get the value of the difference so it knows how much to add to the border
                    value
                    */
                    double x_difference = Math.abs(x_border - x);
                    double y_difference = Math.abs(y_border- y);


                    double x_obstacle =0.5+(0.002*100)- (0.002*x_difference) ;
                    double y_obstacle = (0.002*y_difference) +  0.5-(0.002*200);
                    StdDraw.filledSquare(x_obstacle+0.004, y_obstacle+0.004,0.002*2);

                }

            }

           // StdDraw.show();
        }
    }


    public static void drawObstacles(List obstacles,Player player) {
        double xCentre = 0.5;
        double yCentre = 0.5;
        StdDraw.enableDoubleBuffering();
        if (obstacles.size() > 0) {

            for (int i = 0; i < obstacles.size(); i++) {
                Object obstacle_object = obstacles.get(i);
                SquareObstacle squareObstacle = (SquareObstacle) obstacle_object;


                double x = squareObstacle.getBottomLeftX();
                double y = squareObstacle.getBottomLeftY();

                Border_BOTTOM_RIGHT = player.getBorder_BOTTOM_RIGHT();
                Border_TOP_LEFT = player.getBorder_TOP_LEFT();
                if(squareObstacle.obstacle_inbounds(Border_TOP_LEFT,Border_BOTTOM_RIGHT)){

                    double x_border= Border_BOTTOM_RIGHT.getX();
                    double y_border = Border_BOTTOM_RIGHT.getY();

                    /*the obstalce must be drawn realtive to the border window that is moving
                    so it needs to get the value of the difference so it knows how much to add to the border
                    value
                    */
                    double x_difference = Math.abs(x_border - x);
                    double y_difference = Math.abs(y_border- y);


                    double x_obstacle =0.5+(0.002*100)- (0.002*x_difference) ;
                    double y_obstacle = (0.002*y_difference) +  0.5-(0.002*200);
                    StdDraw.filledSquare(x_obstacle+0.004, y_obstacle+0.004,0.002*2);

                }

            }

           // StdDraw.show();
        }
    }




    public void drawPlayers(Player player) {
        List players = player.getManager().getPlayers();
        List<Obstacle> player_sqaure = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            Player play = (Player) players.get(i);

            if (!play.equals(player)) {

                Position player_position = play.getPosition();
                double x1 = player_position.getX();
                double y1 = player_position.getY();
                Border_BOTTOM_RIGHT = player.getBorder_BOTTOM_RIGHT();
                Border_TOP_LEFT = player.getBorder_TOP_LEFT();
                SquareObstaclePlayers squareObstaclePlayer = new SquareObstaclePlayers(player_position.getX(), player_position.getY());


                double x = squareObstaclePlayer.getBottomLeftX();
                double y = squareObstaclePlayer.getBottomLeftY();

                if (squareObstaclePlayer.obstacle_inbounds(Border_TOP_LEFT, Border_BOTTOM_RIGHT)) {
                    StdDraw.setPenColor(yellow);
                    double x_border = Border_BOTTOM_RIGHT.getX();
                    double y_border = Border_BOTTOM_RIGHT.getY();

                    /*the obstalce must be drawn realtive to the border window that is moving
                    so it needs to get the value of the difference so it knows how much to add to the border
                    value
                    */
                    double x_difference = Math.abs(x_border - x);
                    double y_difference = Math.abs(y_border - y);


                    double x_obstacle = 0.5 + (0.002 * 100) - (0.002 * x_difference);
                    double y_obstacle = (0.002 * y_difference) + 0.5 - (0.002 * 200);
                    StdDraw.filledCircle(x_obstacle + 0.004, y_obstacle + 0.004, 0.01);


                }
            }
        }//StdDraw.show();
    }

    public void showObstacles() {

    }

    public void printStatus() {

    }

    public void forward(double steps){
        drawing_pen.forward(0.002 * steps);
    }

    public void back(double steps){
        drawing_pen.backward(0.002 * steps);
    }

    public void left(){
        drawing_pen.left(90.0);
    }

    public void right(){
        drawing_pen.right(90.0);
    }

    public void forward(double steps, Turtle turtle1){

        turtle1.forward(0.002 * steps);
    }

    public void reset(Turtle turtle,Player player){
        turtle.setColor(black);
       StdDraw.clear();
       makeWorld();
       drawObstacles(obstacles,player);
       drawPlayers(player);
    }

    public void forward(double steps, Turtle turtle1, Color color,Player player) {


        StdDraw.enableDoubleBuffering();
        Position position_checked;
         Border_TOP_LEFT = player.getBorder_TOP_LEFT();
         Border_BOTTOM_RIGHT = player.getBorder_BOTTOM_RIGHT();

            Border_TOP_LEFT = player.getBorder_TOP_LEFT();
            Border_BOTTOM_RIGHT = player.getBorder_BOTTOM_RIGHT();
            position_checked = check_if_beyond_border(turtle1,player);
            turtle1 =player.getTurtle();
            turtle1.forward(0.002*steps);
            double[] position =turtle1.getPosition();
             double x1 =position[0];
             double y1 =position[1];


            if (turtle1.getAngle() == 90) {
                y1 = y1 + 0.002;
            } else if (turtle1.getAngle() == 0) {
                x1 = x1 + 0.002;
            } else if (turtle1.getAngle() == 270) {

                y1 = y1 - 0.002;

            } else if (turtle1.getAngle() == 180) {
                x1 = x1 - 0.002;
            }


        Border_TOP_LEFT = player.getBorder_TOP_LEFT();
        Border_BOTTOM_RIGHT = player.getBorder_BOTTOM_RIGHT();
        position_checked = check_if_beyond_border(turtle1,player);

        reset(turtle1,player);
        turtle1.setColor(color);
        StdDraw.filledCircle(x1, y1, 0.01);
        StdDraw.show();

    }



    public void back(double steps, Turtle turtle1, Color color, Player player) {
        double[] position = turtle1.getPosition();
        double x1 = position[0];
        double y1 = position[1];
        double x2;
        double y2;
        StdDraw.enableDoubleBuffering();
        Position position_checked;
        Border_TOP_LEFT = player.getBorder_TOP_LEFT();
        Border_BOTTOM_RIGHT = player.getBorder_BOTTOM_RIGHT();

       // for (double x = 0; x < steps; x++) {
            Border_TOP_LEFT = player.getBorder_TOP_LEFT();
            Border_BOTTOM_RIGHT = player.getBorder_BOTTOM_RIGHT();
            position_checked = check_if_beyond_border(turtle1,player);
            turtle1 =player.getTurtle();

            turtle1.backward(0.002);

            x1= position_checked.getX();
            y1 = position_checked.getY();

            if (turtle1.getAngle() == 90) {
                y1 = y1 - 0.002;
            } else if (turtle1.getAngle() == 0) {
                x1 = x1 - 0.002;
            } else if (turtle1.getAngle() == 270) {

                y1 = y1 + 0.002;

            } else if (turtle1.getAngle() == 180) {
                x1 = x1 + 0.002;
            }

            reset(turtle1,player);

            turtle1.setColor(color);
            StdDraw.filledCircle(x1, y1, 0.01);

            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception k) {
            }

      //  }
        Border_TOP_LEFT = player.getBorder_TOP_LEFT();
        Border_BOTTOM_RIGHT = player.getBorder_BOTTOM_RIGHT();
        position_checked = check_if_beyond_border(turtle1,player);
        StdDraw.show();

    }

    public Position check_if_beyond_border(Turtle turtle3,Player player){
        double[] position =player.getTurtle().getPosition();
        double x1 =position[0];
        double y1 =position[1];
        double x2;
        double y2;

        Border_BOTTOM_RIGHT = player.getBorder_BOTTOM_RIGHT();
        Border_TOP_LEFT =player.getBorder_TOP_LEFT();

        double x_border_top = player.getBorder_TOP_LEFT().getX();
        double y_border_top = player.getBorder_TOP_LEFT().getY();

        double x_border_bottom = player.getBorder_BOTTOM_RIGHT().getX();
        double y_border_bottom = player.getBorder_BOTTOM_RIGHT().getY();


        DecimalFormat df = new DecimalFormat("#.######");
        x1 = Double.parseDouble(df.format(x1));
        y1 = Double.parseDouble(df.format(y1));

        if (x1 >limit(0.5+( 0.002*101))){
            x1 =limit( x1-(0.4));
            x_border_bottom = x_border_bottom +200;
            x_border_top = x_border_top + 200;

        }else if(y1 >limit(0.5+( 0.002*201))){
            y1 =limit(y1-(0.8));
            y_border_bottom = y_border_bottom +400;
            y_border_top = y_border_top + 400;

        }else if (x1 <limit(0.5+( -0.002*101))){
            x1 = limit(x1+0.4);
            x_border_bottom = x_border_bottom -200;
            x_border_top = x_border_top - 200;
        }else if (y1 < limit(0.5+ (0.002*-201))){
            y1= limit(y1+0.8);
            y_border_bottom = y_border_bottom -400;
            y_border_top = y_border_top - 400;
      }

        turtle3.setPosition(x1,y1);
        player.setTurtle(turtle3);
        Position positionfinal = new Position(x1,y1);


        Position topleft = new Position(x_border_top,y_border_top);
        Position bottomright = new Position(x_border_bottom,y_border_bottom);

        Border_BOTTOM_RIGHT = bottomright;
        Border_TOP_LEFT = topleft;

        player.setBorder_BOTTOM_RIGHT(bottomright);
        player.setBorder_TOP_LEFT(topleft);


        return  positionfinal;
    }

    public double limit(double var){
        DecimalFormat df = new DecimalFormat("#.######");
        Double var2 = Double.parseDouble(df.format(var));
        return var2;
    }


    public Position getBottomRight() {
        return BOTTOM_RIGHT;
    }

    public  Position getTopLeft() {
        return TOP_LEFT;
    }

    public void back(double steps, Turtle turtle){
        turtle.backward(0.002 * steps);
    }

    public void left(Turtle turtle){
        turtle.left(90.0);
        if (turtle.getAngle() >270){
            turtle.setAngle(0);}
            else if (turtle.getAngle() <0){
                turtle.setAngle(270);
    }
    }

    public void right(Turtle turtle){
        turtle.right(90.0);

        if (turtle.getAngle() >270){
            turtle.setAngle(0);

    }
        else if (turtle.getAngle() <0){
            turtle.setAngle(270);
        }
    }

}
