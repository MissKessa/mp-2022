package uo.mp.minesweeper.gui;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import uo.mp.minesweeper.gui.img.Icons;
import uo.mp.minesweeper.gui.swing.OnSwingThread;
import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * Builds the window (the View) and links it with the ModelView
 */
/* package */ class BoardView extends BoardScreen implements Presenter.View {
	private Presenter presenter;

	public BoardView(int rows, int cols) {
		super(rows, cols);
	}

	public void setPresenter(Presenter presenter) {
		ArgumentChecks.isTrue(presenter != null);
		this.presenter = presenter;
	}

	@Override
	@OnSwingThread
	public void open() {
		onSwingThread(() -> window.setVisible(true));
	}

	@Override
	@OnSwingThread
	public void refreshElapsedTime(String text) {
		onSwingThread(() -> timeLabel.setText(text));
	}

	@Override
	@OnSwingThread
	public void refreshFlagsLeft(String text) {
		onSwingThread(() -> flagsLabel.setText(text));
	}

	@Override
	@OnSwingThread
	public void refreshMessage(String msg) {
		onSwingThread(() -> msgLabel.setText(msg));
	}

	@Override
	@OnSwingThread
	public void refreshButtons(ButtonState[][] states) {
		onSwingThread(() -> updateButtons(states));
	}

	@OnSwingThread
	private void updateButtons(ButtonState[][] states) {
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				updateButton(buttons[y][x], states[y][x]);
			}
		}
	}

	@OnSwingThread
	private void updateButton(JButton bt, ButtonState state) {
		bt.setText(state.getText());
		bt.setEnabled(state.isEnabled());

		if (state.hasFlagIcon()) {
			bt.setIcon(Icons.FLAG);
		} else if (state.hasMineIcon()) {
			bt.setIcon(Icons.MINE); // ?? if not setDisabledIcon has no effect
			bt.setDisabledIcon(Icons.MINE);
		} else {
			bt.setIcon(null);
			bt.setDisabledIcon(null);
		}
	}

	@Override
	protected MouseListener createMouseListener() {
		return new SquareMouseListener();
	}

	@OnSwingThread
	private class SquareMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JButton bt = (JButton) e.getSource();
			String actionCommand = bt.getActionCommand();
			String[] coord = actionCommand.split("-");
			int y = Integer.parseInt(coord[0]);
			int x = Integer.parseInt(coord[1]);

			if (isRightMouseButton(e)) {
				presenter.rightMouseClickOnSquare(y, x);
			} else if (isLeftMouseButton(e)) {
				presenter.leftMouseClickOnSquare(y, x);
			}
		}

	}

	private void onSwingThread(Runnable task) {
		SwingUtilities.invokeLater(task);
	}

}
