package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

public class PressedKeyboard extends KeyboardAndMouse{

    @Override
    public String execute() {

        number_of_entered_keys = stdDraw2.getList().size();
        firstWorld.setNumber_of_entered_keys(number_of_entered_keys);
        keyboard_entries.add(stdDraw2.getKeyz());
        firstWorld.setKeyboard_entries(keyboard_entries);
        stdDraw2.enableDoubleBuffering();

        int key = stdDraw2.getKeyNumber();
        if (key == 8) {
            stdDraw2.setKeyNumber(0);

            int index = keyboard_entries.size() - 1;

            keyboard_entries.remove(index);
            firstWorld.setKeyboard_entries(keyboard_entries);

            try {
                keyboard_entries.remove(index - 1);
                firstWorld.setKeyboard_entries(keyboard_entries);
            }catch(Exception n){
            }
        }
        else if (key == 92) {
            stdDraw2.setKeyNumber(0);
            int index = keyboard_entries.size() - 1;

            while(!stdDraw2.getIsMousePressed()) {
                drawInterface.showHelp();

            }
            stdDraw2.setIsMousePressed(false);

            try {
                keyboard_entries.remove(index - 1);
                firstWorld.setKeyboard_entries(keyboard_entries);
            }catch(Exception n){
            }
        }
        firstWorld.setKeyboard_entries(keyboard_entries);
        firstWorld.showKeyEntries();

    return "";
    }

}
