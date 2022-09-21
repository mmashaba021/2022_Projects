package za.co.wethinkcode.examples.client.world;
import za.co.wethinkcode.examples.client.player.Player;
import za.co.wethinkcode.examples.client.turtle2.StdDraw3;
import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Notifications implements Runnable {



    static double a0 = 90.0D;
    List obstacles_found;
    Player player;
    String root_folder_images = System.getProperty("user.dir") + "/images/";
    String root_folder = System.getProperty("user.dir") + "/";
    FirstWorld firstWorld;
    StdDraw3 notifications = new StdDraw3();

    public Notifications(FirstWorld firstWorld) {
        this.firstWorld = firstWorld;

    }

    @Override
    public void run() {
        makeWindow();
    }
        public void makeWindow(){


        notifications.setCanvasSize(400,400);
        notifications.picture(0.5,0.5,root_folder_images+"bord3.jpg",1.5,1.5,0);
        notifications.filledRectangle(0.5,0.5,0.2,0.3); //to work this out you have to take 0.002*400/2 and 0.002*200/2 because rectangle wants half the width and half length

        String window = firstWorld.getNotificaton();
        switch (window){

            case("look"):
                lookCommand();
                break;
            case("end"):
                endGame();
                break;

        }
    }

    public void lookCommand() {


        List obstacles_found1 = firstWorld.getObstacles_found();


        if (obstacles_found1.size() > 0) {
            double y = 0.97;
            double x = 0.5;
            double newline = 0.04;
            for (int i = 0; i < obstacles_found1.size(); i++) {

                List first_obstacle = (List) obstacles_found1.get(i);
                String type = (String) first_obstacle.get(0);
                String distance = (String) first_obstacle.get(1);
                Double distance_double = Double.valueOf(distance);
                if (distance_double < 1) {
                    distance_double = distance_double * (-1);
                }
                Double convert_double = new Double(distance_double);
                int distance_int = convert_double.intValue();
                String direction = (String) first_obstacle.get(2);

                notifications.setFont(new Font("TimesRoman", Font.BOLD, 14));
                notifications.setPenColor(Color.red);

                notifications.text(x, y, "Found: " + type + " " + distance_int + " steps " + direction);
                y = y - newline;
            }
          drawWarningText();
        }
                showWarningGif();
    }

    private void drawWarningText() {
        notifications.setFont(new Font("TimesRoman", Font.BOLD, 15));

        notifications.text(0.5, 0.15, "WARNING OBSTACLES FOUND");
        notifications.text(0.5, 0.1, "PROCEED WITH CAUTION");

        notifications.show();
    }

    private void showWarningGif() {

        for(int x = 0; x<15;x++) {
            for (int i = 1; i < 22; i++) {

                String image2 = root_folder + "gifs/warning/AnyConv.com__warning" + i + ".jpg";
                notifications.picture(0.5, 0.4, image2, 0.4, 0.4, 0);

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (Exception k) {

                }
            }
        }
        notifications.getFrame().setVisible(false);

        notifications.getFrame().dispose(); //Destroy the JFrame object
    }


    private void showGameOverGif() {

        for(int x = 0; x<20;x++) {
            for (int i = 1; i < 20; i++) {

                String image2 = root_folder + "gifs/gameover/"+i + ".jpg";
                notifications.picture(0.5, 0.5, image2, 0.6, 0.6, 0);

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (Exception k) {

                }
            }
        }
        notifications.getFrame().setVisible(false);

        notifications.getFrame().dispose(); //Destroy the JFrame object
    }

    public void endGame() {

        showGameOverGif();
    }





}
