import java.awt.Color;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture picture;
	public SeamCarver(Picture picture1) {
		if (picture1 == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.picture = picture1;
	}
	// current picture
	public Picture picture() {
		return picture;
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
		if (x == 0 || y == 0 || picture.width() - 1 == x || picture.height() - 1 == y) {
			return 1000;
		}
		Color top = picture.get(x - 1, y);
		Color bottom = picture.get(x + 1, y);
		Color left = picture.get(x, y - 1);
		Color right = picture.get(x, y + 1);
		int red = bottom.getRed() - top.getRed();
		int blue = bottom.getBlue() - top.getBlue();
		int green = bottom.getGreen() - top.getGreen();
		int horizontal = red * red + blue * blue + green * green;
		int redVertical = left.getRed() - right.getRed();
		int blueVertical = left.getBlue() - right.getBlue();
		int greenVertical = left.getGreen() - right.getGreen();
		int vertical = redVertical * redVertical + blueVertical *
		blueVertical + greenVertical * greenVertical;
		double energy = Math.sqrt(horizontal + vertical);
		return energy;
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