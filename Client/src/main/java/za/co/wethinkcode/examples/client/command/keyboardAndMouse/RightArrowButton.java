package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class RightArrowButton extends KeyboardAndMouse{

    @Override
    public String execute() {
        stdDraw2.setKeyNumber(0);
        if(!player.getDirection().equals("EAST")){
            return "left";
        }


        return "forward "+firstWorld.getSpeed();
    }
}
