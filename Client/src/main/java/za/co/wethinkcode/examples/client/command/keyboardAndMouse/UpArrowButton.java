package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class UpArrowButton extends KeyboardAndMouse{

    @Override
    public String execute() {
        stdDraw2.setKeyNumber(0);
        if(!player.getDirection().equals("NORTH")){
            return "left";
        }


        return "forward "+firstWorld.getSpeed();
    }
}
