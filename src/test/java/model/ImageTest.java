package model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ImageTest {

    private static int WIDHT = 3;
    private static int HEIGHT = 3;
    private static int LEFT_TOP_X = 1;
    private static int LEFT_TOP_Y = 1;

    /*
    Scenario: Get sub image from the original
            Compare with an expected result
   */
    @Test
    public void testGetSubImage() {

        Image radar = createMockImage();
        Image subImage = radar.getSubImage(createSubImageRange());
        Image expected = createExpectedImage();

        assertThat(expected, is(equalTo(subImage)));
    }

    private Image createMockImage(){
        Image image = new Image();
        image.addRow("-----");
        image.addRow("-o-o-");
        image.addRow("--o--");
        image.addRow("-o-o-");
        image.addRow("-----");
        return image;
    }

    private Image createExpectedImage(){
        Image expected = new Image();
        expected.addRow("o-o");
        expected.addRow("-o-");
        expected.addRow("o-o");
        return expected;
    }

    private ImageRange createSubImageRange(){
       return new ImageRange(WIDHT, HEIGHT, LEFT_TOP_X, LEFT_TOP_Y);
    }
}
