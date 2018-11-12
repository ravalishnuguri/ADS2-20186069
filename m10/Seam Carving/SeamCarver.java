import java.awt.Color;
import java.lang.Math;
public class SeamCarver {
    // create a seam carver object based on the given picture
    private Picture picture;
    private int width, height;
    private static final double border = 1000;
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
                    energy[i][j] = border;
                } else {
                    energy[i][j] = sqroot(i, j);
                }
            }
        }
    }
    // current picture
    public Picture picture() {
        return this.picture;
    }
    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
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
        Color e1 = picture.get(i, j - 1);
        Color e2 = picture.get(i, j + 1);
        int r = e1.getRed() - e2.getRed();
        int g = e1.getGreen() - e2.getGreen();
        int b = e1.getBlue() - e2.getBlue();
        int yaxis = (r * r) + (g * g) + (b * b);
        return yaxis;
    }
     private double[][] initEnergies() {
        double[][] energies = new double[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                energies[i][j] = energy(j, i);
            }
        }
        return energies;
    }

    // pass through an array and mark the shorthest distance from top to entry
    private void topologicalSort(double[][] energies) {
        int h = energies.length, w = energies[0].length;
        for (int row = 1; row < h; row++) {
            for (int col = 0; col < w; col++) {
                double temp = energies[row - 1][col];
                double min = 0;
                if (col == 0) {
                    min = temp;
                } else {
                    min = Math.min(temp, energies[row - 1][col - 1]);
                }

                if (col != (w - 1)) {
                    min = Math.min(min, energies[row - 1][col + 1]);
                } else {
                    min = min;
                }
                energies[row][col] += min;
            }
        }

    }
    private double[][] transposeGrid(double[][] energies) {
        int h = energies.length, w = energies[0].length;
        double[][] trEnergies = new double[w][h];
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                trEnergies[col][row] = energies[row][col];
            }
        }
        return trEnergies;
    }

    private int[] minVerticalPath(double[][] energies) {
        int h = energies.length, w = energies[0].length;
        int[] path = new int[h];

        topologicalSort(energies);

        // find the lowest element in last row
        path[h - 1] = 0;
        for (int i = 0; i < w; i++) {
            if (energies[h - 1][i] < energies[h - 1][path[h - 1]])
                path[h - 1] = i;
        }
        // trace path back to first row
        // assuming we need the cheapest upper neighboring entry
        for (int row = h - 2; row >= 0; row--) {
            int col = path[row + 1];
            // three neighboring, priority to center
            path[row] = col;
            if (col > 0 && energies[row][col - 1] < energies[row][path[row]])
                path[row] = col - 1;
            if (col < (w - 2) && energies[row][col + 1] < energies[row][path[row]])
                path[row] = col + 1;
        }
        return path;
    }
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double[][] transposeEnergies = transposeGrid(initEnergies());
        return minVerticalPath(transposeEnergies);
    }
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] normalEnergies = initEnergies();
        return minVerticalPath(normalEnergies);
    }
    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] a) {
        if (height() <= 1 || !isValid(a, width(), height() - 1))
            throw new java.lang.IllegalArgumentException("IllegalArgumentException");
        Picture pic = new Picture(width(), height() - 1);
        for (int w = 0; w < width(); w++) {
            for (int h = 0; h < a[w]; h++) {
                pic.set(w, h, this.picture.get(w, h));
            }

            for (int h = a[w] + 1; h < height(); h++) {
                pic.set(w, h - 1, this.picture.get(w, h));
            }

        }
        this.picture = pic;
    }

    // remove vertical seam from picture
    public void removeVerticalSeam(int[] a) {
        if (width() <= 1 || !isValid(a, height(), width()))
            throw new java.lang.IllegalArgumentException("IllegalArgumentException");
        Picture pic = new Picture(width() - 1, height());
        for (int h = 0; h < height(); h++) {
            for (int w = 0; w < a[h]; w++) {
                pic.set(w, h, this.picture.get(w, h));
            }


            for (int w = a[h] + 1; w < width(); w++) {
                pic.set(w - 1, h, this.picture.get(w, h));
            }

        }
        this.picture = pic;
    }

    // return false if two consecutive entries differ by more than 1
    private boolean isValid(int[] a, int len, int range) {
        if (a == null) {
            return false;
        }
        if (a.length != len || a[0] < 0 || a[0] > range) {
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (a[i] < Math.max(0, a[i - 1] - 1) || a[i] > Math.min(range, a[i - 1] + 1))
                return false;
        }
        return true;
    }
}