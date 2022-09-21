package za.co.wethinkcode.examples.client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.wethinkcode.examples.client.command.Command;
import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.world.DrawInterface;
import za.co.wethinkcode.examples.client.world.FirstWorld;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//this is the client. it receives all json strings and makes all requests
//every response is processed by the command class
public class Client {

    private Socket socket		 = null;
    private DataInputStream input = null;
    private DataOutputStream out	 = null;
    private DataInputStream in	 = null;
    static Scanner scanner;
    String robot_name;
    FirstWorld firstWorld;
    DrawInterface drawInterface;
    Player player;
    String address;
    int port;
    String user_request = "";
    String server_response = "";
    String user_input = "";
    JSONObject request_json;
    boolean loop = false;

    // Get ip from user
    private static final String INET4ADDRESS = "172.8.9.28";

    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    public Client(String address1, int portNew) {

        address= address1;
        port =portNew;

        initialiseWorld();
        initializeSocket();
        createPlayer();
        requestAndResponseLoop();
        close_connection();
    }


    /**
    *keeps user in  a while loop till the correct response is received from server
    *this lets user keep trying again if a launch response is not received
    *A response_json is received and its contents will be used to update
    *the clients player and first world classes so the state is up to date
    */
    private void requestAndResponseLoop() {

        while (!user_input.equals("exit")&& !loop)
        {
            try
            {

                launchPlayer();
                request_json = userRequest();
                sendUserRequest(request_json);
                parseResponse();
                UpdateClientWithResponse();
            }
            catch(IOException i)
            {
                System.out.println("You have disconnected from server");

            }
        }
    }

    private void close_connection() {
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
        }
    }


public void setLoop(boolean loopSet){
        loop = loopSet;
}

    private void parseResponse() throws IOException {
        server_response = in.readUTF();
        JSONParser parser = new JSONParser();
        JSONObject response_json = null;
        try {
            response_json = (JSONObject) parser.parse(server_response);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        player.setJsonObject(response_json);
    }

    private JSONObject userRequest() {
        user_request = firstWorld.getTurtleInput().toLowerCase();
        user_input = user_request;
        JSONObject jsonRequest = createRequest(user_request,robot_name);
        return jsonRequest;
    }

    private void sendUserRequest(JSONObject json) throws IOException {

        user_request = json.toString();
        out.writeUTF(user_request);


    }

    private void launchPlayer() throws IOException {
        while(!player.isPlayer_launched()){

            chooseName();
            chooseRobot();
            String type_choice = chooseType();
            LaunchPrompt();
            sendLaunchRequest(type_choice);
            parseResponse();
            UpdateClientWithResponse();

        }
    }

    private void LaunchPrompt() throws IOException {
        System.out.println("Enter 'launch' to join the game and start");
        user_request = input.readLine();
    }

    private void chooseName() {
        robot_name = getInput("What do you want to name your robot?");
    }

    private void UpdateClientWithResponse() {
        Command command = Command.create(request_json,player);
        command.setFirstWorld(firstWorld);
        command.setDrawInterface(drawInterface);
        if(!command.execute(player)){
            loop= true;

        }

    }

    private void sendLaunchRequest(String type_choice) throws IOException {
        request_json = createRequest(user_request, robot_name,type_choice);
        user_request = request_json.toString();
        out.writeUTF(user_request);
    }

    private void chooseRobot() {
        String team_choice = chooseTeam();
        String robot_name_final = team_choice+ robot_name;
        robot_name = robot_name_final;
        player.setRobotName(robot_name);
    }

    private void initialiseWorld() {
        firstWorld = new FirstWorld();
        drawInterface = new DrawInterface();
        firstWorld.setDrawInterface(drawInterface);
        drawInterface.setFirstWorld(firstWorld);
    }

    private void createPlayer() {
        Player player= new Player(firstWorld);
        this.player = player;
        firstWorld.setPlayer(player);
        drawInterface.setPlayer(player);
        firstWorld.setClient(this);
    }

    private void initializeSocket() {

        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);

            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        }
        catch(UnknownHostException u)
        {
        }
        catch(IOException i)
        {
        }

    }

    private String chooseTeam(){
        String team_Choice = getInput("Choose a team:\nEnter '1' for team one\nEnter '2' for team two");
        while (!team_Choice.equals("1") && !team_Choice.equals("2") ){
            System.out.println("invalid entry, please try again");
             team_Choice = getInput("Choose a team:\nEnter '1' for team one\nEnter '2' for team two");

        }
        switch (team_Choice){

            case "1": return "Team_One: ";

            case "2": return "Team_Two: ";
        }


    return  team_Choice;
    }



    private String chooseType(){
        String type_Choice = getInput("Choose a robot type:\nEnter '1' for long range\nEnter '2' for short range");
        while (!type_Choice.equals("1") && !type_Choice.equals("2") ) {
            System.out.println("invalid entry, please try again");
            type_Choice = getInput("Choose a robot type:\nEnter '1' for long range\nEnter '2' for short range");

        }
            switch (type_Choice) {

                case "1":
                    return "long_range";

                case "2":
                    return "short_range";
            }
        return type_Choice;
    }


    private static String getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }

public JSONObject createRequest(String request,String robot){
    String[] args1 = request.split(" ",2);
    String args2= "";
    JSONObject jsonRequest = new JSONObject();

        String[] args;
        try{
        args = args1[1].split(" ");
        JSONArray jsArray = new JSONArray();
        StringBuffer buffer = new StringBuffer();
        for (int i=0; i< args.length;i++){
            buffer.append(" "+args[i]);
            jsArray.add(args[i]);
        }

            args2 = String.valueOf(jsArray);}
        catch (Exception o){}


        jsonRequest.put("robot", robot);
        jsonRequest.put("command", args1[0]);
        jsonRequest.put("arguments", args2);

    return jsonRequest;
}



    public JSONObject createRequest(String request,String robot, String type){
        request = request.toLowerCase();
        String[] args1 = request.split(" ",2);
        String args2= "";
        JSONObject jsonRequest = new JSONObject();

            JSONArray jsArray = new JSONArray();

                jsArray.add(type);


            args2 = String.valueOf(jsArray);

        jsonRequest.put("robot", robot);
        jsonRequest.put("command", args1[0].toLowerCase());
        jsonRequest.put("arguments", args2);

        return jsonRequest;
    }
    public static void main(String args[])
    {
        boolean validIp;
        Scanner scanner = new Scanner(System.in);
        System.out.println("***************************************************************************");
        System.out.println("*                         Welcome to Robot Worlds                         *");
        System.out.println("***************************************************************************\n");

        System.out.println("Please enter an IP address to connect: ");
        String IpAddress = scanner.nextLine();
        validIp = isValidInet4Address(IpAddress);

        while (!validIp) {
            System.out.println("Please enter the correct IP address: ");
            IpAddress = scanner.nextLine();
            validIp = isValidInet4Address(IpAddress);
        }

        Client client = new Client("127.0.0.1", 5000);
    }

    /*
    * Validates ip address
    */
    public static boolean isValidInet4Address(String ip)
    {
        if (ip == null) {
            return false;
        }
        Matcher matcher = IPv4_PATTERN.matcher(ip);
        return matcher.matches();
    }
}
