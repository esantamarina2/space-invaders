package readers;

import com.fuga.model.Image;
import com.fuga.readers.FileImageReader;
import com.fuga.readers.ImageReader;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class FileImageReaderTest {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private static String FILE_NAME = "invaders/invader-1";

    /*
    Scenario: Read file from resources folder
           Verify expected bounds
    */
    @Test
    public void testReadInvaderFile() throws Exception {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        ImageReader imageReader = new FileImageReader(new File(classloader.getResource(FILE_NAME).toURI()));
        Image image = imageReader.readImage();
        assertThat(image.getWidth(), is(equalTo(WIDTH)));
        assertThat(image.getHeight(), is(equalTo(HEIGHT)));
    }

}
