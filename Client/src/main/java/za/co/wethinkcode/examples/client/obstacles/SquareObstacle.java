package za.co.wethinkcode.examples.client.obstacles;

import za.co.wethinkcode.examples.client.world.Position;

import static java.lang.Math.abs;

public class SquareObstacle implements Obstacle {

    double bottomLeftX;
    double bottomLeftY;



    String image_location;
    double size = 5;


    public SquareObstacle(double bottomLeftX, double bottomLeftY){
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
    }

    public String getImage_location() {
        return image_location;
    }

    public void setImage_location(String image_location) {
        this.image_location = image_location;
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

    public boolean obstacle_inbounds(Position top, Position bottom){
        Position obstacle_position = new Position(bottomLeftX,bottomLeftY);



        if (obstacle_position.isIn(top,bottom)   ) {

            return  true;

        }
        return  false;
    }

}
