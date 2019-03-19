import game.DetectionDetected;
import game.InvaderDetector;
import model.Image;
import model.ImageRange;
import readers.FileImageReader;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File file = new File(classloader.getResource("radar-image").getFile());

        DetectionDetected invadersDetected = detectInvadersInFile(file);

        for (Map.Entry<Image, ImageRange> entry : invadersDetected.getDetectedInvaders().entrySet()) {
            System.out.println("************************");
            System.out.print("==>In ranges:");
            System.out.println(entry.getValue());
            System.out.println("==>Invader:");
            System.out.println(entry.getKey());
            System.out.println("************************");

        }

       /* InvaderDetector invaderDetector = new InvaderDetector();
        DetectionResult detectionResult = invaderDetector.detectInvadersOnMap(new RadarInfo(image));
        if (detectionResult.imageHasInvaders()) {
            System.out.println("======WARNING!!!=======");
            System.out.println("Invaders detected:");
            for (Map.Entry<Invader, Collection<SelectionRange>> entry : detectionResult.getDetectionsMap().entrySet()) {
                System.out.println("************************");
                System.out.print("==>In ranges:");
                entry.getValue().forEach(System.out::println);
                System.out.println("==>Invader:");
                System.out.println(entry.getKey());
                System.out.println("************************");

            }
        } else {
            System.out.println("Image is clear. Fuuuuuh...");
        } */
    }

    private static DetectionDetected detectInvadersInFile(File file){
        Image image = new FileImageReader(file).readImage();
        return new InvaderDetector().detectInvaders(image);
    }
}
