package uo.mp.lab05.paint.canvas;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Canvas {
	private List<Drawable> drawables = new ArrayList<>();

	public void add(Drawable rectangle) {
		drawables.add(rectangle);
	}

	public void draw(PrintStream out) {
		for (Drawable drawable : drawables) {
			drawable.draw(out);
		}
	}
}
