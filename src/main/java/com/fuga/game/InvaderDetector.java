package com.fuga.game;

import com.fuga.model.Image;
import com.fuga.model.ImageRange;
import com.fuga.readers.FileImageReader;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class InvaderDetector {

    private final Set<Image> invaders;

    private static Logger log = Logger.getLogger(InvaderDetector.class);

    private static final String RESOURCE_FOLDER = "invaders";

    public InvaderDetector() {
        invaders = readInvadersFromFiles();
    }

    /**
     * Get all of the images existing in resource folder
     * Throws Exception
     */
    private Set<Image> readInvadersFromFiles() {
        Set<Image> result = new HashSet<>();
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            File[] invadersFiles = new File(classloader.getResource(RESOURCE_FOLDER).toURI()).listFiles();
            if (invadersFiles == null) {
                log.error("readInvadersFromFiles: No files found");
            }

            for (File invader : invadersFiles) {
                FileImageReader fileImageReader = new FileImageReader(invader);
                result.add(fileImageReader.readImage());
                log.debug("readInvadersFromFiles: New Image created for: " + invader.getName());
            }

        } catch (Exception e) {
            log.error("readInvadersFromFiles: Error Reading files directory: " + e);
        }

        return result;
    }

    public DetectionDetected detectInvaders(Image radarInfo) {
        DetectionDetected detectionResult = new DetectionDetected();
        for (int x = 0; x < radarInfo.getWidth(); x++) {
            for (int y = 0; y < radarInfo.getHeight(); y++) {
                for (Image invader : invaders) {
                    ImageRange imageRange = new ImageRange(invader.getWidth(), invader.getHeight(), x, y);
                    Image subImage = radarInfo.getSubImage(imageRange);
                    if (imagesMatches(subImage, invader)) {
                        detectionResult.addDetection(invader, imageRange);
                        log.debug("detectInvaders: New Invader detected! " + imageRange.toString());
                        break;
                    }
                }
            }
        }

        return detectionResult;
    }

    public static boolean imagesMatches(Image image, Image pattern) {
        if (image.getHeight() != pattern.getHeight() || image.getWidth() != pattern.getWidth()) {
            return false;
        }

        for (int rowIndex = 0; rowIndex < pattern.getHeight(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < pattern.getWidth(); columnIndex++) {
                char patternSymbol = pattern.getRows().get(rowIndex)[columnIndex];
                char imageSymbol = image.getRows().get(rowIndex)[columnIndex];
                if (imageSymbol != patternSymbol) {
                    return false;
                }
            }
        }
        return true;
    }
}
