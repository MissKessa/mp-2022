package uo.mp.minesweeper;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.gui.GuiGameInteractor;

public class MainMedium {

	private Game game;

	public static void main(String[] args) {
		new MainMedium()
			.configure()
			.run();
	}

	private MainMedium configure() {
		Board board = new Board(16, 16, 15 /* % */);
		game = new Game( board );
		game.setInteractor( new GuiGameInteractor() );
		return this;
	}

	private void run() {
		game.play();
	}

}
