package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class MouseOverFire extends  KeyboardAndMouse {

    public MouseOverFire(String fire) {
        super(fire);
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
