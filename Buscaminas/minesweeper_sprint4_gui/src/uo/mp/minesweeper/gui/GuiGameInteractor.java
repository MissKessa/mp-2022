package uo.mp.minesweeper.gui;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.game.GameMove;

public class GuiGameInteractor implements GameInteractor {

	private Presenter presenter;
	
	@Override
	public GameMove askMove(int heigth, int width) {
		return presenter.getNextMove();
	}

	@Override
	public void showGame(long elapsedTime, Board board) {
		if (presenter == null) {
			createPresenterAndView( board );
		}
		presenter.update(elapsedTime, board.getNumberOfFlagsLeft(),	board.getState());
	}

	@Override
	public void showGameFinished() {
		presenter.setMessage("Game Over");
	}

	@Override
	public void showCongratulations(long elapsedTime) {
		presenter.setMessage("You win!");
	}

	@Override
	public void showBooommm() {
		presenter.setMessage("BOOOMMMM");
	}

	@Override
	public void showReadyToStart() {
		presenter = null;
		// nothing to show for this user interface
	}

	private void createPresenterAndView(Board board) {
		BoardView view = new BoardView(board.getNumberOfRows(), board.getNumberOfColumns());
		presenter = new Presenter();
		presenter.setView( view );
		view.setPresenter( presenter );
		presenter.show();
	}


}
