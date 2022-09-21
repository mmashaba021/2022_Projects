package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class ShiftButton extends KeyboardAndMouse{

    @Override
    public String execute() {

        stdDraw2.setKeyNumber(0);
        return "fire";
    }
}
