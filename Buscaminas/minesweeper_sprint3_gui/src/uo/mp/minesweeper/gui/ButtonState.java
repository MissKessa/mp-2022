package uo.mp.minesweeper.gui;

/**
 * Represents the state of a button on the game board view (different from the
 * state on the Game) This class is part of the Presenter
 */
/* package */ class ButtonState {

	private boolean enabled = true;
	private String text = "";
	private boolean hasFlagIcon = false;
	private boolean hasMineIcon = false;

	public ButtonState(boolean enabled, String text, boolean hasFlagIcon,
			boolean hasMineIcon) {
		this.enabled = enabled;
		this.text = text;
		this.hasFlagIcon = hasFlagIcon;
		this.hasMineIcon = hasMineIcon;
	}

	public String getText() {
		return this.text;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public boolean hasFlagIcon() {
		return hasFlagIcon;
	}

	public boolean hasMineIcon() {
		return hasMineIcon;
	}

}
