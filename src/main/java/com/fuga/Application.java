package com.fuga;

import com.fuga.game.DetectionDetected;
import com.fuga.game.InvaderDetector;
import com.fuga.model.Image;
import com.fuga.model.ImageRange;
import org.apache.log4j.Logger;
import com.fuga.readers.FileImageReader;

import java.io.File;
import java.util.Map;

public class Application {

    private static Logger log = Logger.getLogger(FileImageReader.class);

    private static final String RADAR = "radar-image";

    public static void main(String[] args) {
        findInvaders(RADAR);
    }

    private static void findInvaders(String fileName){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File file = new File(classloader.getResource(fileName).getFile());

        DetectionDetected invadersDetected = detectInvadersInFile(file);

        if (!invadersDetected.getDetectedInvaders().isEmpty()) {
            log.debug("Application: Number of invaders detected: " + invadersDetected.getDetectedInvaders().size());
            for (Map.Entry<Image, ImageRange> entry : invadersDetected.getDetectedInvaders().entrySet()) {
                log.debug("Application: Invader found in ranges: " + entry.getValue());
                log.debug("Application: Invader: " + entry.getKey());
            }
        }
        else
        {
            log.debug("Application: No invaders found!");
        }
    }

    private static DetectionDetected detectInvadersInFile(File file) {
        Image image = new FileImageReader(file).readImage();
        return new InvaderDetector().detectInvaders(image);
    }
}
