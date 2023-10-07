package uo.mp.minesweeper;

import uo.mp.minesweeper.console.ConsoleGameInteractor;
import uo.mp.minesweeper.console.ConsoleSessionInteractor;
import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.session.GameSession;
import uo.mp.minesweeper.util.log.BasicSimpleLogger;

public class Main {

	private GameSession session;

	public static void main(String[] args) {
		new Main()
			.configure()
			.run();
	}

	private Main configure() {
		session = new GameSession();
		session.setSessionInteractor( new ConsoleSessionInteractor() );
		session.setGameInteractor( new ConsoleGameInteractor() );
		session.setLogger( new BasicSimpleLogger() );
		session.setGameRanking( new GameRanking() );
		return this;
	}

	private void run() {
		session.run();
	}

}
