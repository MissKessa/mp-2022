package uo.mp.s6.greenhouse.controllers;

import java.util.ArrayList;
import java.util.List;

import uo.mp.s6.greenhouse.actuators.Irrigator;
import uo.mp.s6.greenhouse.actuators.State;
import uo.mp.s6.greenhouse.sensors.HumiditySensor;

/**
 * The humidity controller reads and actuates over the humidity of the
 * greenhouse using both the HumiditySensors and the Irrigators. It also defined
 * the max and min humidity to 60%-40%.
 *
 * @author Paula
 * @version 2023
 */
public class HumidityController {
	public static final double MAX_HUMIDTY = 60;
	public static final double MIN_HUMIDTY = 40;

	private final List<HumiditySensor> sensors = new ArrayList<>();
	private final List<Irrigator> irrigators = new ArrayList<>();

	public void add(HumiditySensor sensor) {
		this.sensors.add(sensor);
	}

	public void add(Irrigator irrigator) {
		this.irrigators.add(irrigator);
	}

	/**
	 * Handles the irrigators to keep the humidity under control between 40% and 60%
	 *
	 * @return A list of messages generated when monitoring
	 */
	public List<String> monitor() {
		List<String> messages = new ArrayList<>();

		double avgHumidity = calculateAverageHumidity();

		if (isTooHigHumidity(avgHumidity))
			messages.addAll(reduceHumidity(avgHumidity));

		else if (isTooLowHumidity(avgHumidity))
			messages.addAll(increaseHumidity(avgHumidity));
		else
			messages.add(rightHumidity(avgHumidity));

		return messages;
	}

	private boolean isTooHigHumidity(double avgHumidity) {
		return avgHumidity > MAX_HUMIDTY;
	}

	private boolean isTooLowHumidity(double avgHumidity) {
		return avgHumidity < MIN_HUMIDTY;
	}

	/**
	 * Increases the humidity of the greenhouse
	 * 
	 * @param hum the average humidity calculate
	 * @return a list of messages to inform the gardener, produced while trying to
	 *         reduce the humidity the greenhouse
	 */
	private List<String> reduceHumidity(double hum) {
		List<String> messages = new ArrayList<>();

		int irrigatorsPerHumidity = irrigators.size() / 10;
		double diff = hum - MAX_HUMIDTY;
		messages.add("Current humidity is " + hum + "%. It is too high");

		int irrigatorsToClose = (int) Math.ceil(diff * irrigatorsPerHumidity);
		if (diff > 20) {
			messages.add(irrigatorsToClose + " irrigators must be closed");
			messages.addAll(closeIrrigators(irrigatorsToClose));
		} else {
			messages.add(irrigatorsToClose + " irrigators must reduce their level");
			messages.addAll(reduceLevelIrrigators(irrigatorsToClose));
		}
		return messages;
	}

	/**
	 * Tries to close irrigatorsToClose irrigators
	 * 
	 * @param irrigatorsToClose number of irrigators to close
	 * @return a list of messages to inform the gardener, produced while trying
	 *         close as many irrigators as the argument
	 */
	private List<String> closeIrrigators(int irrigatorsToClose) {
		List<String> messages = new ArrayList<>();

		for (Irrigator irrigator : irrigators) {
			if (irrigatorsToClose == 0)
				return messages;
			if (irrigator.isOpened()) {
				messages.add(irrigator.close());
				irrigatorsToClose--;
			}
		}
		if (irrigatorsToClose > 0) {
			messages.add("WARNING: Can not close enough irrigators. " + irrigatorsToClose
					+ " more irrigators should be closed");
		}
		return messages;
	}

	/**
	 * Tries to reduce the level of irrigatorsToReduce irrigators
	 * 
	 * @param irrigatorsToReduce number of irrigators to reduce
	 * @return a list of messages to inform the gardener, produced while trying to
	 *         reduce as many irrigators as the argument
	 */
	private List<String> reduceLevelIrrigators(int irrigatorsToReduce) {
		List<String> messages = new ArrayList<>();

		for (Irrigator irrigator : irrigators) {
			if (irrigatorsToReduce == 0)
				return messages;
			if (irrigator.isOpened()) {
				messages.add(irrigator.reduceLevel());
				irrigatorsToReduce--;
			}
		}
		if (irrigatorsToReduce > 0) {
			messages.add("WARNING: Can not reduce the level enough irrigators. " + irrigatorsToReduce
					+ " more irrigators should be reduce");
		}
		return messages;
	}

	/**
	 * Increases the humidity of the greenhouse
	 * 
	 * @param hum the average humidity calculate
	 * @return a list of messages to inform the gardener, produced while trying to
	 *         increase the humidity the greenhouse
	 */
	private List<String> increaseHumidity(double hum) {
		List<String> messages = new ArrayList<>();

		int irrigatorsPerHumidity = irrigators.size() / 10;
		double diff = MIN_HUMIDTY - hum;
		messages.add("Current humidity is " + hum + "%. It is too low");

		int irregatorsToIncrease = (int) Math.ceil(diff * irrigatorsPerHumidity);
		if (diff > 20) {
			messages.add(irregatorsToIncrease + " irrigators must set to high level");
			messages.addAll(setToHighLevelTheIrrigators(irregatorsToIncrease));
		} else {
			messages.add(irregatorsToIncrease + " irrigators must increase their level");
			messages.addAll(increaseLevelIrrigators(irregatorsToIncrease));
		}
		return messages;
	}

	/**
	 * Tries to set to high level irregatorsToIncrease irrigators
	 * 
	 * @param irregatorsToIncrease number of irrigators to close
	 * @return a list of messages to inform the gardener, produced while trying set
	 *         to high level as many irrigators as the argument
	 */
	private List<String> setToHighLevelTheIrrigators(int irregatorsToIncrease) {
		List<String> messages = new ArrayList<>();

		for (Irrigator irrigator : irrigators) {
			if (irregatorsToIncrease == 0)
				return messages;
			if (!irrigator.isAtMaximumLevel()) {
				messages.add(irrigator.setState(State.HIGH));
				irregatorsToIncrease--;
			}
		}
		if (irregatorsToIncrease > 0) {
			messages.add("WARNING: Can not set to maximum level enough irrigators. " + irregatorsToIncrease
					+ " more irrigators should be set to maximum level");
		}
		return messages;
	}

	/**
	 * Tries to increase the level of irregatorsToIncrease irrigators
	 * 
	 * @param irregatorsToIncrease number of irrigators to increase
	 * @return a list of messages to inform the gardener, produced while trying to
	 *         increase as many irrigators as the argument
	 */
	private List<String> increaseLevelIrrigators(int irregatorsToIncrease) {
		List<String> messages = new ArrayList<>();

		for (Irrigator irrigator : irrigators) {
			if (irregatorsToIncrease == 0)
				return messages;
			if (!irrigator.isAtMaximumLevel()) {
				messages.add(irrigator.increaseLevel());
				irregatorsToIncrease--;
			}
		}
		if (irregatorsToIncrease > 0) {
			messages.add("WARNING: Can not reduce the level enough irrigators. " + irregatorsToIncrease
					+ " more irrigators should be reduce");
		}
		return messages;
	}

	private String rightHumidity(double hum) {
		return "Current humidity is " + hum + "%";
	}

	private double calculateAverageHumidity() {
		double addition = 0;
		for (HumiditySensor sensor : sensors) {
			addition += sensor.getCurrentValue();
		}
		return (addition / sensors.size());
	}

	@Override
	public String toString() {
		return String.format("HumidityController [sensors=%s, irrigators=%s]", sensors, irrigators);
	}

}
