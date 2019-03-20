package game;

import model.Image;
import model.ImageRange;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import readers.FileImageReader;
import readers.ImageReader;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class InvadeDetectorTest {

    private static final String WITH_INVADERS = "map-with-invaders";
    private static final String WITHOUT_INVADERS = "map-without-invaders";

    /*
    Scenario: Detect invaders on image with
          an existing one
    */
    @Test
    public void testDetectInvaders() throws Exception {
        assertThat(getDetectionResult(WITH_INVADERS).getDetectedInvaders().isEmpty(), is(equalTo(false)));
    }

    /*
    Scenario: Detect invaders on image with
         an existing one
    */
    @Test
    public void testDetectNoInvaders() throws Exception {
        assertThat(getDetectionResult(WITHOUT_INVADERS).getDetectedInvaders().isEmpty(), is(equalTo(true)));
    }

    /*
    Scenario: Verify image range for
         detected invaders
    */
    @Test
    public void testImageRangeDetected() throws Exception {
        DetectionDetected resultDetection = getDetectionResult(WITH_INVADERS);
        ImageRange firstInvader = new ImageRange(11, 8, 1, 0);
        ImageRange secondInvader = new ImageRange(8, 8, 12, 8);

        List<ImageRange> imageRanges = new ArrayList<>(resultDetection.getDetectedInvaders().values());

        assertThat(imageRanges.size(), is(equalTo(2)));
        assertThat(imageRanges.contains(firstInvader), is(equalTo(true)));
        assertThat(imageRanges.contains(secondInvader), is(equalTo(true)));
    }

    /*
   Scenario: Compare two similar images
   */
    @Test
    public void imagesMatches() {
       Image firstImage = getSubImage();
       Image secondImage = getSubImage();

       assertThat(InvaderDetector.imagesMatches(firstImage, secondImage), is(equalTo(true)));
    }

    /*
   Scenario: Compare two different images
   */
    @Test
    public void imageNotMatches() {
        Image image = new Image();
        image.addRow("o--");
        image.addRow("-o-");
        image.addRow("-oo");

        assertThat(InvaderDetector.imagesMatches(image, getSubImage()), is(equalTo(false)));
    }


    private DetectionDetected getDetectionResult(String resourceName) throws Exception {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        ImageReader imageReader = new FileImageReader(new File(classloader.getResource(resourceName).toURI()));
        return new InvaderDetector().detectInvaders(imageReader.readImage());
    }

    private Image getSubImage() {
        Image subImage = new Image();
        subImage.addRow("ooo");
        subImage.addRow("-o-");
        subImage.addRow("ooo");

        return subImage;
    }
}
