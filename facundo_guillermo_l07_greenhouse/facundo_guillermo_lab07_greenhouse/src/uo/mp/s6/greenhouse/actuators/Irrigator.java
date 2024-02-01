package uo.mp.s6.greenhouse.actuators;

/**
 * This class models a irrigators that has an id and a state that can be OFF,
 * LOW, MEDIUM or HIGH.
 *
 * @author Paula
 * @version 2023
 */
public class Irrigator {

	private final int id;
	private State state = State.OFF;

	/**
	 * Creates an irrigator instance with the given integer id.
	 * 
	 * @param id is the index of the irrigator.
	 */
	public Irrigator(int id) {
		this.id = id;
	}

	/**
	 * Increases the level of the irrigator
	 * 
	 * @return the message "Irrigator is set to %s,now set to %s"
	 */
	public String increaseLevel() {
		if (!this.state.equals(State.HIGH)) {
			return setState(this.state.getValue() + 1);
		}
		return "";
	}

	/**
	 * 
	 * @return if the state of the irrigator is equal to high
	 */
	public boolean isAtMaximumLevel() {
		return this.state.equals(State.HIGH);
	}

	public String setState(State state) {
		State originalState = this.state;
		this.state = state;
		return String.format("Irrigator %s is set to %s,now set to %s", id, originalState, this.state);
	}

	public String setState(int state) {

		int originalState = this.state.getValue();
		this.state = this.state.getState(state);
		return String.format("Irrigator %s is set to %s,now set to %s", this.id, this.state.getState(originalState),
				this.state);
	}

	/**
	 * @returns true if the irrigator is opened (not OFF)
	 */
	public boolean isOpened() {
		return !this.state.equals(State.OFF);
	}

	public String reduceLevel() {
		if (isOpened()) {
			return setState(this.state.getValue() - 1);
		}
		return "";
	}

	public String close() {
		return setState(State.OFF);
	}

	@Override
	public String toString() {
		return String.format("Irrigator [id=%s, state=%s]", id, this.state.toString());
	}
}
