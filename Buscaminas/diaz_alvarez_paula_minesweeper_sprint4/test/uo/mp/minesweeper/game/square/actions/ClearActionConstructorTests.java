package uo.mp.minesweeper.game.square.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class ClearActionConstructorTests {
	private List<Square> neighbours;
	private Square neighbour1 = new Square();
	private Square neighbour2 = new Square();
	private Square neighbour3 = new Square();

	@BeforeEach
	public void setUp() {
		neighbours = new ArrayList<>();
		neighbours.add(neighbour1);
		neighbours.add(neighbour2);
		neighbours.add(neighbour3);
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a ClearAction with a valid list of neighbours
	 * <p>
	 * THEN: a correct clearAction is created with that neighbours
	 */
	@Test
	void validParametersConstructor() {
		try {
			new ClearAction(neighbours);
		} catch (Exception e) {
			fail("No exception should be thrown");
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a ClearAction with null neighbours
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidParametersNullList() {
		try {
			new ClearAction(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), ClearAction.ERROR_MESSAGE_NULL_LIST_OF_NEIGHBOURS);
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a ClearAction with a list of neighbours with some null
	 * elements
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidParametersSomeNullNeighbours() {
		neighbours.add(null);
		try {
			new ClearAction(neighbours);
		} catch (Exception e) {
			assertEquals(e.getMessage(), ClearAction.ERROR_MESSAGE_NULL_NEIGHBOURS);
		}
	}

}
