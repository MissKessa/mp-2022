package uo.mp.minesweeper.gui;

import uo.mp.minesweeper.game.GameMove;

/**
 * Utility class to create GameMove objects
 */
/* package */ class GameMoves {

	public static GameMove flag(int y, int x) {
		return new GameMove('f', y, x);
	}

	public static GameMove unflag(int y, int x) {
		return new GameMove('u', y, x);
	}

	public static GameMove stepOn(int y, int x) {
		return new GameMove('s', y, x);
	}

	public static GameMove empty() {
		return new GameMove('\0', -1, -1);
	}

}
