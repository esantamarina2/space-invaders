package game;

import model.Image;
import model.ImageRange;
import readers.FileImageReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InvaderDetector {

    private final Set<Image> invaders;

    public InvaderDetector() {
        invaders = readInvadersFromFiles();
    }

    private Set<Image> readInvadersFromFiles() {
        Set<Image> result = new HashSet<>();

        try {

            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            File[] invadersFiles = new File(classloader.getResource("invaders").toURI()).listFiles();
            if (invadersFiles == null) {
                throw new IllegalStateException("Invader patterns not found");
            }

            for (File invader : invadersFiles) {
                FileImageReader fileImageReader = new FileImageReader(invader);
                result.add(fileImageReader.readImage());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public DetectionDetected detectInvaders(Image radarInfo) {
        DetectionDetected detectionResult = new DetectionDetected();
        for (int x = 0; x < radarInfo.getWidth(); x++) {
            for (int y = 0; y < radarInfo.getHeight(); y++) {
              //  if (detectionResult.dotDetected(x, y)) {
               //     continue;
               // }

                for (Image invader : invaders) {
                    ImageRange imageRange = new ImageRange(invader.getWidth(), invader.getHeight(), x, y);
                    Image subImage = radarInfo.getSubImage(imageRange);
                    if (imageMatchesPattern(subImage, invader)) {
                        detectionResult.addDetection(invader, imageRange);
                        break;
                    }
                }
            }
        }

        return detectionResult;
    }

    boolean imageMatchesPattern(Image image, Image pattern) {
        if (image.getHeight() != pattern.getHeight() || image.getWidth() != pattern.getWidth()) {
            return false;
        }

        //int checkedPoints = 0;
        //int matchesPoints = 0;
        for (int rowIndex = 0; rowIndex < pattern.getHeight(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < pattern.getWidth(); columnIndex++) {
                char patternSymbol = pattern.getRows().get(rowIndex)[columnIndex];
                char imageSymbol = image.getRows().get(rowIndex)[columnIndex];
                if (imageSymbol != patternSymbol) {
                    return false;
                }
             /*   if (patternSymbol == ImageReader.POINT_SYMBOL) {
                    checkedPoints++;
                    if (imageSymbol == patternSymbol) {
                        matchesPoints++;
                    }
                }
            } */
            }
            //return matchesPoints >= checkedPoints * MATCH_DETECTION_PERCENT / 100;
        }
        return true;
    }
}
