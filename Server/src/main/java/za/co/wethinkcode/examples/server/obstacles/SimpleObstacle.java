package za.co.wethinkcode.examples.server.obstacles;

import za.co.wethinkcode.examples.server.world.FirstWorld;
import za.co.wethinkcode.examples.server.world.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleObstacle {
    List obstacles;
    FirstWorld firstWorld;


    int obs_amount;

    public void setFirstWorld(FirstWorld firstWorld) {
        this.firstWorld = firstWorld;
    }

    public void setObs_amount(int obs_amount) {
        this.obs_amount = obs_amount-1;
    }

    public List<Obstacle> getObstacles() {


        Random random = new Random();

       List obstacles1= addFirstObstacle();

        for (int i = 0; i < obs_amount; i++) {

            boolean check_random_position = false;

            while (!check_random_position) {

                LookObstacle lookObstacle = new LookObstacle();

                try {
                    lookObstacle.setObstacles(obstacles1);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                int random_numberx = random.nextInt((int) firstWorld.getWidth() - 10);
                random_numberx = random_numberx - (int) (firstWorld.getWidth() / 2) - 10;

                int random_numbery = random.nextInt((int) firstWorld.getLength() - 10);
                random_numbery = random_numbery - (int) (firstWorld.getLength() / 2) - 10;

                int x = random_numberx;
                int y = random_numbery;

                SquareObstacle obs = new SquareObstacle(x, y);
                Position newPosition = new Position(x, y);

                if (!lookObstacle.blocksPosition(newPosition)) {
                    check_random_position = true;
                    obstacles1.add(obs);

                }
            }
        }
        obstacles = obstacles1;
        return obstacles1;


    }

    public List addFirstObstacle() {

        List<Obstacle> obstacles1 = new ArrayList<>();
        Random random = new Random();

        int random_numberx = random.nextInt((int) firstWorld.getWidth() - 10);
        random_numberx = random_numberx - (int) (firstWorld.getWidth() / 2) - 10;

        int random_numbery = random.nextInt((int) firstWorld.getLength() - 10);
        random_numbery = random_numbery - (int) (firstWorld.getLength() / 2) - 10;

        int x = random_numberx;
        int y = random_numbery;

        SquareObstacle obs = new SquareObstacle(x, y);
        Position newPosition = new Position(x, y);

            obstacles1.add(obs);



        return obstacles1;
    }
}

