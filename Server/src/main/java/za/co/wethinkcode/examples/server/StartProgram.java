package za.co.wethinkcode.examples.server;

import org.turtle.StdDraw;
import za.co.wethinkcode.examples.server.obstacles.SimpleObstacle;
import za.co.wethinkcode.examples.server.world.FirstWorld;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StartProgram {
    static String msg;
    static ArrayList<Runnable> servers;
    static Manager manager;
    static FirstWorld firstWorld;
    static ArrayList<String> clients;
    static ArrayList<Socket> sockets;
    static ServerSocket server;
    static String root_folder = System.getProperty("user.dir") + "/";
    static Properties properties;

    /*this starts a new server when a new client connects
   runs and starts the entire program. for the duration of the program we are always in the main

     */



    public static void main(String[] args) throws IOException {

        //load config file
        initialiseConfigFile();
        createServerArry();

        startSever();
        createProjectManager();
        createWorld();
        StartProgram multiServers = new StartProgram();
        multiServers.serverConsole();
        newServer();


    }

    private static void startSever() throws IOException {
        server = new ServerSocket(GameServer.PORT);
        System.out.println("Server running & waiting for client connections.");
    }

    private static void createProjectManager() {
        manager = new Manager();
        manager.setServers(servers);
    }

    private static void initialiseConfigFile() throws IOException {
        properties=new Properties();
        FileInputStream config_file= new FileInputStream(root_folder+"config.properties");
        properties.load(config_file);

    }

    //this outputs information about the world to the server admin. curriculum requirement for iteraion 1
    //not fully tested/implemented

    public static void createServerArry(){
        servers = new ArrayList<>();
        clients = new ArrayList<>();
        sockets = new ArrayList<>();

    }

    public static void newServer(){
        while(true) { /*this is the main loop that adds each client/player
        this is the core of the server creations and where all new clients are processed
        */
            try {

                Socket socket = server.accept();
                Runnable r = new GameServer(socket, manager);
                System.out.println("Connection: " + socket);

                Thread task = new Thread(r);
                task.start();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //creates the world that will be use throughout the program. all players need access to this
    //this object is created once and is used throughout the whole program

    public static void createWorld(){

        firstWorld = new FirstWorld();
        manager.setFirstWorld(firstWorld);
        setWorldVariables();
        firstWorld.makeWorld();
        StdDraw.show();
        System.out.println("World created");
        System.out.println("Type quit to quit server");

    }

    private static void setWorldVariables() {

        firstWorld.setLength(Double.parseDouble(properties.getProperty("world_length")));
        firstWorld.setWidth(Double.parseDouble(properties.getProperty("world_width")));
        firstWorld.setBottomRight();
        firstWorld.setTopLeft();

        SimpleObstacle obstacles = new SimpleObstacle();
        obstacles.setFirstWorld(firstWorld);


        obstacles.setObs_amount(Integer.parseInt(properties.getProperty("number_of_obstacles")));
        List obstacle = obstacles.getObstacles();
        firstWorld.setObstacles(obstacle);
        firstWorld.drawObstacles(obstacle);
        firstWorld.setDefault_reload(Integer.parseInt(properties.getProperty("reload_time")));
        firstWorld.setDefault_repair_time(Integer.parseInt((properties.getProperty("repair_time"))));
        firstWorld.setDefault_visibility(Integer.parseInt((properties.getProperty("visibility_distance"))));
        firstWorld.setDefault_shots_amount_type_shortrange(Integer.parseInt((properties.getProperty("number_of_shots_type_shortrange"))));
        firstWorld.setDefault_shots_amount_type_longrange(Integer.parseInt((properties.getProperty("number_of_shots_type_longrange"))));
        firstWorld.setDefault_shot_distance_type_shortrange(Integer.parseInt((properties.getProperty("shot_distance_type_shortrange"))));
        firstWorld.setDefault_shot_distance_type_longrange(Integer.parseInt((properties.getProperty("shot_distance_type_longrange"))));
        firstWorld.setDefault_sheild(Integer.parseInt(properties.getProperty("shield_strength")));
    }

    public  void serverConsole(){
        Console multiServerConsole = new Console();
        StartProgram multiServers = this;
        multiServerConsole.setMultiServers(multiServers);
        multiServerConsole.serverConsole();
    }


    public  ArrayList<Socket> getSockets() {
        return sockets;
    }

    public  ArrayList<Runnable> getServers() {
        return servers;
    }

    public  Manager getManager() {
        return manager;
    }

    public ArrayList<String> getClients() {
        return clients;
    }

    public  FirstWorld getFirstWorld() {

        return firstWorld;
    }

    public static void setServers(ArrayList<Runnable> servers) {
        StartProgram.servers = servers;
    }

    public static void setManager(Manager manager) {
        StartProgram.manager = manager;
    }

    public static void setFirstWorld(FirstWorld firstWorld) {
        StartProgram.firstWorld = firstWorld;
    }


}