package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
public class Image {

    private List<char[]> rows = new ArrayList<>();

    public void addRow(String line) {
        rows.add(line.toCharArray());
    }

    public void addRow(char[] line) { rows.add(line); }

    public int getHeight(){
        return rows.size();
    }

    public int getWidth() {
        if (rows.isEmpty()) {
            return 0;
        }
        return rows.get(0).length;
    }

    @Override
    public String toString() {
        return "Image{" +
                "rows=" + rows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(rows, image.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }

    public Image getSubImage(ImageRange imageRange) {
        Image newImage = new Image();
        for (int i = imageRange.getLeftTopY(); i <= imageRange.getRightBottomY() && i < this.getHeight(); i++) {
            newImage.addRow(Arrays.copyOfRange(rows.get(i), imageRange.getLeftTopX(), Math.min(imageRange.getRightBottomX() + 1, rows.get(i).length)));
        }
        return newImage;
    }
}
