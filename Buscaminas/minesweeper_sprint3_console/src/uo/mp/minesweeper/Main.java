package uo.mp.minesweeper;

import uo.mp.minesweeper.console.ConsoleGameInteractor;
import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;

public class Main {

	private Game game;

	public static void main(String[] args) {
		new Main().configure().run();
	}

	private Main configure() {
		Board board = new Board(9, 9, 12 /* % */);
		game = new Game(board);
		game.setInteractor(new ConsoleGameInteractor());
		return this;
	}

	private void run() {
		game.play();
	}

}
