package uo.mp.minesweeper;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.gui.GuiGameInteractor;

public class MainHard {

	private Game game;

	public static void main(String[] args) {
		new MainHard()
			.configure()
			.run();
	}

	private MainHard configure() {
		Board board = new Board(30, 16, 20 /* % */);
		game = new Game( board );
		game.setInteractor( new GuiGameInteractor() );
		return this;
	}

	private void run() {
		game.play();
	}

}
