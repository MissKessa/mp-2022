package uo.mp.s6.greenhouse.actuators;

public enum State {

	OFF(0), LOW(1), MEDIUM(2), HIGH(3);

	private int value;

	State(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public State getState(int value) {
		if (OFF.getValue() == value)
			return OFF;
		else if (LOW.getValue() == value)
			return LOW;
		else if (MEDIUM.getValue() == value)
			return MEDIUM;
		else
			return HIGH;
	}

}
