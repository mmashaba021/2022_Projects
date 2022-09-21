package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class MouseNotOverButton extends KeyboardAndMouse{

    public MouseNotOverButton() {
        super();
    }

    @Override
    public String execute() {

        drawInterface.drawNonMouseOverButton();
        return null;
    }
}
