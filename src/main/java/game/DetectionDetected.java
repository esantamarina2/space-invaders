package game;

import lombok.Data;
import model.Image;
import model.ImageRange;

import java.util.HashMap;
import java.util.Map;

@Data
public class DetectionDetected {

    private Map<Image, ImageRange> detectedInvaders = new HashMap<>();

    public void addDetection(Image invaderDetected, ImageRange imageRange) {
        detectedInvaders.put(invaderDetected, imageRange);
    }

    public boolean imageHasInvaders() {
        return !detectedInvaders.isEmpty();
    }
}
