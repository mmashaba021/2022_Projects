package za.co.wethinkcode.examples.server.obstacles;

import za.co.wethinkcode.examples.server.world.FirstWorld;
import za.co.wethinkcode.examples.server.world.Position;

import static java.lang.Math.abs;

public class SquareObstacle implements Obstacle {

    double bottomLeftX;
    double bottomLeftY;
    double size = 5;


    public SquareObstacle(double bottomLeftX, double bottomLeftY){
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
    public boolean blocksPosition(Position newPosition) {

        double x1 =newPosition.getX();
        double y1 =newPosition.getY();

        double check_x_obstacle_1 = this.bottomLeftX;
        double check_y_obstacle_1 = this.bottomLeftY;
        double check_x_obstacle_2 = check_x_obstacle_1 + 4;
        double check_y_obstacle_2 = check_y_obstacle_1 + 4;


        if (x1 >= check_x_obstacle_1 && x1 <= check_x_obstacle_2 &&
            y1 >= check_y_obstacle_1 && y1 <= check_y_obstacle_2) {
           return true;
        }
        return false;
        }


    @Override
    public boolean blocksPath(Position oldPosition, Position newPosition) {


        double x1 =oldPosition.getX();
        double y1 =oldPosition.getY();

        double x2 =newPosition.getX();
        double y2 =newPosition.getY();


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


            double check_x_obstacle_1 = this.getBottomLeftX();
            double check_y_obstacle_1 = this.getBottomLeftY();
            double check_x_obstacle_2 = check_x_obstacle_1 + 4;
            double check_y_obstacle_2 = check_y_obstacle_1 + 4;


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
