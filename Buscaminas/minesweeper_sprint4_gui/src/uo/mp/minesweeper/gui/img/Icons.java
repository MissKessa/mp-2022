package uo.mp.minesweeper.gui.img;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Icons {

	public static final Icon FLAG = load("flag-16x16x8.png");
	public static final Icon MINE = load("mine-bn-16x16.png");
	public static final Icon RED_MINE = load("mine-red-16x16x8.png");

	private static Icon load(String resource) {
		return new ImageIcon(Icons.class.getResource( resource ));
	}
}
