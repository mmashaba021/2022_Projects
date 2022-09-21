package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class LeftArrowButton extends KeyboardAndMouse{

    @Override
    public String execute() {
        stdDraw2.setKeyNumber(0);
        if(!player.getDirection().equals("WEST")){

            return "left";
        }
        return "forward "+firstWorld.getSpeed();
}
}
