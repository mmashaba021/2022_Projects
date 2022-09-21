package za.co.wethinkcode.examples.client.effects;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds implements Runnable{

    String file;
    public Sounds(String file1) {
        file = file1;
    }

    @Override
    public void run() {

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();

    }
}
