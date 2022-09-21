package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

import java.util.ArrayList;
import java.util.Arrays;

public class MouseOverForward extends KeyboardAndMouse{

    public MouseOverForward(String forward) {
        super(forward);
    }

    @Override
    public String execute() {
        drawInterface.drawMouseOverButton(key_name);

        if (stdDraw2.mousePressed()) {
            stdDraw2.setIsMousePressed(false);
            keyboard_entries= new ArrayList<>(Arrays.asList("f", "o","r","w","a","r","d"," "));
            firstWorld.setKeyboard_entries(keyboard_entries);
            firstWorld.showKeyEntries();
        }


        return null;
    }


}
