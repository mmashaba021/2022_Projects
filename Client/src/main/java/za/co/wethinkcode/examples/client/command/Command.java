package za.co.wethinkcode.examples.client.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.world.DrawInterface;
import za.co.wethinkcode.examples.client.world.FirstWorld;

public abstract class Command {
    private final String name;
    private  String argument;
    public FirstWorld firstWorld;
    public Player player;
    public DrawInterface drawInterface;


    public void setFirstWorld(FirstWorld FirstWorld) {
        this.firstWorld = FirstWorld;
    }

    public void setDrawInterface(DrawInterface drawInterface) {
        this.drawInterface = drawInterface;}

    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract boolean execute(Player player);

    public Command(String name,Player player){

        this.name = name.toLowerCase().trim();
        this.player = player;
    }

    public Command(String name, String argument,Player player) {
        this.name =name;
        this.argument = argument.trim();
        this.player = player;

    }
    public String getArgument() {
        return this.argument;
    }

    public String getName() {
        return name;
    }



    public static Command create(JSONObject request, Player player) {
        JSONObject jsonObject = player.getJsonObject();
        if (checkWinner(jsonObject)){
            return new TeamWonCommand(player);
        }

       if (checkState(jsonObject)){
            return new UnavailableCommand(player);
        }

        if ( "DEAD".equals(player.getOperational_state())) {

            return new EndGame("",player);
        }




            String user_command = (String) request.get("command");
            String first_argument = getCommandArguments(user_command, request);

            String entry;
            entry = first_argument;


            switch (user_command) {

                case "look":

                    return new LookCommand(player);

                case "orientation":

                    return new OrientationCommand (player);


                case "launch":
                    return new LaunchCommand(entry ,player);

                case "right":
                    return new TurnRightCommand(player);

                case "left":
                    return new TurnLeftCommand(player);

                case "forward":
                    return new ForwardCommand(entry, player);

                case "state":
                    return new StateCommand(entry, player);

                case "back":
                    return new BackCommand(entry, player);

                case "fire":
                    return new FireCommand(player);
                case "reload":
                    return new ReloadCommand(entry, player);

                case "repair":
                    return new RepairCommand(entry, player);

                default:
                    return new UnrecognisedCommand(player);
            }

        }


    private static String getCommandArguments(String user_command,JSONObject request) {

        String command_argumements = (String) request.get("arguments");
        JSONParser parser = new JSONParser();
        Object multiple_arguments = null;
        try {
            multiple_arguments = parser.parse("2");
        } catch (ParseException e) {

        }
        String first_argument = "";
        String entry = "";
        try {
            multiple_arguments = parser.parse(command_argumements);
            JSONArray jsonArray = (JSONArray) multiple_arguments;
            first_argument = (String) jsonArray.get(0);
        } catch (ParseException e) {

        }
        return first_argument;
    }
    private static boolean checkState(JSONObject jsonObject){
        String message = "";

        try {

            message = (String) jsonObject.get("message");

            if (message.equals("state not NORMAL")) {
                return true;

            }
        }catch (Exception v){
        }
        return  false;
    }

    private static boolean checkWinner(JSONObject jsonObject){
        String message = "";

        try {

            message = (String) jsonObject.get("message");

            if (message.contains("Won")) {
                return true;

            }
        }catch (Exception v){
        }
        return  false;
    }

    }

