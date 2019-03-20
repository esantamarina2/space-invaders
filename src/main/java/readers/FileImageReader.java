package readers;

import lombok.Data;
import model.Image;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


@Data
public class FileImageReader implements ImageReader {

    protected File file;

    private static Logger log = Logger.getLogger(FileImageReader.class);

    public FileImageReader(File file){
        this.file=file;
    }

    /**
     * Create new image reading file line by line
     * Throws FileNotFoundException, IOException
     */
     @Override
    public Image readImage() {
        Image image = new Image();
        try (
            BufferedReader reader = new BufferedReader(
                    new FileReader(file))) {
            reader.lines().forEach(image::addRow);
        } catch (FileNotFoundException e) {
            log.error("readImage: File not found: " + e);
        } catch (IOException e) {
            log.error("readImage: Error ocurring reading new file image: " + e);
        }
        return image;
    }
}
