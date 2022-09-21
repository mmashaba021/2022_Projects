package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class MouseOverRepair extends KeyboardAndMouse {

    public MouseOverRepair(String repair) {
        super(repair);
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
