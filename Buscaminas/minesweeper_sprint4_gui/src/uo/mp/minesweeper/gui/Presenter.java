package uo.mp.minesweeper.gui;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import uo.mp.minesweeper.game.GameMove;
import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * Represents the presenter of the MVP (Model-View-Presenter) pattern Model:
 * represented by the interactor View: represented by BoardView object
 * Presenter: an instance of this class
 * 
 * It is in charge of handling all the presentation logic abstracted away from
 * the technological dependencies of the view. An object of this class keeps the
 * logical state of the visible components on the window and the logic rules to
 * change it. This way the view is a dumb object without logic with only the
 * arrangement of the visual elements. This design promotes testing of the
 * presentation logic.
 * 
 * It also keeps the synchronization between the Game (trough the interactor)
 * and the view as they are executed on different threads.
 * 
 * @author alb
 */
/* package */ class Presenter {

	/**
	 * Any view must implement this interface, this avoids dependency of the
	 * presenter on the view
	 */
	interface View {
		void refreshButtons(ButtonState[][] states);

		void refreshMessage(String msg);

		void refreshFlagsLeft(String text);

		void refreshElapsedTime(String text);

		void open();
	}

	/*
	 * The BoardView and the Game are executed on different threads. This
	 * BlockingQueue implements the producer-consumer pattern. - Producer: the
	 * (swing) board view, produces GameMoves - Consumer: the GuiInteractor consumes
	 * them when asked by the Game
	 */
	private BlockingQueue<GameMove> moves = new LinkedBlockingQueue<>();

	private View view;

	private char[][] status;

	public void setView(View view) {
		ArgumentChecks.isTrue(view != null);
		this.view = view;
	}

	public void show() {
		this.view.open();
	}

	public void update(long seconds, int flagsLeft, char[][] status) {
		this.status = status;

		view.refreshElapsedTime(asTimeString(seconds));
		view.refreshFlagsLeft(asFlagsString(flagsLeft));
		view.refreshButtons(createButtonStates(status));
	}

	public void setMessage(String msg) {
		view.refreshMessage(msg);
	}

	private ButtonState[][] createButtonStates(char[][] status) {
		int rows = status.length;
		int cols = status[0].length;
		ButtonState[][] res = new ButtonState[rows][cols];

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				char square = getCurrentSquare(y, x);
				res[y][x] = createButtonState(square);
			}
		}

		return res;
	}

	private ButtonState createButtonState(char square) {
		boolean enabled = isClosed(square) || isFlagged(square);
		boolean hasFlag = isFlagged(square);
		boolean hasMine = isMine(square);
		String text = isClosed(square) || isFlagged(square) || isMine(square) ? "" : Character.toString(square);

		return new ButtonState(enabled, text, hasFlag, hasMine);
	}

	public void rightMouseClickOnSquare(int y, int x) {
		char square = getCurrentSquare(y, x);

		if (isClosed(square)) {
			enqueueMove(GameMoves.flag(y, x));
		}
		if (isFlagged(square)) {
			enqueueMove(GameMoves.unflag(y, x));
		}
	}

	public void leftMouseClickOnSquare(int y, int x) {
		char square = getCurrentSquare(y, x);

		if (isClosed(square)) {
			enqueueMove(GameMoves.stepOn(y, x));
		}
	}

	/**
	 * Blocking call if the queue is empty, so the Game object will block until the
	 * user clicks on a square
	 * 
	 * @return the next game move to be processed
	 */
	public GameMove getNextMove() {
		try {
			return moves.take();
		} catch (InterruptedException veryRare) {
			return GameMoves.empty();
		}
	}

	/**
	 * Thread safe, is a blocking queue
	 * 
	 * @return
	 */
	private void enqueueMove(GameMove mov) {
		try {
			moves.put(mov);
		} catch (InterruptedException ignored) {
			// intentionally ignored
		}
	}

	private boolean isClosed(char square) {
		return square == '#';
	}

	private boolean isMine(char square) {
		return square == '@';
	}

	private boolean isFlagged(char square) {
		return square == ((char) 182);
	}

	private char getCurrentSquare(int y, int x) {
		return status[y][x];
	}

	private String asTimeString(long seconds) {
		return String.format("%04d", seconds);
	}

	private String asFlagsString(int flagsLeft) {
		return String.format("%03d", flagsLeft);
	}

}
