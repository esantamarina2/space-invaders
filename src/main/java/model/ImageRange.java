package model;

import lombok.Data;

import java.util.Objects;

@Data
public class ImageRange {

    private final int height;
    private final int width;
    private final int leftTopX;
    private final int leftTopY;


    public ImageRange(int width, int height, int leftTopX, int leftTopY) {
        this.height = height;
        this.width = width;
        this.leftTopX = leftTopX;
        this.leftTopY = leftTopY;
    }

    public int getRightBottomY() {
        return leftTopY + height - 1;
    }

    public int getRightBottomX() {
        return leftTopX + width - 1;
    }

    @Override
    public String toString() {
        return "ImageRange{" +
                "height=" + height +
                ", width=" + width +
                ", leftTopX=" + leftTopX +
                ", leftTopY=" + leftTopY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageRange that = (ImageRange) o;
        return height == that.height &&
                width == that.width &&
                leftTopX == that.leftTopX &&
                leftTopY == that.leftTopY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, leftTopX, leftTopY);
    }
}
