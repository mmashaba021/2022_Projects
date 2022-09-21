package za.co.wethinkcode.examples.server.obstacles;

import za.co.wethinkcode.examples.server.Manager;
import za.co.wethinkcode.examples.server.world.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LookObstacle {



    List obstacles_path_blocked;
    List obstacles;
    Manager manager;

    public List getObstacles_path_blocked() {
        return obstacles_path_blocked;
    }
    public void setObstacles(List <Obstacle> obstacles) throws IOException {
        this.obstacles = obstacles;
    }


    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List getObstacles() {
        return obstacles;
    }

    public Map<String, SquareObstacle> blocksPath(Position a, Position b) {
        List obstacles1 = this.obstacles;

        Map<String, SquareObstacle> found_obstacle = new HashMap<String, SquareObstacle>();
        boolean result = false;

        if (obstacles1.size() > 1) {
            for (int i = 0; i < obstacles1.size(); i++) {

                Object listed_obstacle = obstacles1.get(i);
                SquareObstacle squareObstacle = (SquareObstacle) listed_obstacle;

                if(squareObstacle.blocksPath(a,b)) {


                    String entry = squareObstacle.getBottomLeftX() + "," + squareObstacle.getBottomLeftY();
                    found_obstacle.put(entry, squareObstacle);
                    result = true;
                }
            }


        }else if (obstacles1.size() ==1){


                Object listed_obstacle = obstacles1.get(0);
                SquareObstacle squareObstacle = (SquareObstacle) listed_obstacle;

                if(squareObstacle.blocksPath(a,b)) {

                    String entry = squareObstacle.getBottomLeftX() + "," + squareObstacle.getBottomLeftY();
                    found_obstacle.put(entry, squareObstacle);
                    result = true;
                }
            }

        else{return found_obstacle;
        }

        return found_obstacle;
    }

    public Map<String, SquareObstaclePlayers> blocksPathPlayers(Position a, Position b) {
        List obstacles1 = this.obstacles;
        obstacles_path_blocked= new ArrayList<>();
        Map<String, SquareObstaclePlayers> found_obstacle = new HashMap<String, SquareObstaclePlayers>();
        boolean result = false;

        if (obstacles1.size() > 1) {
            for (int i = 0; i < obstacles1.size(); i++) {

                Object listed_obstacle = obstacles1.get(i);
                SquareObstaclePlayers squareObstacle = (SquareObstaclePlayers) listed_obstacle;
                squareObstacle.setManager(manager);
                if(squareObstacle.blocksPath(a,b)) {
                    obstacles_path_blocked.add(squareObstacle.getXy_start_obstacle());
                    String entry = squareObstacle.getBottomLeftX() + "," + squareObstacle.getBottomLeftY();
                    found_obstacle.put(entry, squareObstacle);
                    result = true;
                }
            }


        }else if (obstacles1.size() ==1){
            Object listed_obstacle = obstacles1.get(0);
            SquareObstaclePlayers squareObstacle = (SquareObstaclePlayers) listed_obstacle;
            squareObstacle.setManager(manager);
            if(squareObstacle.blocksPath(a,b)) {
                obstacles_path_blocked.add(squareObstacle.getXy_start_obstacle());

                String entry = squareObstacle.getBottomLeftX() + "," + squareObstacle.getBottomLeftY();
                found_obstacle.put(entry, squareObstacle);
                result = true;
            }
        }
        else{return found_obstacle;
        }

        return found_obstacle;
    }

    public Map<String, SquareObstaclePlayers> firePathPlayers(Position a, Position b) {
        List obstacles1 = this.obstacles;

        Map<String, SquareObstaclePlayers> found_obstacle = new HashMap<String, SquareObstaclePlayers>();
        boolean result = false;

        if (obstacles1.size() > 1) {
            for (int i = 0; i < obstacles1.size(); i++) {

                Object listed_obstacle = obstacles1.get(i);
                SquareObstaclePlayers squareObstacle = (SquareObstaclePlayers) listed_obstacle;

                if(squareObstacle.firePath(a,b)) {

                    String entry = squareObstacle.getBottomLeftX() + "," + squareObstacle.getBottomLeftY();
                    found_obstacle.put(entry, squareObstacle);
                    result = true;
                }
            }


        }else if (obstacles1.size() ==1){
            Object listed_obstacle = obstacles1.get(0);
            SquareObstaclePlayers squareObstacle = (SquareObstaclePlayers) listed_obstacle;

            if(squareObstacle.firePath(a,b)) {

                String entry = squareObstacle.getBottomLeftX() + "," + squareObstacle.getBottomLeftY();
                found_obstacle.put(entry, squareObstacle);
                result = true;
            }
        }
        else{return found_obstacle;
        }

        return found_obstacle;
    }


    public boolean blocksPosition(Position a) {
        List obstacles1 = this.obstacles;

        boolean result = false;

        if (obstacles1.size() > 1) {
            for (int i = 0; i < obstacles1.size(); i++) {

                Object listedObstacle = obstacles1.get(i);
                SquareObstacle newObstacle = (SquareObstacle) listedObstacle;

                if(newObstacle.blocksPosition(a))
                    result = true;
            }


        }else if (obstacles1.size() ==1){
            Object listedObstacle = obstacles1.get(0);
            SquareObstacle newObstacle = (SquareObstacle) listedObstacle;

            if(newObstacle.blocksPosition(a))
                result = true;
        }
        else{return false;
        }
        return result;
    }

}

