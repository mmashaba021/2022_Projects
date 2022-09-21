package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class MouseOverLeft extends KeyboardAndMouse{

    public MouseOverLeft(String left) {
        super(left);
    }

    @Override
    public String execute() {
        drawInterface.drawMouseOverButton(key_name);

        if (stdDraw2.mousePressed()) {
            stdDraw2.setIsMousePressed(false);
            return key_name;

        }
        return null;
    }


}
