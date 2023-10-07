package uo.mp.minesweeper;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;

/**
 * This class manages the main logic of the game. It is the class in charge of
 * executing the loop of iterations of the game. Only from this class it is
 * possible to interact with the user.
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public class Main {

	private Game game;

	public static void main(String[] args) {
		new Main().configure().run();
	}

	private Main configure() {
		Board board = new Board(9, 9, 12 /* % */);
		game = new Game(board);
		return this;
	}

	private void run() {
		game.play();
	}

}
