package uo.mp.minesweeper;

import uo.mp.minesweeper.gui.GuiGameInteractor;
import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.session.GameSession;
import uo.mp.minesweeper.util.log.BasicSimpleLogger;

/**
 * 
 * @author paula
 *
 */
public class Main {
	private GameSession session;

	public static void main(String[] args) {
		new Main().configure().run();
	}

	private Main configure() {
		session = new GameSession();
		session.setSessionInteractor(new ConsoleSessionInteractorPaula());
		session.setGameInteractor(new GuiGameInteractor());
		session.setLogger(new BasicSimpleLogger());
		session.setGameRanking(new GameRanking());
		return this;
	}

	private void run() {
		session.run();
	}
}
