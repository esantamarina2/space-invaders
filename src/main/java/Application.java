import game.InvaderDetector;
import model.Image;
import readers.FileImageReader;
import readers.ImageReader;

import javax.annotation.Resources;
import java.io.File;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws Exception {
        System.out.println("******WELCOME!******");
        System.out.println("Input radar image:");

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File file = new File(classloader.getResource("radar-image.txt").getFile());
        FileImageReader imageReader = new FileImageReader(file);
        Image image = imageReader.readImage();

        InvaderDetector invaderDetector = new InvaderDetector();
        List<Image> invadersDetected = invaderDetector.detectInvaders(image);
        for (Image i: invadersDetected){
            for (char[] row: i.getRows()){
                System.out.println(row);
            }
        }

        System.out.println("Image loaded");
        System.out.println("Scanning image...");

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
}
