package za.co.wethinkcode.examples.server.command;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.wethinkcode.examples.server.Manager;
import za.co.wethinkcode.examples.server.player.Player;
import za.co.wethinkcode.examples.server.world.FirstWorld;
import za.co.wethinkcode.examples.server.world.State;

public abstract class Command {
    private final String name;
    private String argument;
    public FirstWorld firstWorld;
    public Player player;
    public Manager manager;
    public CreateJsonObjects createJsonObjectStateClass;

    public void setCreateJsonObjectStateClass(CreateJsonObjects createJsonObjectStateClass) {
        this.createJsonObjectStateClass = createJsonObjectStateClass;
    }

    public void setFirstWorld(FirstWorld FirstWorld) {
        this.firstWorld = FirstWorld;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract boolean execute(Player player);

    public Command(String name, Player player) {

        this.name = name.toLowerCase().trim();
        this.player = player;
    }

    public Command(String name, String argument, Player player) {
        this.name = name;
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
        if (player.getManager().isTeamOneHasWon()|| player.getManager().isTeamTwoHasWon()){
            return new TeamWonCommand(player);
        }
        if (State.NORMAL.equals(player.getOperational_state())|| State.DEAD.equals(player.getOperational_state())) {

            String user_command = (String) request.get("command");
            String first_argument = getCommandArguments(user_command, request);

            String entry;
            entry = first_argument;


            switch (user_command) {

                case "launch":
                    String name = (String) request.get("robot");
                    player.setRobotName(name);
                    return new LaunchCommand(entry, player);

                case "look":
                    return new LookCommand(player);

                case "state":

                    return new StateCommand(player);
                case "right":

                    return new TurnRightCommand(player);

                case "left":
                    return new TurnLeftCommand(player);

                case "forward":

                    return new ForwardCommand(entry, player);
                case "sprint":

                case "back":
                    return new BackCommand(entry, player);

                case "fire":
                    return new FireCommand(player);

                case "repair":

                    return new RepairCommand(entry, player);

                case "reload":

                    return new ReloadCommand(entry, player);

                case "orientation":

                    return new OrientationCommand(player);

                default:
                    return new UnrecognisedCommand(player);
            }

        } else {
            return new UnavailableCommand(player);
        }

    }


    private static String getCommandArguments(String user_command, JSONObject request) {

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
}
