package readers;

import lombok.Data;
import model.Image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


@Data
public class FileImageReader implements ImageReader {

    protected File file;

    public FileImageReader(File file){
        this.file=file;
    }

    @Override
    public Image readImage() {
        Image image = new Image();
        try (
            BufferedReader reader = new BufferedReader(
                    new FileReader(file))) {
            reader.lines().forEach(image::addRow);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
