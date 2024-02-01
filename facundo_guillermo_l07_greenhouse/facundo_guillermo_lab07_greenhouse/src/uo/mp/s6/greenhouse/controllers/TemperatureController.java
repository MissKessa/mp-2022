package uo.mp.s6.greenhouse.controllers;

import java.util.ArrayList;
import java.util.List;
import uo.mp.s6.greenhouse.actuators.Door;
import uo.mp.s6.greenhouse.sensors.TemperatureSensor;

/**
 * The temperature controller reads and actuates over the temperature of the greenhouse using both
 * the TemperatureSensors and the Doors. It also defined the max and min temperature to 22-19
 * degrees.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class TemperatureController {

  public static final double MAX_TEMPERATURE = 22;
  public static final double MIN_TEMPERATURE = 19;

  private final List<TemperatureSensor> sensors = new ArrayList<>();
  private final List<Door> doors = new ArrayList<>();

  public void add(TemperatureSensor sensor) {
    this.sensors.add(sensor);
  }

  public void add(Door door) {
    this.doors.add(door);
  }

  /**
   * Handles the doors to keep the temperature under control between 19 and 22 degrees
   *
   * @return A list of messages generated when monitoring
   */
  public List<String> monitor() {

    /*
     * It is colder outside, so opening the doors always involves cooling inside.
     * 
     * When getAverageTemperature is greater than maxTemperature (too hot) - Some doors should be
     * opened (if possible) for cooling - Each degree of difference
     * (getAverageTemperature-maxTemperature) involves opening 10% of the doors (if possible) - Show
     * a message telling how many doors are about to be opened (if possible)
     * 
     * When getAverageTemperature is lower than minTemperature - Some doors should be closed (if
     * possible) for heating - Each degree of difference (minTemperature-getAverageTemperature)
     * involves opening 10% of the doors (if possible) - Show a message telling how many doors are
     * about to be closed (if possible)
     *
     * Otherwise, a message showing the average temperature is simply shown on the console
     */

    /*
     * Holds strings to be returned to the control panel to inform the gardener after each and every
     * execution of method monitor()
     */
    List<String> messages = new ArrayList<>();

    double avgTemp = calculateAverageTemperature();
    if (isTooCold(avgTemp))
      messages.addAll(warmUp(avgTemp));
    else if (isTooHot(avgTemp))
      messages.addAll(coolDown(avgTemp));
    else
      messages.add(rightTemperature(avgTemp));
    return messages;
  }

  private boolean isTooCold(double avgTemp) {

    return avgTemp < MIN_TEMPERATURE;
  }

  private boolean isTooHot(double avgTemp) {

    return avgTemp > MAX_TEMPERATURE;
  }

  /**
   * Warms up the greenhouse
   * 
   * @param temp the average temperature calculate
   * @return a list of messages to inform the gardener, produced while trying to warm up the
   *         greenhouse
   */
  private List<String> warmUp(double temp) {
    List<String> messages = new ArrayList<>();

    int doorsPerDegree = doors.size() / 10;
    double diff = MIN_TEMPERATURE - temp;
    messages.add("Current temp is " + temp + ". It is too cold");

    int doorsToClose = (int) Math.ceil(diff * doorsPerDegree);
    if (doorsToClose > 0) {
      messages.add(doorsToClose + " doors must be closed");
      messages.addAll(closeDoors(doorsToClose));

    }
    return messages;
  }

  /**
   * Tries to close doorsToClose doors
   * 
   * @param doorsToClose number of doors to close
   * @return a list of messages to inform the gardener, produced while trying close as many doors as
   *         the argument
   */
  private List<String> closeDoors(int doorsToClose) {
    List<String> messages = new ArrayList<>();

    for (Door door : doors) {
      if (doorsToClose == 0)
        return messages;
      if (door.isOpened()) {
        messages.add(door.close());
        doorsToClose--;
      }
    }
    if (doorsToClose > 0) {
      messages.add(
          "WARNING: Can not close enough doors. " + doorsToClose + " more doors should be closed");
    }
    return messages;
  }

  /**
   * Cools down the greenhouse
   * 
   * @param temp the average temperature calculate
   * @return a list of messages to inform the gardener, produced while trying to cool down the
   *         greenhouse
   */
  private List<String> coolDown(double temp) {
    List<String> messages = new ArrayList<>();

    int doorsPerDegree = doors.size() / 10;
    double diff = temp - MAX_TEMPERATURE;
    messages.add("Current temp is " + temp + ". It is too hot");

    int doorsToOpen = (int) Math.ceil(diff * doorsPerDegree);
    if (doorsToOpen > 0) {
      messages.add(doorsToOpen + " doors must be opened");
      messages.addAll(openDoors(doorsToOpen));
    }
    return messages;
  }

  /**
   * Open as many doors as the value passed as argument
   * 
   * @param doorsToOpen number of doors to open
   * @return a list of messages to inform the gardener, probably empty, produced while trying to
   *         open the doors
   */
  private List<String> openDoors(int doorsToOpen) {
    List<String> messages = new ArrayList<>();

    for (Door door : doors) {
      if (doorsToOpen == 0)
        return messages;
      if (!door.isOpened()) {
        messages.add(door.open());
        doorsToOpen--;
      }
    }
    if (doorsToOpen > 0) {
      messages.add(
          "WARNING: Can not open enough doors. " + doorsToOpen + " more doors should be opened");
    }
    return messages;
  }

  private String rightTemperature(double temp) {
    return "Current temperature is " + temp + " It is right";
  }

  private double calculateAverageTemperature() {
    double addition = 0;
    for (TemperatureSensor sensor : sensors) {
      addition += sensor.getTemperature();
    }
    return (addition / sensors.size());
  }

  @Override
  public String toString() {
    return String.format("TemperatureController [sensors=%s, doors=%s]", sensors, doors);
  }

}
