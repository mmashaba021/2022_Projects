package za.co.wethinkcode.examples.client.world;
import org.turtle.StdDraw;
import za.co.wethinkcode.examples.client.obstacles.SquareObstacle;
import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.turtle2.StdDraw2;
import za.co.wethinkcode.examples.client.turtle2.Turtle2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
Manages stdDraw and methods that create key parts of the interface
 */
public class DrawInterface {
    StdDraw2 stdDraw2;
    String root_folder_images = System.getProperty("user.dir") + "/images/";
    String root_folder = System.getProperty("user.dir") + "/";
    Player player;
    FirstWorld firstWorld;

    /*
     initializes the interface
    */
    public void makeWorld() {
        stdDraw2 = new StdDraw2();
        stdDraw2.setCanvasSize(900, 600);
        stdDraw2.show();
        showState();
        firstWorld.setStdDraw(stdDraw2);

        stdDraw2.picture(0.5, 0.5, root_folder_images + "border.jpeg", 1.5, 1.5, 0);

        firstWorld.drawing_pen.setColor(Color.white);
        stdDraw2.rectangle(0.5, 0.5, 0.2, 0.4);


    }

    public void setFirstWorld(FirstWorld firstWorld) {
        this.firstWorld = firstWorld;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /*
    updates the player state on the interface
     */
    public void showState() {

        stdDraw2.setPenColor(Color.white);
        stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 28));
        stdDraw2.picture(0.2, 0.96, root_folder_images + "bord3.jpg", 1.9, 0.39, 0);
        stdDraw2.text(0.18, 0.97, "Robot Worlds");


        double x = player.getPosition().getX();
        double y = player.getPosition().getY();
        stdDraw2.setPenColor(Color.blue);
        String direction = player.getDirection();
        stdDraw2.setFont();


        drawCheckReloadRepair();
        drawStateBorders();
        drawStateText(direction, x, y);
        drawStateImages();
        drawActionLog();
        drawMovementButtons();
        drawHelpButton();
        drawMostRecentAction();
        drawFoundObstacles();

    }

    private void drawCheckReloadRepair() {
        stdDraw2.setPenColor(Color.green);
        if (player.getOperational_state().equals("RELOAD") || player.getOperational_state().equals("DEAD")) {
            stdDraw2.setPenColor(Color.red);
        } else if (player.getOperational_state().equals("REPAIR")) {
            {
                stdDraw2.setPenColor(Color.gray);
            }
        }
    }

    private void drawStateBorders() {
        stdDraw2.filledRectangle(0.175, 0.882, 0.1, 0.023);
        stdDraw2.setPenColor(Color.darkGray);
        stdDraw2.filledRectangle(0.175, 0.832, 0.1, 0.023); //to work this out you have to take 0.002*400/2 and 0.002*200/2 because rectangle wants half the width and half length
        stdDraw2.setPenColor(Color.white);
        stdDraw2.filledRectangle(0.175, 0.78, 0.1, 0.023); //to work this out you have to take 0.002*400/2 and 0.002*200/2 because rectangle wants half the width and half length
        stdDraw2.setPenColor(Color.white);
        stdDraw2.filledRectangle(0.83, 0.83, 0.1, 0.023);

    }

    private void drawStateText(String direction, double x, double y) {
        int sheild = (int) player.getSheild();
        int shots = (int) player.getShots();
        int pos_x = (int) x;
        int pos_y = (int) y;


        stdDraw2.setPenColor(Color.blue);
        stdDraw2.textLeft(0.09, 0.88, "Status: " + player.getOperational_state());
        stdDraw2.textLeft(0.09, 0.83, "Shield: " + sheild);
        stdDraw2.textLeft(0.09, 0.78, "Shots: " + shots);
        stdDraw2.textLeft(0.73, 0.88, "Direction: " + direction);
        stdDraw2.textLeft(0.73, 0.83, "Position: (" + pos_x + "," + pos_y + ")");

    }

    private void drawStateImages() {
        stdDraw2.picture(0.05, 0.83, root_folder_images + "shield.jpg", 0.05, 0.05, 0);
        stdDraw2.picture(0.05, 0.78, root_folder_images + "gun.jpg", 0.05, 0.05, 0);
        if (player.getOperational_state().equals("RELOAD")) {
            stdDraw2.picture(0.05, 0.88, root_folder_images + "pause.png", 0.05, 0.05, 0);
        } else if (player.getOperational_state().equals("REPAIR")) {
            stdDraw2.picture(0.05, 0.88, root_folder_images + "repair2.png", 0.05, 0.05, 0);
        } else if (player.getOperational_state().equals("DEAD")) {
            stdDraw2.picture(0.05, 0.88, root_folder_images + "dead.png", 0.05, 0.05, 0);
        } else {
            stdDraw2.picture(0.05, 0.88, root_folder_images + "normal.jpg", 0.05, 0.05, 0);

        }
        stdDraw2.picture(0.80, 0.755, root_folder_images + "compass.jpg", 0.14, 0.09, 0);

    }

    private void drawActionLog() {
        stdDraw2.setPenColor(Color.white);
        //stdDraw2.filledRectangle(0.85,0.4,0.12,0.2);
        stdDraw2.picture(0.85, 0.4, root_folder_images + "bord3.jpg", 0.24, 0.4);
        stdDraw2.setPenColor(Color.red);
        stdDraw2.filledRectangle(0.85, 0.62, 0.12, 0.03);
        stdDraw2.setPenColor(Color.darkGray);
        stdDraw2.filledRectangle(0.85, 0.59, 0.12, 0.005);
        stdDraw2.filledRectangle(0.85, 0.655, 0.12, 0.005);
        stdDraw2.setPenColor(Color.white);
        stdDraw2.text(0.82, 0.62, "ACTIONS LOG");


        stdDraw2.setPenColor(Color.black);
        List<String> actions = player.getActions_list();
        double list_output_x = 0.731;
        double list_output_y = 0.57;

        double y_changer = 0.03;
        stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 11));
        for (int i = actions.size() - 1; i > -1; i--) {
            if (!actions.get(i).equals("")) {
                stdDraw2.textLeft(list_output_x, list_output_y, "" + actions.get(i));
                list_output_y = list_output_y - y_changer;
            }
        }
    }

    private void drawHelpButton() {

        stdDraw2.setPenColor(Color.white);
        stdDraw2.setFont(new Font("TimesRoman", Font.BOLD, 15));
        stdDraw2.textLeft(0.73, 0.97, "Press \\ for help ");

    }

    private void drawMovementButtons() {


        String button = "button10.png";

        stdDraw2.setPenColor(Color.white);
        stdDraw2.setFont(new Font("TimesRoman", Font.BOLD, 15));

        Double add = 0.13;
        stdDraw2.picture(0.14, 0.171 + add, root_folder_images + button, 0.2, 0.04, 0);
        stdDraw2.text(0.14, 0.168 + add, "FORWARD");


        stdDraw2.picture(0.08, 0.121 + add, root_folder_images + button, 0.1, 0.04, 0);
        stdDraw2.text(0.08, 0.118 + add, "LEFT");


        stdDraw2.picture(0.2, 0.121 + add, root_folder_images + button, 0.1, 0.04, 0);
        stdDraw2.text(0.2, 0.118 + add, "RIGHT");

        stdDraw2.picture(0.14, 0.071 + add, root_folder_images + button, 0.2, 0.04, 0);
        stdDraw2.text(0.14, 0.068 + add, "BACK");

        stdDraw2.picture(0.14, 0.021 + add, root_folder_images + button, 0.2, 0.05, 0);
        stdDraw2.text(0.14, 0.018 + add, "FIRE");

        drawActionButtons();
        double add2 = 0.08;
        stdDraw2.setPenColor(Color.black);
        stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        stdDraw2.picture(0.14, 0.28 + add2, root_folder_images + button, 0.23, 0.06, 0);
        stdDraw2.text(0.14, 0.28 + add2, "ENTER");

    }

    private void drawActionButtons() {

        String button2 = "button10.png";

        Double subract = -0.01;
        stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        stdDraw2.setPenColor(Color.black);

        stdDraw2.picture(0.14, 0.62 - subract, root_folder_images + button2, 0.2, 0.06, 0);

        stdDraw2.text(0.14, 0.62 - subract, "REPAIR");

        stdDraw2.picture(0.14, 0.57 - subract - 0.02, root_folder_images + button2, 0.2, 0.06, 0);
        stdDraw2.text(0.14, 0.57 - subract - 0.02, "RELOAD");

        stdDraw2.picture(0.14, 0.52 - subract - 0.04, root_folder_images + button2, 0.2, 0.06, 0);
        stdDraw2.text(0.14, 0.52 - subract - 0.04, "LOOK");

        stdDraw2.picture(0.14, 0.47 - subract - 0.06, root_folder_images + button2, 0.2, 0.06, 0);
        stdDraw2.text(0.14, 0.47 - subract - 0.06, "STATE");

    }

    private void drawMostRecentAction() {
        stdDraw2.setFont(new Font("TimesRoman", Font.BOLD, 15));
        stdDraw2.setPenColor(Color.black);
        stdDraw2.text(0.5, 0.025, firstWorld.most_recent_action);

    }

    public FirstWorld getFirstWorld() {
        return firstWorld;
    }

    private void drawFoundObstacles() {

        try {
            for (int i = 0; i < firstWorld.updated_obstacles_found_normal.size(); i++) {

                SquareObstacle squareObstacle = (SquareObstacle) firstWorld.updated_obstacles_found_normal.get(i);
                SquareObstacle squareObstacle2 = (SquareObstacle) firstWorld.updated_obstacles_found.get(i);
                double x = squareObstacle.getBottomLeftX();
                double y = squareObstacle.getBottomLeftY();
                String image = squareObstacle2.getImage_location();

                if (squareObstacle.obstacle_inbounds(firstWorld.Border_TOP_LEFT, firstWorld.Border_BOTTOM_RIGHT)) {
                    double x_border = firstWorld.Border_BOTTOM_RIGHT.getX();
                    double y_border = firstWorld.Border_BOTTOM_RIGHT.getY();

                    /*the obstalce must be drawn realtive to the border window that is moving
                    so it needs to get the value of the difference so it knows how much to add to the border
                    value
                    */
                    double x_difference = Math.abs(x_border - x);
                    double y_difference = Math.abs(y_border - y);

                    // here i add the steps of the differnce to the bottom edge and the left edge
                    double x_obstacle = 0.5 + (0.002 * 100) - (0.002 * x_difference);
                    double y_obstacle = (0.002 * y_difference) + 0.5 - (0.002 * 200);

                    double x8 = 100 - x_difference;
                    double y8 = y_difference - 200;

                    stdDraw2.picture(x_obstacle + 0.004, y_obstacle + 0.004, image, 0.03, 0.03, 0);

                }


            }
        } catch (Exception l) {
        }
    }

    public void showHelp() {
        stdDraw2.picture(0.5, 0.5, root_folder_images + "border.jpeg", 1.5, 1.5, 0);
        String help1 = "Help screen:\n";
        String help2 = "Robot World commands:\n";
        String help3 = "how to use a command\n";
        String help4 = " click on any of the buttons on the left side of the screen\n";
        String help5 = " or use arrow keys to navigate. hold down arrow key to move in specified direction\n";
        String help7 = " or type the name of the command out and press enter\n";
        String help8 = "Commands:\n";
        String help9 = "-Forward - Moves robot forward in the directioin it is facting - requires number to ";
        String help91 = " be entered when typed or clicked. The entered number is the number of steps you want to move";
        String help10 = "-Back -  Moves robot back in the  opposite direction it is facting - requires number to be ";
        String help101 = " entered when typed or clicked. The entered number is the number of steps you want to move";
        String help11 = "-Left - turns robot left";
        String help12 = "-Right - turns robot right\n";
        String help13 = "-Look - looks North, east, west and south in a straight line and detects any ";
        String help131 = " nearby obstacles or robot or edges";
        String help14 = "-State - updates the state of the robot\n";
        String help15 = "-Orientation - checks the direction the robot is facing\n";
        String help17 = "-Reload - reload the robot ammo. Reloading has a waiting time\n";
        String help18 = "-Repair - repairs the robots shield. Repairing has a waiting time\n";
        String help19 = "-Fire - fires a shot. Each robot type can fire a shot with a - Shift key also lets you fire ";
        String help191 = " limited distance. (distance displayed on robot type screen at start of game)";
        String help20 = "Close the window to quit the game\n";
        String help21 = "To change speed of foward when using arrows, enter 'speed' and the number of steps\n";
        String help22 = "Eg. 'speed 10' will make robot move 10 steps per an arrow movement rather than 1";
        stdDraw2.setPenColor(Color.black);
        stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        stdDraw2.text(0.5, 0.97, "click on window to go back");
        stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 12));

        double x = 0.05;
        double y = 0.9;
        double difference = 0.03;
        stdDraw2.textLeft(x, y, help1);
        y = y - difference;
        stdDraw2.textLeft(x, y, help2);
        y = y - difference;
        stdDraw2.textLeft(x, y, help3);
        y = y - difference;
        stdDraw2.textLeft(x, y, help4);
        y = y - difference;
        stdDraw2.textLeft(x, y, help5);
        y = y - difference;
        stdDraw2.textLeft(x, y, help7);
        y = y - difference;
        stdDraw2.textLeft(x, y, help8);
        y = y - difference;
        stdDraw2.textLeft(x, y, help9);
        y = y - difference;
        stdDraw2.textLeft(x, y, help91);
        y = y - difference;
        stdDraw2.textLeft(x, y, help10);
        y = y - difference;
        stdDraw2.textLeft(x, y, help101);
        y = y - difference;
        stdDraw2.textLeft(x, y, help11);
        y = y - difference;
        stdDraw2.textLeft(x, y, help12);
        y = y - difference;
        stdDraw2.textLeft(x, y, help13);
        y = y - difference;
        stdDraw2.textLeft(x, y, help131);
        y = y - difference;
        stdDraw2.textLeft(x, y, help14);
        y = y - difference;
        stdDraw2.textLeft(x, y, help15);
        y = y - difference;
        stdDraw2.textLeft(x, y, help17);
        y = y - difference;
        stdDraw2.textLeft(x, y, help18);
        y = y - difference;
        stdDraw2.textLeft(x, y, help19);
        y = y - difference;
        stdDraw2.textLeft(x, y, help191);
        y = y - difference;
        stdDraw2.textLeft(x, y, help20);
        y = y - difference;
        stdDraw2.textLeft(x, y, help21);
        y = y - difference;
        stdDraw2.textLeft(x, y, help22);


        stdDraw2.show();

    }


    public void repair() {
        int count = firstWorld.repair_time;
        for (int i = count; i > -1; i--) {

            reset(player.getTurtle());

            stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            stdDraw2.setPenColor(Color.blue);

            stdDraw2.text(0.5, 0.5, String.valueOf(i));
            stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            stdDraw2.text(0.5, 0.4, "Repairing");


            stdDraw2.setFont(new Font("TimesRoman", Font.BOLD, 15));
            stdDraw2.setPenColor(Color.red);
            stdDraw2.show();
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception k) {
            }
        }

    }

    public void obstructed() {
        for (int i = 50; i > 25; i--) {

            reset(player.getTurtle());
            stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, i));
            stdDraw2.setPenColor(Color.red);
            stdDraw2.text(0.5, 0.35, "OBSTRUCTED");
            stdDraw2.picture(0.5, 0.5, root_folder_images + "blocked.png", 0.15, 0.15, 0);


            stdDraw2.setFont(new Font("TimesRoman", Font.BOLD, 15));
            stdDraw2.setPenColor(Color.red);
            stdDraw2.show();
            try {
                TimeUnit.MILLISECONDS.sleep(0);
            } catch (Exception k) {
            }
        }
    }


    public void drawTurtle() {
        Turtle2 turtle = player.getTurtle();
        double[] position = turtle.getPosition();
        double x0 = position[0];
        double y0 = position[1];
        double z0 = turtle.getAngle();
        stdDraw2.picture2(x0, y0, root_folder_images + firstWorld.robot_image, firstWorld.width, firstWorld.height, turtle.getAngle());

    }

    public void explodegif(double x1, double y1, Turtle2 turtle1) {
        double[] position = turtle1.getPosition();
        double x0 = position[0];
        double y0 = position[1];

        try {
            firstWorld.playfire(root_folder + "sound/explode.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        stdDraw2.enableDoubleBuffering();
        for (int i = 1; i < 21; i++) {
            reset(turtle1);
            stdDraw2.picture2(x0, y0, root_folder_images + firstWorld.robot_image, firstWorld.width, firstWorld.height, turtle1.getAngle());

            stdDraw2.picture(x1, y1, root_folder + "gifs/explosion/explode" + i + ".png", 0.05, 0.07, turtle1.getAngle());

            stdDraw2.show();
            try {
                TimeUnit.MILLISECONDS.sleep(60);
            } catch (Exception k) {
            }
        }

    }


    public void reset(Turtle2 turtle) {


        stdDraw2.picture(0.5, 0.5, root_folder_images + "border.jpeg", 1.5, 1.5, 0);

        showState();
        turtle.setColor(Color.white);
        stdDraw2.rectangle(0.5, 0.5, 0.2, 0.4);

    }

    public void drawEndGame() {


        stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        stdDraw2.setPenColor(firstWorld.col);
        stdDraw2.text(0.5, 0.5, "ROBOT DEAD - GAME OVER - Game will disconnect");
        stdDraw2.show();
    }

    public void drawNonMouseOverButton() {

        firstWorld.showKeyEntries();

        stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        stdDraw2.setPenColor(firstWorld.col);
        stdDraw2.text(0.14, 0.28 + firstWorld.add2, "ENTER");
        stdDraw2.text(0.14, 0.57 - firstWorld.subtract - 0.02, "RELOAD");
        stdDraw2.text(0.14, 0.62 - firstWorld.subtract, "REPAIR");
        stdDraw2.text(0.14, 0.47 - firstWorld.subtract - 0.06, "STATE");
        stdDraw2.text(0.14, 0.52 - firstWorld.subtract - 0.04, "LOOK");

        stdDraw2.setPenColor(firstWorld.col2);
        stdDraw2.setFont(new Font("TimesRoman", Font.BOLD, 15));
        stdDraw2.text(0.14, 0.168 + firstWorld.add, "FORWARD");
        stdDraw2.text(0.08, 0.118 + firstWorld.add, "LEFT");
        stdDraw2.text(0.2, 0.118 + firstWorld.add, "RIGHT");
        stdDraw2.text(0.14, 0.068 + firstWorld.add, "BACK");
        stdDraw2.text(0.14, 0.018 + firstWorld.add, "FIRE");

        drawTurtle();
        stdDraw2.show();


    }

    public void reload() {
        for (int i = firstWorld.reload_time; i > -1; i--) {

            reset(player.getTurtle());

            stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            stdDraw2.setPenColor(Color.red);

            stdDraw2.text(0.5, 0.5, String.valueOf(i));
            stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            stdDraw2.text(0.5, 0.4, "Reloading");


            stdDraw2.setFont(new Font("TimesRoman", Font.BOLD, 15));
            stdDraw2.setPenColor(Color.red);
            stdDraw2.show();
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception k) {
            }
        }

    }

    public void drawMouseOverButton(String buttonName) {


        String button_text = firstWorld.buttonProperties.getProperty(buttonName + "_button");
        double x = 0;
        if (buttonName.equals("left")) {
            x = (Double.parseDouble((firstWorld.buttonProperties.getProperty("button_x_Left"))));
        } else if (buttonName.equals("right")) {
            x = (Double.parseDouble((firstWorld.buttonProperties.getProperty("button_x_right"))));
        } else {
            x = (Double.parseDouble((firstWorld.buttonProperties.getProperty("button_x_AllOtherButtons"))));
        }

        double y = (Double.parseDouble((firstWorld.buttonProperties.getProperty(buttonName + "_button_y"))));

        List group_1_buttons = new ArrayList<>(Arrays.asList("forward", "left", "right", "back", "fire"));

        if (group_1_buttons.contains(buttonName)) {
            y = y + firstWorld.add;
            stdDraw2.setFont(new Font("TimesRoman", Font.BOLD, 15));
        } else if (buttonName.equals("enter")) {
            stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            y = y + firstWorld.add2;
        } else {
            y = y - firstWorld.subtract;
            stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        }


        stdDraw2.setPenColor(Color.red);
        stdDraw2.text(x, y, button_text);


        drawTurtle();

        stdDraw2.show();

    }

    public void Winner() {
        stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        stdDraw2.setPenColor(Color.red);
        stdDraw2.text(0.5, 0.2, "no ememies remaining");
        stdDraw2.show();


        for (int x = 0; x < 10; x++) {
            for (int i = 10; i < 100; i++) {

                reset(player.getTurtle());
                stdDraw2.setFont(new Font("TimesRoman", Font.PLAIN, i));
                stdDraw2.setPenColor(Color.red);
                stdDraw2.text(0.5, 0.5, player.getWinner());
                stdDraw2.show();
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
