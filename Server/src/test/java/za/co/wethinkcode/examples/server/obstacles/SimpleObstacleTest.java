package za.co.wethinkcode.examples.server.obstacles;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.examples.server.world.FirstWorld;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleObstacleTest {







    @Test
    void testCoordinatesAreNumbers() {

        FirstWorld firstWorld = new FirstWorld();
        firstWorld.setWidth(100);
        firstWorld.setLength(100);


        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setFirstWorld(firstWorld);
        List<Obstacle> obstacles = simpleObstacle.getObstacles();
        Object first_obstacle = obstacles.get(0);
        SquareObstacle coordinate = (SquareObstacle) first_obstacle;
        boolean check_digit = false;
        try {
            double x_coordinate = coordinate.getBottomLeftX();
            double y_coordinate = coordinate.getBottomLeftY();
            check_digit = true;
        }catch(Exception e){}

        assertTrue(check_digit);

    }



    @Test
    /*
    tests that the number of obstacles are configurable
     */
    void getObstacles() {
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(10);

        FirstWorld firstWorld = new FirstWorld();
        firstWorld.setWidth(100);
        firstWorld.setLength(100);
        simpleObstacle.setFirstWorld(firstWorld);

        assertEquals(10, simpleObstacle.getObstacles().size());
    }

    @Test
    /*
    tests that addObstacle function
     */
    void addOneObstacle() {
        SimpleObstacle simpleObstacle = new SimpleObstacle();
        simpleObstacle.setObs_amount(10);

        FirstWorld firstWorld = new FirstWorld();
        firstWorld.setWidth(100);
        firstWorld.setLength(100);
        simpleObstacle.setFirstWorld(firstWorld);

        assertEquals(1, simpleObstacle.addFirstObstacle().size());
    }


}