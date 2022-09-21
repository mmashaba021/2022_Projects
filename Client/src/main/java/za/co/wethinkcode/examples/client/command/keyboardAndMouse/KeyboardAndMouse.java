package za.co.wethinkcode.examples.client.command.keyboardAndMouse;

import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.turtle2.StdDraw2;
import za.co.wethinkcode.examples.client.world.DrawInterface;
import za.co.wethinkcode.examples.client.world.FirstWorld;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

public abstract class KeyboardAndMouse {

    static StdDraw2 stdDraw2;
    static FirstWorld firstWorld;
    static DrawInterface drawInterface;
    static String key_name;
    String entered_string;
    Player player;
    int number_of_entered_keys;
    List keyboard_entries = new ArrayList<>();

    public KeyboardAndMouse(String name){

        key_name = name.toLowerCase().trim();

    }
    public static void setDrawInterface(DrawInterface drawInterface1) {
       drawInterface = drawInterface1;
    }

    public KeyboardAndMouse(){

    }
    public abstract String execute();

    public void setKeyboardEntries(List keyboard_entries) {
        this.keyboard_entries = keyboard_entries;
    }

    public static void setStdDraw2(StdDraw2 stdDraw22) {
        stdDraw2 = stdDraw22;
    }

    public void setNumber_of_entered_keys(int number_of_entered_keys) {
        this.number_of_entered_keys = number_of_entered_keys;
    }
    public void setFirstWorld(FirstWorld firstWorld){
        this.firstWorld = firstWorld;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public static String makeCase(FirstWorld firstWorld, StdDraw2 stdDraw2) {
        String output = "";
        stdDraw2.enableDoubleBuffering();
        int number_of_entered_keys = firstWorld.getNumber_of_entered_keys();

        double add2 = 0.08;
        Double add = 0.13;

        Double subract = -0.01;
        boolean check_enter = false;

        if (stdDraw2.getKeyNumber() == 10) {
            return "enter";
        } else if (stdDraw2.getKeyNumber() == 16) {
            return "shift";
        } else if (stdDraw2.getKeyNumber() == 38) {
            return "up_arrow";

        } else if (stdDraw2.getKeyNumber() == 37) {
            return "left_arrow";
        } else if (stdDraw2.getKeyNumber() == 39) {
            return "right_arrow";
        } else if (stdDraw2.getKeyNumber() == 40) {
            return "down_arrow";
        } else if (stdDraw2.getList().size() > number_of_entered_keys) {
            return "pressed_keyboard";
        } else if (stdDraw2.getMouseX() > 0.14 - (0.23 / 2) && stdDraw2.getMouseX() < 0.14 + (0.23 / 2) && stdDraw2.getMouseY() > 0.28 + add2 - (0.06 / 2) && stdDraw2.getMouseY() < 0.28 + add2 + (0.06 / 2)) {
            return "mouse_over_enter";
        } else if (stdDraw2.getMouseX() > 0.14 - (0.2 / 2) && stdDraw2.getMouseX() < 0.14 + (0.2 / 2) && stdDraw2.getMouseY() > 0.62 - subract - (0.06 / 2) && stdDraw2.getMouseY() < 0.62 - subract + (0.06 / 2)) {
            return "mouse_over_repair";
        } else if (stdDraw2.getMouseX() > 0.14 - (0.2 / 2) && stdDraw2.getMouseX() < 0.14 + (0.2 / 2) && stdDraw2.getMouseY() > 0.57 - subract - 0.02 - (0.06 / 2) && stdDraw2.getMouseY() < 0.57 - subract - 0.02 + (0.06 / 2)) {
            return "mouse_over_reload";
        } else if (stdDraw2.getMouseX() > 0.14 - (0.2 / 2) && stdDraw2.getMouseX() < 0.14 + (0.2 / 2) && stdDraw2.getMouseY() > 0.52 - subract - 0.04 - (0.06 / 2) && stdDraw2.getMouseY() < 0.52 - subract - 0.04 + (0.06 / 2)) {
            return "mouse_over_look";
        } else if (stdDraw2.getMouseX() > 0.14 - (0.2 / 2) && stdDraw2.getMouseX() < 0.14 + (0.2 / 2) && stdDraw2.getMouseY() > 0.47 - subract - 0.06 - (0.06 / 2) && stdDraw2.getMouseY() < 0.47 - subract - 0.06 + (0.06 / 2)) {
            return "mouse_over_state";
        } else if (stdDraw2.getMouseX() > 0.14 - (0.2 / 2) && stdDraw2.getMouseX() < 0.14 + (0.2 / 2) && stdDraw2.getMouseY() > 0.171 + add - (0.04 / 2) && stdDraw2.getMouseY() < 0.171 + add + (0.04 / 2)) {
            return "mouse_over_forward";
        } else if (stdDraw2.getMouseX() > 0.08 - (0.1 / 2) && stdDraw2.getMouseX() < 0.08 + (0.1 / 2) && stdDraw2.getMouseY() > 0.121 + add - (0.04 / 2) && stdDraw2.getMouseY() < 0.121 + add + (0.04 / 2)) {
            return "mouse_over_left";
        } else if (stdDraw2.getMouseX() > 0.2 - (0.1 / 2) && stdDraw2.getMouseX() < 0.2 + (0.1 / 2) && stdDraw2.getMouseY() > 0.121 + add - (0.04 / 2) && stdDraw2.getMouseY() < 0.121 + add + (0.04 / 2)) {
            return "mouse_over_right";
        } else if (stdDraw2.getMouseX() > 0.14 - (0.2 / 2) && stdDraw2.getMouseX() < 0.14 + (0.2 / 2) && stdDraw2.getMouseY() > 0.071 + add - (0.04 / 2) && stdDraw2.getMouseY() < 0.071 + add + (0.04 / 2)) {
            return "mouse_over_back";
        } else if (stdDraw2.getMouseX() > 0.14 - (0.2 / 2) && stdDraw2.getMouseX() < 0.14 + (0.2 / 2) && stdDraw2.getMouseY() > 0.021 + add - (0.05 / 2) && stdDraw2.getMouseY() < 0.021 + add + (0.05 / 2)) {
            return "mouse_over_fire";
        } else {
            return "mouse_not_over_button";
        }


    }


    public static KeyboardAndMouse create(FirstWorld firstWorld, StdDraw2 stdDraw2) {

        String input_case = makeCase(firstWorld, stdDraw2);
        switch (input_case) {

            case "enter":
                return new EnterButton();

            case "shift":
                return new ShiftButton();

            case "up_arrow":
                return new UpArrowButton();

            case "left_arrow":
                return new LeftArrowButton();

            case "right_arrow":
                return new RightArrowButton();

            case "down_arrow":
                return new DownArrowButton();

            case "pressed_keyboard":

                return new PressedKeyboard();

            case "mouse_over_enter":

                return new MouseOverEnter("enter");

            case "mouse_over_repair":

                return new MouseOverRepair("repair");

            case "mouse_over_reload":

                return new MouseOverReload("reload");

            case "mouse_over_look":

                return new MouseOverLook("look");

            case "mouse_over_state":

                return new MouseOverState("state");

            case "mouse_over_forward":

                return new MouseOverForward("forward");

            case "mouse_over_left":

                return new MouseOverLeft("left");

            case "mouse_over_right":

                return new MouseOverRight("right");

            case "mouse_over_back":

                return new MouseOverBack("back");

            case "mouse_over_fire":

                return new MouseOverFire("fire");

            default:
                return new MouseNotOverButton();
        }
    }
}