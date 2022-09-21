package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

import java.util.ArrayList;
import java.util.Arrays;

public class MouseOverBack extends KeyboardAndMouse {


    public MouseOverBack(String back) {
        super(back);
    }

    @Override
    public String execute() {

        drawInterface.drawMouseOverButton(key_name);

        if (stdDraw2.mousePressed()) {
            stdDraw2.setIsMousePressed(false);

            keyboard_entries= new ArrayList<>(Arrays.asList("b", "a","c","k"," "));
            firstWorld.setKeyboard_entries(keyboard_entries);
            firstWorld.showKeyEntries();
        }

        return null;
    }



}
