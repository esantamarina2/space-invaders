package com.fuga.game;

import lombok.Data;
import com.fuga.model.Image;
import com.fuga.model.ImageRange;

import java.util.HashMap;
import java.util.Map;

@Data
public class DetectionDetected {

    private Map<Image, ImageRange> detectedInvaders = new HashMap<>();

    public void addDetection(Image invaderDetected, ImageRange imageRange) {
        detectedInvaders.put(invaderDetected, imageRange);
    }

}
