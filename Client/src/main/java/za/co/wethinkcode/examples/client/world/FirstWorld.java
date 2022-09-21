package za.co.wethinkcode.examples.client.world;



import za.co.wethinkcode.examples.client.Client;
import za.co.wethinkcode.examples.client.command.keyboardAndMouse.KeyboardAndMouse;
import za.co.wethinkcode.examples.client.effects.Sounds;
import za.co.wethinkcode.examples.client.obstacles.SquareObstacle;
import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.turtle2.StdDraw2;
import za.co.wethinkcode.examples.client.turtle2.Turtle2;


import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

//this class is responsible for managing all variables tied to the creation and maintenance of the robot world
public class FirstWorld  {
    double a0 = 90.0D;
    Turtle2 drawing_pen = new Turtle2(0.3D, 0.1D, a0);
    List obstacles_found;
    List updated_obstacles_found;
    List updated_obstacles_found_normal;
    Player player;
    String root_folder_images = System.getProperty("user.dir") + "/images/";
    String root_folder = System.getProperty("user.dir") + "/";
    static Position Border_TOP_LEFT = new Position(-100, 200);
    static Position Border_BOTTOM_RIGHT = new Position(100, -200);
    double keyboard_entry_spacing = 0.05;
    static Properties properties;
    static Properties buttonProperties;
    int repair_time;
    int reload_time;
    int visbility;
    int default_shots_amount_long_range;
    int default_shots_amount_short_range;
    double width;
    double add_button_coordinate;
    double subtract_button_coordinate;
    Color col = Color.black;
    String notificaton;
    Client client;
    Color col2 = Color.white;

    double height;
    double long_range_width = 0.08;
    double long_range_height = 0.03;
    double add2 = 0.08;
    Double add = 0.13;

    Double subtract = -0.01;


    double short_range_width = 0.05;
    double short_range_height = 0.05;


    String robot_image;
    String long_range_image = "tank.png";
    String short_range_image = "spaceship2.png";

    String most_recent_action = "";

    int default_shots_distance_long_range;
    int default_shots_distance_short_range;

    int default_shield;
    List keyboard_entries;
    int speed = 1;


    StdDraw2 stdDraw2;



    static int number_of_entered_keys;



    DrawInterface drawInterface;



    // int number_of_entered_keys = 0;
    public FirstWorld() {

        properties = new Properties();
        try (FileInputStream config_file = new FileInputStream(root_folder + "config.properties")) {
            properties.load(config_file);
        } catch (Exception b) {

        }


        buttonProperties = new Properties();
        try (FileInputStream config_file = new FileInputStream(root_folder + "button.properties")) {
            buttonProperties.load(config_file);
        } catch (Exception b) {

        }

        default_shots_amount_short_range = (Integer.parseInt((properties.getProperty("number_of_shots_type_shortrange"))));
        default_shots_amount_long_range = (Integer.parseInt((properties.getProperty("number_of_shots_type_longrange"))));
        default_shots_distance_short_range = (Integer.parseInt((properties.getProperty("shot_distance_type_shortrange"))));
        default_shots_distance_long_range = (Integer.parseInt((properties.getProperty("shot_distance_type_longrange"))));


    }

    public String getNotificaton() {
        return notificaton;
    }

    public void setNotificaton(String notificaton) {
        this.notificaton = notificaton;
    }

    public void setDrawInterface(DrawInterface drawInterface) {
        this.drawInterface = drawInterface;
    }
    public static int getNumber_of_entered_keys() {
        return number_of_entered_keys;
    }


    public static void setNumber_of_entered_keys(int number_of_entered_keys1) {
        number_of_entered_keys = number_of_entered_keys1;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setRobot_image(String robot_image) {
        this.robot_image = robot_image;
    }

    public String getLong_range_image() {
        return long_range_image;
    }

    public String getShort_range_image() {
        return short_range_image;
    }


    public void setMost_recent_action(String most_recent_action) {
        this.most_recent_action = most_recent_action;
    }

    public String getMost_recent_action() {
        return most_recent_action;
    }

    public int getDefault_shots_distance_long_range() {
        return default_shots_distance_long_range;
    }

    public int getDefault_shots_distance_short_range() {
        return default_shots_distance_short_range;
    }

    public double getLong_range_width() {
        return long_range_width;
    }

    public double getLong_range_height() {
        return long_range_height;
    }

    public double getShort_range_width() {
        return short_range_width;
    }

    public double getShort_range_height() {
        return short_range_height;
    }

    public int getDefault_shots_amount_long_range() {
        return default_shots_amount_long_range;
    }

    public int getDefault_shield() {
        return default_shield;
    }

    public void setDefault_shield(int default_shield) {
        this.default_shield = default_shield;
    }

    public int getDefault_shots_amount_short_range() {
        return default_shots_amount_short_range;
    }

    public void setRepair_time(int repair_time) {
        this.repair_time = repair_time;
    }

    public void setReload_time(int reload_time) {
        this.reload_time = reload_time;
    }

    public void setVisbility(int visbility) {
        this.visbility = visbility;
    }

    //The method updates the StdDraw screen and draws the text labels
    /*
    the method updates the program and keeps drawing over the old labels so that the fields are all reset
    and every label is kept up to date. this creates the interface and keeps it updated
     */




    /*launches the robot
    */
    public void launch_robot(Turtle2 turtle1, Color color1) {

        double[] position = turtle1.getPosition();
        double x1 = position[0];
        double y1 = position[1];
        stdDraw2.picture2(x1, y1, root_folder_images + robot_image, width, height, turtle1.getAngle());
        drawInterface.showState();

        ;
    }


    public void setObstacles_found(List obstacles) {
        obstacles_found = obstacles;
    }

    public List getObstacles_found() {
        return obstacles_found;
    }



    /*a list of potential obstacles.
    they are picked randomly and a random object
    apprears on the map to represent the obstacle rather than drawing a square obstacle
    */
    public void CheckFoundObstacles() {


        List<String> list_images = new ArrayList<String>(Arrays.asList("rock.jpeg", "rocks2.jpg", "tree2.jpeg", "tree.jpg"));
        List found_obstacles = new ArrayList<>();
        List found_obstacles_normal = new ArrayList<>();
        Random rand = new Random();
        int random_number1 = rand.nextInt(4);
        try {
            if (obstacles_found.size() > 0) {
                for (int i = 0; i < obstacles_found.size(); i++) {


                    //first the obstacle coordiantes are taken from the list
                    List first_obstacle = (List) obstacles_found.get(i);
                    String type = (String) first_obstacle.get(0);
                    String distance = (String) first_obstacle.get(1);
                    String direction = (String) first_obstacle.get(2);
                    double x1 = player.getPosition().getX();
                    double y1 = player.getPosition().getY();
                    if (type.equals("Obstacles")) {

                        if (direction.equals("south")) {

                            y1 = y1 - Double.parseDouble(distance) - 2;
                            x1 = x1 - 2;
                        } else if (direction.equals("north")) {
                            y1 = y1 + Double.parseDouble(distance);
                            x1 = x1 - 2;
                        } else if (direction.equals("east")) {
                            y1 = y1 - 2;
                            x1 = x1 + Double.parseDouble(distance);
                        } else if (direction.equals("west")) {
                            y1 = y1 - 2;
                            x1 = x1 - Double.parseDouble(distance) - 2;
                        }

                        String image = root_folder + "obstacles_images/" + list_images.get(random_number1);
                        SquareObstacle squareNormalPosition = new SquareObstacle(x1, y1 );
                        SquareObstacle squareObstacle = new SquareObstacle(0.5 + (x1 * 0.002), 0.5 + (y1 * 0.002));
                        squareObstacle.setImage_location(image);

                        found_obstacles.add(squareObstacle);
                        found_obstacles_normal.add(squareNormalPosition);

                    }
                }
                updated_obstacles_found = found_obstacles;
                updated_obstacles_found_normal = found_obstacles_normal;
            }
        } catch (NullPointerException e) {
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void playfire(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Runnable r = new Sounds(file);
        Thread thread = new Thread(r);
        thread.start();

    }


    public void forward(double steps) {

        drawing_pen.forward(0.002 * steps);
    }

    public void forward(double steps, Turtle2 turtle1) {

        turtle1.forward(0.002 * steps);
    }


    public void fire(double steps, Turtle2 turtle1) {

        double[] position = turtle1.getPosition();
        double x0 = position[0];
        double y0 = position[1];
        double z0 = turtle1.getAngle();
        double x1 = position[0];
        double y1 = position[1];
        double x2;
        double y2;
        stdDraw2.enableDoubleBuffering();
        Position position_checked;
        Turtle2 turtle2 = new Turtle2(x0, y0, z0);
        for (double x = 0; x < steps; x++) {
            turtle2.setColor(Color.blue);
            turtle2.forward(0.002);


            if (turtle1.getAngle() == 90) {
                y1 = y1 + 0.002;
            } else if (turtle1.getAngle() == 0) {
                x1 = x1 + 0.002;
            } else if (turtle1.getAngle() == 270) {

                y1 = y1 - 0.002;

            } else if (turtle1.getAngle() == 180) {
                x1 = x1 - 0.002;
            }

            drawInterface.reset(turtle1);

            stdDraw2.picture2(x0, y0, root_folder_images + robot_image, width, height, turtle1.getAngle());

            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (Exception k) {
            }

        }

        drawInterface.explodegif(x1, y1, turtle1);


    }


    public void forward(double steps, Turtle2 turtle1, Color color) {

        double[] position = turtle1.getPosition();
        double x1 = position[0];
        double y1 = position[1];
        stdDraw2.enableDoubleBuffering();
        Position position_checked;

        for (double x = 0; x < steps; x++) {
            turtle1.forward(0.002);
            position_checked = check_if_beyond_border(turtle1);
            x1 = position_checked.getX();
            y1 = position_checked.getY();


            if (turtle1.getAngle() == 90) {
                y1 = y1 + 0.002;
            } else if (turtle1.getAngle() == 360 || turtle1.getAngle() == 0) {
                x1 = x1 + 0.002;
            } else if (turtle1.getAngle() == 270) {

                y1 = y1 - 0.002;
            } else if (turtle1.getAngle() == 180) {
                x1 = x1 - 0.002;
            }

            drawInterface.reset(turtle1);
            turtle1.setColor(Color.white);


            turtle1.setColor(color);

            stdDraw2.picture2(x1, y1, root_folder_images + robot_image, width, height, turtle1.getAngle());

//            try {
//                TimeUnit.MILLISECONDS.sleep(0);
//            } catch (Exception k) {
//            }
        }

    }

    public Position check_if_beyond_border(Turtle2 turtle) {
        double[] position = turtle.getPosition();
        double x1 = position[0];
        double y1 = position[1];

        double x_border_top = Border_TOP_LEFT.getX();
        double y_border_top = Border_TOP_LEFT.getY();

        double x_border_bottom = Border_BOTTOM_RIGHT.getX();
        double y_border_bottom = Border_BOTTOM_RIGHT.getY();


        DecimalFormat df = new DecimalFormat("#.######");
        x1 = Double.parseDouble(df.format(x1));
        y1 = Double.parseDouble(df.format(y1));


        if (x1 == limit(0.5 + (0.002 * 101))) {
            x1 = limit(0.5 + (-99 * 0.002));
            x_border_bottom = x_border_bottom + 200;
            x_border_top = x_border_top + 200;

        } else if (y1 == limit(0.5 + (0.002 * 201))) {
            y1 = limit(0.5 + (-199 * 0.002));
            y_border_bottom = y_border_bottom + 200;
            y_border_top = y_border_top + 200;

        } else if (x1 == limit(0.5 + (-0.002 * 101))) {
            x1 = limit(0.5 + (99 * 0.002));
            x_border_bottom = x_border_bottom - 200;
            x_border_top = x_border_top - 200;
        } else if (y1 == limit(0.5 + (0.002 * -201))) {
            y1 = limit(0.5 + (0.002 * 199));
            y_border_bottom = y_border_bottom - 200;
            y_border_top = y_border_top - 200;
        }
        turtle.setPosition(x1, y1);
        Position positionfinal = new Position(x1, y1);


        Position topleft = new Position(x_border_top, y_border_top);
        Position bottomright = new Position(x_border_bottom, y_border_bottom);

        Border_BOTTOM_RIGHT = bottomright;
        Border_TOP_LEFT = topleft;


        return positionfinal;
    }

    public double limit(double var) {
        DecimalFormat df = new DecimalFormat("#.######");
        Double var2 = Double.parseDouble(df.format(var));
        return var2;
    }

    public void back(double steps, Turtle2 turtle1, Color color) {

        double[] position = turtle1.getPosition();
        double x1;
        double y1;

        stdDraw2.enableDoubleBuffering();
        Position position_checked;

        for (double x = 0; x < steps; x++) {

            turtle1.backward(0.002);
            position_checked = check_if_beyond_border(turtle1);
            x1 = position_checked.getX();
            y1 = position_checked.getY();

            if (turtle1.getAngle() == 90) {
                y1 = y1 - 0.002;
            } else if (turtle1.getAngle() == 360) {
                x1 = x1 - 0.002;
            } else if (turtle1.getAngle() == 270) {

                y1 = y1 + 0.002;

            } else if (turtle1.getAngle() == 180) {
                x1 = x1 + 0.002;
            }

            drawInterface.reset(turtle1);
            turtle1.setColor(Color.white);

            turtle1.setColor(color);

            stdDraw2.picture2(x1, y1, root_folder_images + robot_image, width, height, turtle1.getAngle());

            try {
                TimeUnit.MILLISECONDS.sleep(0);
            } catch (Exception k) {
            }
        }
    }

    public DrawInterface getDrawInterface(){
        return drawInterface;
    }
    public void left() {
        drawing_pen.left(90.0);
    }

    public void left(Turtle2 turtle) {
        stdDraw2.enableDoubleBuffering();
        double[] position = turtle.getPosition();
        double x1 = position[0];
        double y1 = position[1];
        turtle.left(90.0);

        if (turtle.getAngle() > 270) {
            turtle.setAngle(0);

        } else if (turtle.getAngle() < 0) {
            turtle.setAngle(270);
        }

        drawInterface.reset(turtle);
        turtle.setColor(Color.white);


        stdDraw2.picture2(x1, y1, root_folder_images + robot_image, width, height, turtle.getAngle());
        stdDraw2.show();
    }

    public void right(Turtle2 turtle) {

        stdDraw2.enableDoubleBuffering();
        double[] position = turtle.getPosition();
        double x1 = position[0];
        double y1 = position[1];
        turtle.right(90.0);
        if (turtle.getAngle() > 270) {
            turtle.setAngle(0);

        } else if (turtle.getAngle() < 0) {
            turtle.setAngle(270);
        }
        drawInterface.reset(turtle);
        turtle.setColor(Color.white);

        stdDraw2.picture2(x1, y1, root_folder_images + robot_image, width, height, turtle.getAngle());
        stdDraw2.show();

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int setting) {
        speed = setting;
    }

    public String getTurtleInput() {
        number_of_entered_keys = stdDraw2.getList().size();
        String output = "";
        stdDraw2.enableDoubleBuffering();
        Turtle2 turtle = player.getTurtle();
        double[] position = player.getTurtle().getPosition();
        double x1 = position[0];
        double y1 = position[1];

        stdDraw2.picture2(x1, y1, root_folder_images + robot_image, width, height, turtle.getAngle());
        stdDraw2.show();


        boolean check_enter = false;

        keyboard_entries = new ArrayList<>();
        int number_of_entered_keys = stdDraw2.getList().size();

        while (!stdDraw2.mousePressed() || check_enter == false) {
            KeyboardAndMouse keyboardAndMouse = KeyboardAndMouse.create(this, stdDraw2);
            keyboardAndMouse.setFirstWorld(this);
            keyboardAndMouse.setDrawInterface(drawInterface);
            keyboardAndMouse.setNumber_of_entered_keys(number_of_entered_keys);
            keyboardAndMouse.setKeyboardEntries(keyboard_entries);
            keyboardAndMouse.setStdDraw2(stdDraw2);
            keyboardAndMouse.setPlayer(player);
            output = keyboardAndMouse.execute();
           try {
               if (!output.equals("")) {
                   return output;

               }
           }catch (Exception n){

           }
        }
        return output;
        }

    public void setKeyboard_entries(List keyboard_entries){
         this.keyboard_entries = keyboard_entries;
    }


    public String convertListToString() {

        StringBuilder final_string = new StringBuilder();

        int i = 0;
        while (i < keyboard_entries.size())
        {
                    try {
            final_string.append(keyboard_entries.get(i));
        }catch (Exception c){
            return "";
        }

            i++;
        }

        String string = final_string.toString();
        return string;
    }


    public void showKeyEntries() {
        stdDraw2.enableDoubleBuffering();
        drawInterface.reset(player.getTurtle());
        drawInterface.drawTurtle();
        stdDraw2.setPenColor(Color.black);
        double x = keyboard_entry_spacing;
        for (int i = 0; i < keyboard_entries.size(); i++) {
            String azx = keyboard_entries.get(i).toString();
            stdDraw2.textLeft(x, 0.7, azx);

            x = x + 0.019;
        }
        stdDraw2.textLeft(x, 0.7, "_");

    }


    public void setStdDraw(StdDraw2 stdDraw) {
        stdDraw2 = stdDraw;
    }
    public Client getClient(){
        return client;
    }
    public void setClient(Client client) {
        client = client;
    }
}
