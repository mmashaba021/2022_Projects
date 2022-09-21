package za.co.wethinkcode.examples.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.wethinkcode.examples.server.command.Command;
import za.co.wethinkcode.examples.server.command.CreateJsonObjects;
import za.co.wethinkcode.examples.server.player.Player;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameServer implements Runnable {

    public static final int PORT = 5000;
    private Socket		 socket = null;
    private static ArrayList<Runnable> server = null;
    private DataInputStream in	 = null;
    private DataOutputStream out	 = null;
    ArrayList serve;
    String name;
    Player player;
    Manager manager;


    public GameServer(Socket socket, Manager manager) throws IOException {
        System.out.println("Server started");
        this.socket =socket;
        System.out.println("Waiting for a client ...");
        System.out.println("Client accepted");
        this.manager =manager;
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        }catch(Exception e){

        }


    }

    public void run() {
        String user_entry = "";
        try {

          addNewPlayer(user_entry);
          checkNumberOfConnectedUsers();
          requestAndResponsesLoop(user_entry);

         closeConnection();
        }
        catch(IOException i)
        {
           closeConnection2();

        }
    }

    private void requestAndResponsesLoop(String user_entry) throws IOException {
        while (!user_entry.equals("Over")) {

            try {

                user_entry =getUserEntry();
                parseUserEntry(user_entry);
                makeResponse(user_entry);


            } catch (IOException i) {
                removeDisconnectedPlayer();
                break;


            } catch (ParseException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    private void makeResponse(String user_entry) throws IOException {
        if (!player.getMessageSuccess()) {
            JSONObject unsupported = unrecognisedCommad();
            user_entry = unsupported.toString();
            out.writeUTF(user_entry);
        } else {
            JSONObject response = player.getResponse();
            user_entry = response.toString();
            out.writeUTF(user_entry);

        }
    }

    private void parseUserEntry(String user_entry) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(user_entry);
        initialiseCommand(json);
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    private String getUserEntry() throws IOException {
        String user_entry = "";
        user_entry = in.readUTF();
        return  user_entry;
    }

    private void removeDisconnectedPlayer() throws IOException {
        name = player.getRobotName();
        manager.removePlayers(player);

        System.out.println("client "+ name+ " has disconnected");
        socket.close();
        in.close();
    }

    private void closeConnection2() {
        System.out.println("client "+ name+ " has disconnected");
        manager.removePlayers(player);
    }

    private void closeConnection() throws IOException {
        System.out.println("Closing connection");
        serve = manager.getPlayers();
        System.out.println("the size of list is "+ serve.size());
        manager.removePlayers(player);
        socket.close();
        in.close();
    }

    private void addNewPlayer(String user_entry) {
        player = new Player(user_entry,this, manager.getFirstWorld()); //creating player object
        player.setManager(manager);
        manager.addPlayers(player);

    }

    private void initialiseCommand(JSONObject json) {
        manager.checkTeamWon();
        Command command = Command.create(json,player);
        CreateJsonObjects createJsonObjectState = new CreateJsonObjects();
        createJsonObjectState.setPlayer(player);
        command.setCreateJsonObjectStateClass(createJsonObjectState);
        command.setManager(manager);
        command.setFirstWorld(manager.getFirstWorld());
        command.execute(player);
    }

    private void checkNumberOfConnectedUsers() {
        ArrayList servers = manager.getPlayers();
        if (servers.size()<2){
            System.out.println("there is " + servers.size() +" connected client");
        }else {
            System.out.println("there are " + servers.size() + " connected clients");
        }
        manager.removePlayers(player);

    }

    public JSONObject unrecognisedCommad() {

       JSONObject jsonUnrecognised = new JSONObject();
       JSONObject message = new JSONObject();


       message.put("message", "Unsupported command");
       jsonUnrecognised.put("result", "ERROR");
       jsonUnrecognised.put("data", message);

return jsonUnrecognised;
   }

    public void closeQuietly() {
        try {
            socket.close();
            in.close();
        } catch(IOException ex) {}
    }
}