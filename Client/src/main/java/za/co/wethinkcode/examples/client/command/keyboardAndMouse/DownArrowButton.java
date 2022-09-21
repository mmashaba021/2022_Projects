package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class DownArrowButton extends KeyboardAndMouse{
    @Override
    public String execute() {
        stdDraw2.setKeyNumber(0);
        if(!player.getDirection().equals("SOUTH")){
            return "left";
        }
        return "forward "+firstWorld.getSpeed();
    }
    }

