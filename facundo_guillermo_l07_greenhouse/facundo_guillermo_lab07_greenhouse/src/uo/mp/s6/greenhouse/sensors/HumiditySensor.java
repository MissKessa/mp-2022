package uo.mp.s6.greenhouse.sensors;

import java.util.Random;

import uo.mp.s6.greenhouse.controllers.Checkable;

/**
 * The humidity sensor reads the ambient humidity by means of the
 * getCurrentValue() method. This method is implemented as a random in the range
 * 0 - 100.
 *
 * @author Paula
 * @version 2023
 */
public class HumiditySensor implements Checkable {

	private final int id;

	/**
	 * Creates a humidity sensor instance from the given integer id.
	 * 
	 * @param id is the integer that represents the identifier of the humidity
	 *           sensor.
	 */
	public HumiditySensor(int id) {
		this.id = id;
	}

	/**
	 * Simulates a humidity measurement
	 * 
	 * It returns a value in the range [0, 100]
	 * 
	 * @return The humidity measured by the sensor.
	 */
	public double getCurrentValue() {
		return new Random().nextInt(100) + new Random().nextInt(100) / 100;
	}

	@Override
	public String toString() {
		return String.format("HumiditySensor [id=%s]", id);
	}

	@Override
	public boolean check() {
		return new Random().nextDouble() >= 0.05;
	}

}
