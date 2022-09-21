package za.co.wethinkcode.examples.server.obstacles;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.examples.server.world.FirstWorld;
import za.co.wethinkcode.examples.server.world.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareObstacleTest {



    @Test
    void testPositionBlocksCentre() {

        SimpleObstacle simpleObstacle = new SimpleObstacle();
        FirstWorld firstWorld = new FirstWorld();
        firstWorld.setWidth(100);
        firstWorld.setLength(100);
        simpleObstacle.setFirstWorld(firstWorld);


        SquareObstacle coordinate = new SquareObstacle(1,1);


        double x_coordinate = coordinate.getBottomLeftX();
        double y_coordinate = coordinate.getBottomLeftY();

        Position centre =new Position(1, 1);
        Position edge =new Position(100, 0);
        Obstacle centred_obstacle = new SquareObstacle(x_coordinate, y_coordinate);
        assertTrue(centred_obstacle.blocksPosition(centre));
        assertFalse(centred_obstacle.blocksPosition(new Position(100, 1)));

    }
}