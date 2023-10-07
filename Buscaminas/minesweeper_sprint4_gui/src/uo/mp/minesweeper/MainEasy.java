package uo.mp.minesweeper;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.gui.GuiGameInteractor;

public class MainEasy {

	private Game game;

	public static void main(String[] args) {
		new MainEasy()
			.configure()
			.run();
	}

	private MainEasy configure() {
		Board board = new Board(9, 9, 12 /* % */);
		game = new Game( board );
		game.setInteractor( new GuiGameInteractor() );
		return this;
	}

	private void run() {
		game.play();
	}

}
