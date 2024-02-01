package uo.mp.s6.greenhouse;

import java.util.List;

import uo.mp.s6.greenhouse.actuators.Door;
import uo.mp.s6.greenhouse.actuators.Irrigator;
import uo.mp.s6.greenhouse.controllers.Checkable;
import uo.mp.s6.greenhouse.controllers.DeviceScanner;
import uo.mp.s6.greenhouse.controllers.HumidityController;
import uo.mp.s6.greenhouse.controllers.TemperatureController;
import uo.mp.s6.greenhouse.sensors.HumiditySensor;
import uo.mp.s6.greenhouse.sensors.TemperatureSensor;

/**
 * This class encapsulates the main greenhouse controller.
 * <p>
 * It contains the temperature controller that will manage the temperature in
 * the greenhouse and allows to add sensors and actuators to it. Finally it also
 * contains the method (start) that creates the monitoring loop. On each
 * iteration of the start method the temperature sensor monitors and actuates
 * over the temperature of the greenhouse.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class GreenhouseController {

	private final TemperatureController tempCtrl = new TemperatureController();
	private final HumidityController humiCtrl = new HumidityController();
	private final DeviceScanner deviceScanner = new DeviceScanner();

	/**
	 * Adds temperature sensor to temperature controller.
	 * 
	 * @param sensor to add.
	 */
	public void add(TemperatureSensor sensor) {
		tempCtrl.add(sensor);
		if (sensor instanceof Checkable) {
			this.deviceScanner.add(sensor);
		}
	}

	/**
	 * Adds humidity sensor to humidity controller.
	 * 
	 * @param sensor to add.
	 */
	public void add(HumiditySensor sensor) {
		humiCtrl.add(sensor);
		if (sensor instanceof Checkable) {
			this.deviceScanner.add(sensor);
		}
	}

	/**
	 * Adds door to temperature controller.
	 * 
	 * @param door to add.
	 */
	public void add(Door door) {
		tempCtrl.add(door);
		if (door instanceof Checkable) {
			this.deviceScanner.add((Checkable) door);
		}
	}

	/**
	 * Adds irrigator to humidity controller.
	 * 
	 * @param irrigator to add.
	 */
	public void add(Irrigator irrigator) {
		humiCtrl.add(irrigator);
		if (irrigator instanceof Checkable) {
			this.deviceScanner.add((Checkable) irrigator);
		}
	}

	/**
	 * Starts the monitoring loop. On each iteration the temperature controller
	 * monitor method is invoked. This monitor method measures the average
	 * temperature and if needs to be actuated to meet the expected temperature
	 * notifies the gardener to open or close the needed door.
	 */
	public void start() {
		while (true) {
			/*
			 * Run temperature controller and displays messages returned to command the
			 * gardener
			 */
			display(tempCtrl.monitor());
			display(humiCtrl.monitor());
			sleep(2000);
		}
	}

	/**
	 * Prints the given list of messages in the command line interface.
	 * 
	 * @param messages is the String list that contains the messages to print.
	 */
	private void display(List<String> messages) {
		for (String message : messages) {
			System.out.println(message);
		}
	}

	/**
	 * This private auxiliary method simulates an sleep cycle for the given
	 * millisecond value.
	 * 
	 * @param millis is the milliseconds value to sleep for.
	 */
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// Ignore it for now...
		}
	}

	@Override
	public String toString() {
		return String.format("GreenhouseController [tempCtrl=%s]", tempCtrl);
	}

}
