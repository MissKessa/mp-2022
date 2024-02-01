package uo.mp.newsstand.service.newsstand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import uo.mp2122.newsstand.domain.Magazine;
import uo.mp2122.newsstand.domain.Newspaper;
import uo.mp2122.newsstand.domain.Order;

public class CreateOrdersTest {

	@Before
	public void setUp() {
	}

	/**
	 * GIVEN: A newspaper with enough copies in stock
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: no new order is generated
	 */
	@Test
	public void enoughCopiesNewspaperNoOrderTest() {
		Newspaper np = new Newspaper("Hola", 20, 15);
		assertEquals(null, np.generateOrders());
	}

	/**
	 * GIVEN: A newspaper with copies in stock in the limit
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: no new order is generated
	 */
	@Test
	public void copiesintheLimitNewspaperNoOrderTest() {
		Newspaper np = new Newspaper("Hola", Newspaper.MIN_STOCK, 15);
		assertEquals(null, np.generateOrders());
	}

	/**
	 * GIVEN: A newspaper with no enough copies in stock
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: new order is generated with name and 20
	 */
	@Test
	public void noEnoughCopiesNewspaperNoOrderTest() {
		Newspaper np = new Newspaper("Hola", 1, 15);
		Order order = new Order("Hola", Newspaper.MIN_ORDER);
		assertEquals(order, np.generateOrders());
	}

	/**
	 * GIVEN: A weekly magazine with enough copies in stock
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: no new order is generated
	 */
	@Test
	public void enoughCopiesWeeklyMagazineNoOrderTest() {
		Magazine mg = new Magazine("Hola", Magazine.MIN_STOCK + 10, 15, Magazine.WEEKLY);
		assertEquals(null, mg.generateOrders());
	}

	/**
	 * GIVEN: A weekly magazine with copies in stock in the limit
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: no new order is generated
	 */
	@Test
	public void limitCopiesWeeklyMagazineNoOrderTest() {
		Magazine mg = new Magazine("Hola", Magazine.MIN_STOCK + 1, 15, Magazine.WEEKLY);
		assertEquals(null, mg.generateOrders());
	}

	/**
	 * GIVEN: A weekly magazine with copies in stock under 5
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: order is generated to order 20 copies
	 */
	@Test
	public void weeklyMagazineCopiesUnder5Order20() {
		Magazine mg = new Magazine("Hola", Magazine.MIN_STOCK - 1, 15, Magazine.WEEKLY);
		Order order = new Order("Hola", Magazine.MIN_ORDER);
		assertEquals(order, mg.generateOrders());
	}

	/**
	 * GIVEN: A weekly magazine with copies in stock equals 5
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: order is generated to order number of copies sold
	 */
	@Test
	public void weeklyMagazine5CopiesOrderSold() {
		Magazine mg = new Magazine("Hola", Magazine.MIN_STOCK, 15, Magazine.WEEKLY);
		Order order = new Order("Hola", 15);
		assertEquals(order, mg.generateOrders());
	}

	/**
	 * GIVEN: A monthly magazine with enough copies in stock
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: no new order is generated
	 */
	@Test
	public void enoughCopiesMonthlyMagazineNoOrderTest() {
		Magazine mg = new Magazine("Hola", Magazine.MIN_STOCK + 10, 15, Magazine.MONTHLY);
		assertEquals(null, mg.generateOrders());
	}

	/**
	 * GIVEN: A monthly magazine with copies in stock in the limit (10)
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: no new order is generated
	 */
	@Test
	public void limitCopiesMonthlyMagazineNoOrderTest() {
		Magazine mg = new Magazine("Hola", Magazine.MIN_STOCK + 5, 15, Magazine.MONTHLY);
		assertEquals(null, mg.generateOrders());
	}

	/**
	 * GIVEN: A monthly magazine with copies in stock under 5
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: order is generated to order 20 copies
	 */
	@Test
	public void monthlyMagazineCopiesUnder5Order20() {
		Magazine mg = new Magazine("Hola", Magazine.MIN_STOCK - 1, 15, Magazine.MONTHLY);
		Order order = new Order("Hola", Magazine.MIN_ORDER);
		assertEquals(order, mg.generateOrders());
	}

	/**
	 * GIVEN: A monthly magazine with copies in stock equals 5
	 * <p>
	 * WHEN: generate orders
	 * <p>
	 * THEN: order is generated to order number of copies sold + number of copies in
	 * stock
	 */
	@Test
	public void monthlyMagazine5CopiesOrderSoldPlusStock() {
		Magazine mg = new Magazine("Hola", Magazine.MIN_STOCK, 15, Magazine.MONTHLY);
		Order order = new Order("Hola", Magazine.MIN_STOCK + 15);
		assertEquals(order, mg.generateOrders());
	}

}
