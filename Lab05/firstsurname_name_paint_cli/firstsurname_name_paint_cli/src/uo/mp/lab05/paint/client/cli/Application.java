package uo.mp.lab05.paint.client.cli;

import uo.mp.lab05.paint.canvas.Canvas;
import uo.mp.lab05.paint.canvas.components.shapes.Circle;
import uo.mp.lab05.paint.canvas.components.shapes.Colour;
import uo.mp.lab05.paint.canvas.components.shapes.Rectangle;

public class Application {

	public static void main(String[] args) {
		new Application().run();
	}

	public void run() {
		Canvas drawing = new Canvas();

		drawing.add(new Rectangle(1, 2, 3, 6, Colour.BLACK));
		drawing.add(new Rectangle(3, 4, 7, 8, Colour.YELLOW));
		drawing.add(new Circle(5, 5, 2, Colour.RED));
		// drawing.add( new Triangle(5, 5, 5, 7, 7, 6, Colour.GREEN) );
		// drawing.add( new Picture(10, 10, 100, 75, "picture1.jpg") );

		drawing.draw(System.out);
	}
}
