package uo.mp.minesweeper;

import uo.mp.minesweeper.console.ConsoleGameInteractor;
import uo.mp.minesweeper.console.ConsoleSessionInteractor;
import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.session.GameSession;
import uo.mp.minesweeper.util.exceptions.UserInteractionException;
import uo.mp.minesweeper.util.log.FileLogger;

public class Main {
	private static final String RANKING_FILE = "minesweeper.rnk";
	private static final String LOG_FILE = "minesweeper.log";

	private GameSession session;

	public static void main(String[] args) throws UserInteractionException {
		new Main().configure().run();
	}

	private Main configure() throws UserInteractionException {
		session = new GameSession();
		session.setSessionInteractor(new ConsoleSessionInteractor());
		session.setGameInteractor(new ConsoleGameInteractor());
		session.setLogger(new FileLogger(LOG_FILE));
		session.setGameRanking(new GameRanking(RANKING_FILE));
		return this;
	}

	private void run() {
		session.run();
	}

}
