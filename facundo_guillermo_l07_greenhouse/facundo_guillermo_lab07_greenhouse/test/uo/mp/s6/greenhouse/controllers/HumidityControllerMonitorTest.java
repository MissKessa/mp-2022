package uo.mp.s6.greenhouse.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import uo.mp.s6.greenhouse.actuators.Irrigator;
import uo.mp.s6.greenhouse.sensors.HumiditySensor;

class HumidityControllerMonitorTest {
	private final double VERY_HIGH_HUMIDITY = HumidityController.MAX_HUMIDTY + 30;
	private final double HIGH_HUMIDITY = HumidityController.MAX_HUMIDTY + 3;
	private final double LOW_HUMIDITY = HumidityController.MIN_HUMIDTY - 3;
	private final double VERY_LOW_HUMIDITY = HumidityController.MIN_HUMIDTY - 30;

	class FakeHumiditySensor extends HumiditySensor {

		private double value;

		public FakeHumiditySensor(int id, double value) {
			super(id);
			this.value = value;
		}

		@Override
		public double getCurrentValue() {
			return this.value;
		}
	}

	private HumidityController controller = new HumidityController();

	@Test
	/**
	 * GIVEN: a set of humidity sensors, a humid day and enough irrigators to reduce
	 * humidity
	 * <p>
	 * WHEN: control humidity
	 * <p>
	 * THEN: warning messages decreasing the level of irrigators
	 */
	void highHumidityEnoughIrrigators() {
		String[] m = { "Current humidity is 63.0%. It is too high", "3 irrigators must reduce their level",
				"Irrigator 0 is set to LOW,now set to OFF", "Irrigator 1 is set to MEDIUM,now set to LOW",
				"Irrigator 2 is set to HIGH,now set to MEDIUM" };

		List<String> expected = Arrays.asList(m);
		for (int i = 0; i < 10; i++) {
			controller.add(new FakeHumiditySensor(i, HIGH_HUMIDITY));
			Irrigator irr = new Irrigator(i);
			irr.setState(i + 1);
			controller.add(irr);
		}
		List<String> messages = controller.monitor();
		assertEquals(expected, messages);
	}

	@Test
	/**
	 * GIVEN: a set of humidity sensors, a humid day and enough irrigators to reduce
	 * humidity
	 * <p>
	 * WHEN: control humidity
	 * <p>
	 * THEN: warning messages decreasing the level of irrigators, warning not enough
	 * irrigators
	 */
	void tooHighHumidityNotEnoughIrrigators() {
		String[] m = { "Current humidity is 90.0%. It is too high", "30 irrigators must be closed",
				"Irrigator 1 is set to LOW,now set to OFF", "Irrigator 2 is set to MEDIUM,now set to OFF",
				"Irrigator 3 is set to HIGH,now set to OFF", "Irrigator 4 is set to HIGH,now set to OFF",
				"Irrigator 5 is set to HIGH,now set to OFF", "Irrigator 6 is set to HIGH,now set to OFF",
				"Irrigator 7 is set to HIGH,now set to OFF", "Irrigator 8 is set to HIGH,now set to OFF",
				"Irrigator 9 is set to HIGH,now set to OFF",
				"WARNING: Can not close enough irrigators. 21 more irrigators should be closed" };

		List<String> expected = Arrays.asList(m);
		for (int i = 0; i < 10; i++) {
			controller.add(new FakeHumiditySensor(i, VERY_HIGH_HUMIDITY));
			Irrigator irr = new Irrigator(i);
			irr.setState(i);
			controller.add(irr);
		}
		List<String> messages = controller.monitor();
		assertEquals(expected, messages);
	}

	@Test
	/**
	 * GIVEN: a set of humidity sensors, a humid day and enough irrigators to reduce
	 * humidity
	 * <p>
	 * WHEN: control humidity
	 * <p>
	 * THEN: warning messages decreasing the level of irrigators
	 */
	void lowHumidityEnoughIrrigators() {
		String[] m = { "Current humidity is 37.0%. It is too low", "3 irrigators must increase their level",
				"Irrigator 0 is set to OFF,now set to LOW", "Irrigator 1 is set to LOW,now set to MEDIUM",
				"Irrigator 2 is set to MEDIUM,now set to HIGH" };

		List<String> expected = Arrays.asList(m);
		for (int i = 0; i < 10; i++) {
			controller.add(new FakeHumiditySensor(i, LOW_HUMIDITY));
			Irrigator irr = new Irrigator(i);
			irr.setState(i);
			controller.add(irr);
		}
		List<String> messages = controller.monitor();
		assertEquals(expected, messages);
	}

	@Test
	/**
	 * GIVEN: a set of humidity sensors, a humid day and enough irrigators to reduce
	 * humidity
	 * <p>
	 * WHEN: control humidity
	 * <p>
	 * THEN: warning messages decreasing the level of irrigators, warning not enough
	 * irrigators
	 */
	void tooLowHumidityNotEnoughIrrigators() {
		String[] m = { "Current humidity is 10.0%. It is too low", "30 irrigators must set to high level",
				"Irrigator 0 is set to OFF,now set to HIGH", "Irrigator 1 is set to LOW,now set to HIGH",
				"Irrigator 2 is set to MEDIUM,now set to HIGH",
				"WARNING: Can not set to maximum level enough irrigators. 27 more irrigators should be set to maximum level" };

		List<String> expected = Arrays.asList(m);
		for (int i = 0; i < 10; i++) {
			controller.add(new FakeHumiditySensor(i, VERY_LOW_HUMIDITY));
			Irrigator irr = new Irrigator(i);
			irr.setState(i);
			controller.add(irr);
		}
		List<String> messages = controller.monitor();
		assertEquals(expected, messages);
	}

}
