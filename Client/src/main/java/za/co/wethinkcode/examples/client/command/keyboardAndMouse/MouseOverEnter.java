package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class MouseOverEnter extends KeyboardAndMouse  {

    public MouseOverEnter(String enter) {
        super(enter);
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
