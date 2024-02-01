package uo.mp.lab05.paint.canvas.components.shapes;

import java.io.PrintStream;

public class Circle extends Shape {
	private final int radious;

	public Circle(int x, int y, int radious, Colour colour) {
		super(x, y, colour);
		// validations
		this.radious = radious;
	}

	public int getRadious() {
		return radious;
	}

	@Override
	public void draw(PrintStream out) {
		out.println(
				String.format("Circle [%s,%s] r:%s c:%s", this.getX(), this.getY(), this.radious, this.getColour()));
	}

}
