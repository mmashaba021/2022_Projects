package za.co.wethinkcode.examples.client.obstacles;

/**
 * Defines an interface for obstacles you want to place in your world.
 */
public interface Obstacle {


    // void setObstacles(List obstacles); /////

    /**
     * Get X coordinate of bottom left corner of obstacle.
     *
     * @return x coordinate
     */
    double getBottomLeftX();

    /**
     * Get Y coordinate of bottom left corner of obstacle.
     *
     * @return y coordinate
     */
    double getBottomLeftY();

    /**
     * Gets the side of an obstacle (assuming square obstacles)
     *
     * @return the length of one side in nr of steps
     */
    double getSize();

}


