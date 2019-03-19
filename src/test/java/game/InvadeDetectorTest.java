package game;

import model.Image;
import model.ImageRange;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import readers.FileImageReader;
import readers.ImageReader;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class InvadeDetectorTest {

    @Test
    public void testDetectInvadersOnMap() throws Exception {
        assertThat(getDetectionResult("test-map").getDetectedInvaders().isEmpty(), is(equalTo(false)));
        //Assert.assertTrue(getDetectionResult("map-with-invaders-and-noise").imageHasInvaders());
        //Assert.assertFalse(getDetectionResult("map-without-invaders").imageHasInvaders());

        DetectionDetected positiveDetection = getDetectionResult("map-with-invaders");
        ImageRange firstDetection = new ImageRange(11, 8, 1, 0);
        ImageRange secondDetection = new ImageRange(8, 8, 12, 8);
        Assert.assertTrue(positiveDetection.imageHasInvaders());

        List<ImageRange> selectionRanges = positiveDetection.getDetectedInvaders().values().stream()
                .collect(Collectors.toList());

        Assert.assertEquals(2, selectionRanges.size());
        Assert.assertTrue(selectionRanges.contains(firstDetection));
        Assert.assertTrue(selectionRanges.contains(secondDetection));
    }

    private DetectionDetected getDetectionResult(String resourceName) throws Exception {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        ImageReader imageReader = new FileImageReader(new File(classloader.getResource(resourceName).toURI()));
        Image radarInfo = imageReader.readImage();
        InvaderDetector invaderDetector = new InvaderDetector();
        return invaderDetector.detectInvaders(radarInfo);
    }
/*
    @Test
    public void imageMatchesPattern() throws Exception {
        Image image = new Image();
        image.addRow("ooo");
        image.addRow("-o-");
        image.addRow("ooo");

        Assert.assertTrue(InvaderDetector.imageMatchesPattern(image, getPattern()));
    }

    @Test
    public void imageWithNoiseMatchesPattern() throws Exception {
        Image image = new Image();
        image.addRow("ooo");
        image.addRow("oo-");
        image.addRow("ooo");

        Assert.assertTrue(InvaderDetector.imageMatchesPattern(image, getPattern()));
    }

    @Test
    public void imageWithNegativeNoiseMatchesPattern() throws Exception {
        Image image = new Image();
        image.addRow("o-o");
        image.addRow("-o-");
        image.addRow("ooo");

        Assert.assertTrue(InvaderDetector.imageMatchesPattern(image, getPattern()));
    }

    @Test
    public void imageNotMatchesPattern() throws Exception {
        Image image = new Image();
        image.addRow("o--");
        image.addRow("-o-");
        image.addRow("-oo");

        Assert.assertFalse(InvaderDetector.imageMatchesPattern(image, getPattern()));
    }

    private Image getPattern() {
        Image pattern = new Image();
        pattern.addRow("ooo");
        pattern.addRow("-o-");
        pattern.addRow("ooo");

        return pattern;
    } */
}
