package za.co.wethinkcode.examples.server.command;

import org.json.simple.JSONObject;

import java.util.List;

public interface Response {


    public JSONObject successfulResponse();

    public JSONObject notSuccessfulResponse();

}

