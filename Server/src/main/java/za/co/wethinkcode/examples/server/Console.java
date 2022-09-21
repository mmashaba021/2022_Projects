package za.co.wethinkcode.examples.server;

import za.co.wethinkcode.examples.server.obstacles.SquareObstacle;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.FirstWorld;
import za.co.wethinkcode.examples.server.world.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Console {

    static ArrayList<Runnable> servers;
    static StartProgram multiServers;
    static String output;


    public static void serverConsole(){

        new Thread() {
            @Override
            public void run() {

                while (true) { /*this loop controls a console.
                                it can accept commands
                                listing users and closing all connections.
                              */
                    try {

                        InputStreamReader input = new InputStreamReader(System.in);

                        BufferedReader reader = new BufferedReader(input);


                        String name = null;
                        try {
                            name = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (name.contains("quit")) {

                          quit();
                        }
                        if (name.contains("dump")){

                            dump();

                        }

                        if (name.contains("robots")){
                            robots();

                        }

                    }catch(Exception z){System.out.println("no text");
                    }
                }
            }
        }.start();

    }

    public static void quit() {
        try {


            for (int i = 0; i < multiServers.getSockets().size(); i++) {
                servers = multiServers.getManager().getPlayers();
                Runnable check = servers.get(i);
                Player checker = (Player) check;

                GameServer check1 = checker.getServer();
                check1.closeQuietly();
            }
        } catch (Exception o) {
            System.out.println("exiting.....");
            output ="exiting.....";
            System.out.println("there are " + servers.size() + " connected");
            System.exit(0);

        }
        System.out.println("exiting.....");
        System.out.println("there are " + servers.size() + " connected");
        System.exit(0);

    }

    public  void setServers(ArrayList<Runnable> servers) {
        Console.servers = servers;
    }

    public void setMultiServers(StartProgram multiServers) {
        this.multiServers = multiServers;
    }

    public static void dump(){

        FirstWorld firstWorld1 = multiServers.getManager().getFirstWorld();

        System.out.println("World information: ");
        System.out.println("\nWorld Dimensions: ");
        Position bottomright = firstWorld1.getBottomRight();
        double bottomrightx = bottomright.getX();
        double bottomrighty = bottomright.getY();
        System.out.println("Bottom Right Corner coordinates: (" + bottomrightx + "," + bottomrighty + ")");
        Position topLeft = firstWorld1.getTopLeft();
        double topLeftx = topLeft.getX();
        double topLefty = topLeft.getY();
        System.out.println("\nTop Left Corner coordinates: (" + topLeftx + "," + topLefty + ")");

        System.out.println("\nObstacles: ");

        List obstacles = multiServers.getFirstWorld().getObstacles();
        if (obstacles.size() >0){
            output ="There are "+obstacles.size()+" obstacles";
            System.out.println("There are "+obstacles.size()+" obstacles");
            for(int i =0; i< obstacles.size();i++){

                Object obstacle =  obstacles.get(i);
                SquareObstacle squareObstacle = (SquareObstacle) obstacle;

                double x = squareObstacle.getBottomLeftX();
                double y = squareObstacle.getBottomLeftY();
                double x2 = x + 4;
                double y2 = y + 4;
                System.out.println("- At position " + x + "," + y + "(" + x2 + "," + y2 + ")");
            }
            List player = multiServers.getManager().getPlayers();

            System.out.println("There are "+player.size()+ " robots in the robot world");
            System.out.println("\nList of robots");
            for(int i= 0; i<player.size();i++){
                Player player1 = (Player) player.get(i);
                String robot = player1.getRobotName();
                System.out.println(robot);
            }
        }
    }

    public String getOutput() {
        return output;
    }

    public static void robots(){
        List players = multiServers.getManager().getPlayers();

        System.out.println("There are "+players.size()+ " robots in the robot world");
        output = ("There are "+players.size()+ " robots in the robot world");
        System.out.println("\nList of robots: ");
        for(int i= 0; i<players.size();i++) {
            Player player = (Player) players.get(i);
            String robot_name = player.getRobotName();
            String robot_team = player.getTeam();

            System.out.println("Team: "+robot_team);
            System.out.println("Robot name: "+robot_name);
            System.out.println("Position: "+"("+player.getPosition().getX()+","+ player.getPosition().getY()+")");
            System.out.println("Current Direction: "+ player.getCurrentDirection());
            System.out.println("Shield Level: "+player.getSheild());
            System.out.println("Shots left: "+ player.getShots());
            System.out.println("Operational state: "+player.getOperational_state());
            System.out.println("\n");
        }
    }

}
