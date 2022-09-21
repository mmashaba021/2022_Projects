package za.co.wethinkcode.examples.server.obstacles;

import za.co.wethinkcode.examples.server.Manager;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.Position;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class SquareObstaclePlayers implements Obstacle {

    double bottomLeftX;
    double bottomLeftY;
    double size = 5;
    Manager manager;
    List xy_start_obstacle;

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List getXy_start_obstacle() {
        return xy_start_obstacle;
    }

    public SquareObstaclePlayers(double bottomLeftX, double bottomLeftY){
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
    }


    @Override
    public double getBottomLeftX() {
        return bottomLeftX;
    }

    @Override
    public double getBottomLeftY() {
        return bottomLeftY;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public boolean blocksPosition(Position a) {


        double x1 =a.getX();
        double y1 =a.getY();



        boolean result = false;
        double check_x_obstacle_1 = this.bottomLeftX;
        double check_y_obstacle_1 = this.bottomLeftY;
        double check_x_obstacle_2 = check_x_obstacle_1 + 1;
        double check_y_obstacle_2 = check_y_obstacle_1 + 1;


        if (x1 >= check_x_obstacle_1 && x1 <= check_x_obstacle_2 &&
            y1 >= check_y_obstacle_1 && y1 <= check_y_obstacle_2) {
            result = true;
        }
        return result;
        }


    @Override
    public boolean blocksPath(Position a, Position b) {
        xy_start_obstacle = new ArrayList();

        double x1 =a.getX();
        double y1 =a.getY();

        double x2 =b.getX();
        double y2 =b.getY();

        String direction= "";
        for (int i =0; i<manager.getPlayers().size();i++) {

            Player player = (Player) manager.getPlayers().get(i);
            double player_positionX = player.getPosition().getX();
            double player_positionY = player.getPosition().getY();

            if (player_positionX == bottomLeftX && player_positionY == bottomLeftY) {
                direction = String.valueOf(player.getCurrentDirection());
                break;
            }


        }

        boolean result = false;

        double diff_x = abs(x2 - x1);
        double diff_y  = abs(y2 - y1);
        double diff;

        String choice;
        if (diff_x != 0) {
            diff = diff_x;
            choice = "x";
        }
        else if (diff_y!=0){
            diff = diff_y;
            choice = "y";
        }
        else{ return false;
        }


        for (double nums = 0; nums< diff+1; nums++) {

            double check_x_obstacle_0 = this.getBottomLeftX();
            double check_y_obstacle_0 = this.getBottomLeftY();
            double check_x_obstacle_1 = 0;
            double check_y_obstacle_1 = 0;
            double check_x_obstacle_2 = 0;
            double check_y_obstacle_2 = 0;

            int value = 5;
            if (direction.equals("NORTH")) {
                check_x_obstacle_1 = check_x_obstacle_0 -value;
                check_y_obstacle_1 = check_y_obstacle_0 - (value*2);
                check_x_obstacle_2 = check_x_obstacle_0 + value;
                check_y_obstacle_2 = check_y_obstacle_0 ;

            }

            else if (direction.equals("SOUTH")) {
                check_x_obstacle_1 = check_x_obstacle_0 -value;
                check_y_obstacle_1 = check_y_obstacle_0 ;
                check_x_obstacle_2 = check_x_obstacle_0 + value;
                check_y_obstacle_2 = check_y_obstacle_0 + (value*2);

            }

            else if (direction.equals("EAST")) {
                check_x_obstacle_1 = check_x_obstacle_0 - (value*2);
                check_y_obstacle_1 = check_y_obstacle_0 -value;
                check_x_obstacle_2 = check_x_obstacle_0 ;
                check_y_obstacle_2 = check_y_obstacle_0 +value ;

            }

            else if (direction.equals("WEST")) {
                check_x_obstacle_1 = check_x_obstacle_0 ;
                check_y_obstacle_1 = check_y_obstacle_0 -value;
                check_x_obstacle_2 = check_x_obstacle_0 + (value*2);
                check_y_obstacle_2 = check_y_obstacle_0 +value ;

            }


            if (choice == "x") {

                if (x1 >= check_x_obstacle_1 && x1 <= check_x_obstacle_2 &&
                    y1 >= check_y_obstacle_1 && y1 <= check_y_obstacle_2) {
                    xy_start_obstacle.add(x1);
                    xy_start_obstacle.add(y1);
                    return true;
                    }
                } else if (choice == "y")

                    if (x1 >= check_x_obstacle_1 && x1 <= check_x_obstacle_2
                       && y1 >= check_y_obstacle_1 && y1 <= check_y_obstacle_2) {
                        xy_start_obstacle.add(x1);
                        xy_start_obstacle.add(y1);

                        return true;
                    }

            if (choice == "x") {
                if (x2 < x1) {
                    x1 = x1 - 1;
                }
                if (x2 > x1) {
                    x1 = x1 + 1;
                }
            } else if (choice == "y") {
                if (y2 < y1) {
                    y1 = y1 - 1;
                }
                if (y2 > y1) {
                    y1 = y1 + 1;
                }
            }
        }
        return result;
    }

    public boolean firePath(Position a, Position b) {


        double x1 =a.getX();
        double y1 =a.getY();

        double x2 =b.getX();
        double y2 =b.getY();

        String direction= "";
        for (int i =0; i<manager.getPlayers().size();i++){

            Player player = (Player) manager.getPlayers().get(i);
            double player_positionX = player.getPosition().getX();
            double player_positionY = player.getPosition().getY();

            if (x1 == player_positionX && y1 == player_positionY){
                direction = String.valueOf(player.getCurrentDirection());
                break;
            }


        }
        boolean result = false;

        double diff_x = abs(x2 - x1);
        double diff_y  = abs(y2 - y1);
        double diff;

        String choice;
        if (diff_x != 0) {
            diff = diff_x;
            choice = "x";
        }
        else if (diff_y!=0){
            diff = diff_y;
            choice = "y";
        }
        else{ return false;
        }


        for (double nums = 0; nums< diff+1; nums++) {


            double check_x_obstacle_0 = this.getBottomLeftX();
            double check_y_obstacle_0 = this.getBottomLeftY();
            double check_x_obstacle_1 = 0;
            double check_y_obstacle_1 = 0;
            double check_x_obstacle_2 = 0;
            double check_y_obstacle_2 = 0;

            int value = 5;
            if (direction.equals("NORTH")) {
                check_x_obstacle_1 = check_x_obstacle_0 -value;
                check_y_obstacle_1 = check_y_obstacle_0 - (value*2);
                check_x_obstacle_2 = check_x_obstacle_0 + value;
                check_y_obstacle_2 = check_y_obstacle_0 ;

            }

            else if (direction.equals("SOUTH")) {
                check_x_obstacle_1 = check_x_obstacle_0 -value;
                check_y_obstacle_1 = check_y_obstacle_0 ;
                check_x_obstacle_2 = check_x_obstacle_0 + value;
                check_y_obstacle_2 = check_y_obstacle_0 + (value*2);

            }

            else if (direction.equals("EAST")) {
                check_x_obstacle_1 = check_x_obstacle_0 - (value*2);
                check_y_obstacle_1 = check_y_obstacle_0 -value;
                check_x_obstacle_2 = check_x_obstacle_0 ;
                check_y_obstacle_2 = check_y_obstacle_0 +value ;

            }

            else if (direction.equals("WEST")) {
                check_x_obstacle_1 = check_x_obstacle_0 ;
                check_y_obstacle_1 = check_y_obstacle_0 -value;
                check_x_obstacle_2 = check_x_obstacle_0 + (value*2);
                check_y_obstacle_2 = check_y_obstacle_0 +value ;

            }

            if (choice == "x") {

                if (x1 >= check_x_obstacle_1 && x1 <= check_x_obstacle_2 &&
                        y1 >= check_y_obstacle_1 && y1 <= check_y_obstacle_2) {
                    result = true;
                }
            } else if (choice == "y")

                if (x1 >= check_x_obstacle_1 && x1 <= check_x_obstacle_2
                        && y1 >= check_y_obstacle_1 && y1 <= check_y_obstacle_2) {
                    result = true;
                }

            if (choice == "x") {
                if (x2 < x1) {
                    x1 = x1 - 1;
                }
                if (x2 > x1) {
                    x1 = x1 + 1;
                }
            } else if (choice == "y") {
                if (y2 < y1) {
                    y1 = y1 - 1;
                }
                if (y2 > y1) {
                    y1 = y1 + 1;
                }
            }
        }
        return result;
    }

    public boolean obstacle_inbounds(Position top, Position bottom){
        Position obstacle_position = new Position(bottomLeftX,bottomLeftY);



        if (obstacle_position.isIn(top,bottom)   ) {

            return  true;

        }
        return  false;
    }
}
