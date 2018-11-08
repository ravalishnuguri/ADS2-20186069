import java.awt.Color;
import java.lang.Math;
public class SeamCarver {
    // create a seam carver object based on the given picture
    private Picture picture;
    private int width, height;
    private double energy[][];
    public SeamCarver(Picture pic) {
        if (pic == null) {
            throw new IllegalArgumentException("picture is null");
        }
        this.picture = pic;
        this.width = picture.width();
        this.height = picture.height();
        this.energy = new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height ; j++) {
                if(i == 0 || j == 0 || j == (height - 1) || i == (width - 1)) {
                    
                } else {
                    energy[i][j] = sqroot(i, j);
                }
            }
        }
    }
    // current picture
    public Picture picture() {
        return picture;
    }
    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        return energy[x][y];
    }
    public double sqroot(int i, int j) {
        int xaxis = xaxis(i, j);
        int yaxis = yaxis(i, j);
        double energy1 = Math.sqrt(xaxis + yaxis);
        return energy1;
    }
    public int xaxis(int i, int j) {
        Color e1 = picture.get(i - 1, j);
        Color e2 = picture.get(i + 1, j);
        int r = e1.getRed() - e2.getRed();
        int g = e1.getGreen() - e2.getGreen();
        int b = e1.getBlue() - e2.getBlue();
        int xaxis = (r * r) + (g * g) + (b * b);
        return xaxis;
    }
    public int yaxis(int i, int j) {
        Color e1 = picture.get(i - 1, j);
        Color e2 = picture.get(i + 1, j);
        int r = e1.getRed() - e2.getRed();
        int g = e1.getGreen() - e2.getGreen();
        int b = e1.getBlue() - e2.getBlue();
        int yaxis = (r * r) + (g * g) + (b * b);
        return yaxis;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return new int[0];
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return new int[0];
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {

    }
}