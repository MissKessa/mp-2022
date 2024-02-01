package uo.mp.s6.greenhouse.sensors;

import java.util.Random;

import uo.mp.s6.greenhouse.controllers.Checkable;

/**
 * The temperature sensor reads the ambient temperature by means of the
 * getTemperature() method. This method is implemented as a random in the range
 * 5 - 40.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 202301111648
 */
public class TemperatureSensor implements Checkable {

	private final int id;

	/**
	 * Creates a temperature sensor instance from the given integer id.
	 * 
	 * @param id is the integer that represents the identifier of the temperature
	 *           sensor.
	 */
	public TemperatureSensor(int id) {
		this.id = id;
	}

	/**
	 * Simulates a temperature measurement
	 * 
	 * It returns a value in the range [5, 40)
	 * 
	 * @return The temperature measured by the sensor.
	 */
	public int getTemperature() {
		return new Random().nextInt(36) + 5;
	}

	@Override
	public String toString() {
		return String.format("TemperatureSensor [id=%s]", id);
	}

	@Override
	public boolean check() {
		return new Random().nextDouble() >= 0.05;
	}

}
