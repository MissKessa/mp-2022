package uo.mp.s6.greenhouse;

import uo.mp.s6.greenhouse.actuators.AutomaticDoor;
import uo.mp.s6.greenhouse.actuators.Irrigator;
import uo.mp.s6.greenhouse.actuators.ManualDoor;
import uo.mp.s6.greenhouse.sensors.HumiditySensor;
import uo.mp.s6.greenhouse.sensors.TemperatureSensor;

/**
 * This Main class configures and starts the Greenhouse.
 * <p>
 * Initially this Main class will configure the Greenhouse to have 10
 * temperature sensors and 10 doors. Then invokes the start method from the
 * GreenhouseController class.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class Main {

	private GreenhouseController greenhouse;

	/**
	 * Main method that launches the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().configure().run();
	}

	/**
	 * Initializes the greenhouse to a new instance and adds 10 temperature sensors
	 * and doors to it.
	 * <p>
	 * Please, notice that this method uses API chaining and therefore its
	 * functionality is to modify the current instance of main and modify it by
	 * creating a new greenhouse controller and adding doors and temperature
	 * sensors.
	 * 
	 * @return the modified main instance.
	 */
	private Main configure() {
		greenhouse = new GreenhouseController();

		for (int i = 0; i < 10; i++) {
			greenhouse.add(new TemperatureSensor(i));
			greenhouse.add(new AutomaticDoor(i));
			greenhouse.add(new ManualDoor(i));
			greenhouse.add(new HumiditySensor(i));
			greenhouse.add(new Irrigator(i));
		}

		return this;
	}

	/**
	 * Invokes the start method over the greenhouse field. If the greenhouse is null
	 * then an IllegalStateException is thrown.
	 */
	public void run() {
		if (this.greenhouse == null) {
			throw new IllegalStateException("Please, invoke the configure method first.");
		}
		greenhouse.start();
	}

	@Override
	public String toString() {
		return String.format("Main [greenhouse=%s]", greenhouse);
	}

}
