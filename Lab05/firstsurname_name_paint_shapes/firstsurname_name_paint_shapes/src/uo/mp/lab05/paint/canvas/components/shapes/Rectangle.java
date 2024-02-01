package uo.mp.lab05.paint.canvas.components.shapes;

import java.io.PrintStream;

import uo.mp.util.check.ArgumentChecks;

/**
 * A rectangle specifies an area in a coordinate space that is enclosed by the
 * the rectangle upper-left point (x, y) in the coordinate space, its width, and
 * its height.
 */
public class Rectangle extends Shape {

	/**
	 * The width of the rectangle, in pixels.
	 */
	private int width;

	/**
	 * The height of the rectangle, in pixels.
	 */
	private int height;

	public Rectangle(int x, int y, int width, int height, Colour colour) {
		super(x, y, colour);
		ArgumentChecks.isTrue(width >= 0);
		ArgumentChecks.isTrue(height >= 0);
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void draw(PrintStream out) {
		out.println(String.format("Rectangle [%s,%s] h:%s w:%s c:%s", this.getX(), this.getY(), this.height, this.width,
				this.getColour()));

	}

}
