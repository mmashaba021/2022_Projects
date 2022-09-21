package za.co.wethinkcode.examples.server.command;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import za.co.wethinkcode.examples.server.player.Player;


public class UnrecognisedCommand extends Command implements Response{
    public UnrecognisedCommand(Player player) {
        super("unrecognized",player);
    }

    @Override
    public boolean execute(Player player) {

        player.setResponse(successfulResponse());

return true;

    }

    @Override
    public JSONObject successfulResponse() {
        JSONObject jsonMain = new JSONObject();
        JSONObject data = new JSONObject();


        data.put("message", "Unsupported command");
       jsonMain.put("result", "ERROR");
       jsonMain.put("data", data);
        return jsonMain;
    }

    @Override
    public JSONObject notSuccessfulResponse() {
        return null;
    }
}
