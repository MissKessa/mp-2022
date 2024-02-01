package uo.mp.lab05.paint.canvas.components.shapes;

import uo.mp.lab05.paint.canvas.Drawable;
import uo.mp.util.check.ArgumentChecks;

public abstract class Shape implements Drawable {
	/**
	 * The x coordinate of the upper-left corner.
	 */
	private int x;

	/**
	 * The y coordinate of the upper-left corner.
	 */
	private int y;

	private Colour colour;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Shape(int x, int y, Colour colour) {
		ArgumentChecks.isTrue(colour != null);
		ArgumentChecks.isTrue(x >= 0);
		ArgumentChecks.isTrue(y >= 0);
		this.x = x;
		this.y = y;
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}

}
