import java.awt.Color;
import java.lang.Math;
public class SeamCarver {
    // create a seam carver object based on the given picture
    private Picture picture;
    public SeamCarver(Picture pic) {
        if (pic == null) {
            throw new IllegalArgumentException("picture is null");
        }
        this.picture = pic;

    }
    // current picture
    public Picture picture() {
        return null;
    }
    // width of current picture
    public int width() {
        return this.picture.width();
    }

    // height of current picture
    public int height() {
        return this.picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if(x == 0 || y == 0 || y == (picture.height() - 1) || x == (picture.width() - 1)) {
            return 1000.0;
        }
        double coordinatex = 0.0;
        double coordinatey = 0.0;
        Color object = picture.get(x,y);
        Color leftObj = picture.get(x, y - 1);
        Color rightObj = picture.get(x, y + 1);
        double xR = Math.abs((leftObj.getRed() - rightObj.getRed()));
        double xG = Math.abs((leftObj.getGreen() - rightObj.getGreen()));
        double xB = Math.abs((leftObj.getBlue() - rightObj.getBlue()));
        coordinatex = Math.pow(xR, 2) + Math.pow(xB, 2) + Math.pow(xG, 2);
        Color topObj = picture.get(x - 1, y);
        Color bottomObj = picture.get(x + 1, y);
        double yR = Math.abs((topObj.getRed() - bottomObj.getRed()));
        double yG = Math.abs((topObj.getGreen() - bottomObj.getGreen()));
        double yB = Math.abs((topObj.getBlue() - bottomObj.getBlue()));
        coordinatey = Math.pow(yR, 2) + Math.pow(yB, 2) + Math.pow(yG, 2);
        double result = Math.sqrt((coordinatey + coordinatey));
        return result;
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