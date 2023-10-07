package uo.mp.minesweeper.gui;

import static java.lang.Math.max;
import static uo.mp.minesweeper.gui.swing.builders.BorderedPanelBuilder.borderedPanel;
import static uo.mp.minesweeper.gui.swing.builders.ButtonBuilder.button;
import static uo.mp.minesweeper.gui.swing.builders.FrameBuilder.frame;
import static uo.mp.minesweeper.gui.swing.builders.GridPanelBuilder.gridPanel;
import static uo.mp.minesweeper.gui.swing.builders.LabelBuilder.label;

import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Represents the structure of the mine field window. Its unique public method
 * is the constructor whose mission is to assemble the visual components of the 
 * screen.
 * 
 * This class is abstract and must be complimented, through inheritance, with
 * the methods needed from the view model.
 * 
 * @author alb
 */
/*package*/ abstract class BoardScreen {
	private static final int CELL_HEIGHT = 25;
	private static final int CELL_WIDTH = 25;

	protected int cols;
	protected int rows;
	
	protected JFrame window;
	protected JButton[][] buttons;
	protected JLabel flagsLabel;
	protected JLabel timeLabel;
	protected JLabel msgLabel;

	public BoardScreen(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
		this.buttons = createSquareButtons();
		this.flagsLabel = createFlagsLabel();
		this.msgLabel = createMessageLabel();
		this.timeLabel = createTimeLabel();
		this.window = createWindow();
	}

	private JFrame createWindow() {
		int width = max(350, cols * CELL_WIDTH + 20);
		int height = rows * CELL_HEIGHT + 100;
		
		return frame()
				.title("Minesweeper")
				.position(100, 100)
				.size( width, height )
				.content( 
					borderedPanel()
						.atNorth(
							gridPanel(1, 3)
								.add(flagsLabel)
								.add(msgLabel)
								.add(timeLabel)
						)
						.atCenter(
							gridPanel(rows, cols)
								.add( buttons )
						)
				)
				.build();
	}

	private JLabel createTimeLabel() {
		return label().text("0000").alignRight().fontSize( 30.0f ).build();
	}

	private JLabel createFlagsLabel() {
		return label().text("000").alignLeft().fontSize( 30.0f ).build();
	}

	private JLabel createMessageLabel() {
		return label().text("").alignCenter().fontSize( 20.0f ).build();
	}

	private JButton[][] createSquareButtons() {
		JButton[][] res = new JButton[rows][cols];
		MouseListener onClick = createMouseListener();
		
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				res[y][x] = createSquareButton(y, x, onClick); 
			}
		}
		return res;
	}

	protected abstract MouseListener createMouseListener();

	private JButton createSquareButton(int y, int x, MouseListener onClick) {
		return button()
				.margin(0, 0, 0, 0)
				.command( y + "-" + x )
				.listener( onClick )
				.build();
	}

}
