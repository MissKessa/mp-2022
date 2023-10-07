package uo.mp.minesweeper.game.square.actions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class ClearActionExecuteTests {

	private ClearAction action;

	private List<Square> neighbours;
	private Square neighbour1 = new Square();
	private Square neighbour2 = new Square();
	private Square neighbour3 = new Square();

	@BeforeEach
	public void setUp() {
		neighbour1.setAction(new NullAction());
		neighbour2.setAction(new NullAction());
		neighbour3.setAction(new NullAction());

		neighbours = new ArrayList<>();
		neighbours.add(neighbour1);
		neighbours.add(neighbour2);
		neighbours.add(neighbour3);
		action = new ClearAction(neighbours);
	}

	/**
	 * GIVEN: an action of type ClearAction
	 * <p>
	 * WHEN: calling execute()
	 * <p>
	 * THEN: returns true and stepOn() is called on all the neighbours
	 */
	@Test
	void executeAction() {
		assertTrue(neighbour1.isEmpty());
		assertTrue(neighbour1.isClosed());

		assertTrue(neighbour2.isEmpty());
		assertTrue(neighbour2.isClosed());

		assertTrue(neighbour3.isEmpty());
		assertTrue(neighbour3.isClosed());

		assertTrue(action.execute());

		assertTrue(neighbour1.isOpened());

		assertTrue(neighbour2.isOpened());

		assertTrue(neighbour3.isOpened());
	}

}
