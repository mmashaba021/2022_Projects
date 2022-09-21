package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class EnterButton extends KeyboardAndMouse {
    @Override
    public String execute() {
        entered_string = firstWorld.convertListToString();
        stdDraw2.setKeyNumber(0);
        if (entered_string.contains("speed")) {
            try {

                String[] speed_setting = entered_string.split(" ", 2);
                firstWorld.setSpeed(Integer.parseInt(speed_setting[1]));

            } catch (Exception v) {
                return "";
            }
            return "";
        }


        return entered_string;
    }

}
