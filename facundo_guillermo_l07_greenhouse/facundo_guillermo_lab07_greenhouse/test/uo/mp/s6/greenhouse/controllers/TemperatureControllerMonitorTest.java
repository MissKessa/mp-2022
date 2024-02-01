package uo.mp.s6.greenhouse.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import uo.mp.s6.greenhouse.actuators.AutomaticDoor;
import uo.mp.s6.greenhouse.actuators.Door;
import uo.mp.s6.greenhouse.sensors.TemperatureSensor;

public class TemperatureControllerMonitorTest {

  private final int HOT = 25;
  private final int COLD = 16;

  class FakeTemperatureSensor extends TemperatureSensor {

    private int value;

    public FakeTemperatureSensor(int id, int value) {
      super(id);
      this.value = value;
    }

    @Override
    public int getTemperature() {
      return this.value;
    }
  }


  private TemperatureController controller = new TemperatureController();

  @Test
  /**
   * GIVEN: a set of ta. sensors, a hot day and enough doors to open WHEN: control temperature THEN:
   * warning messages opening doors
   */
  void tooHotEnoughDoors() {
    String[] m = {"Current temp is 25.0. It is too hot", "3 doors must be opened",
        "Automatic door 0 is opening", "Automatic door 1 is opening",
        "Automatic door 2 is opening"};
    List<String> expected = Arrays.asList(m);
    for (int i = 0; i < 10; i++) {
      controller.add(new FakeTemperatureSensor(i, HOT)); // HOT = 25;
      controller.add(new AutomaticDoor(i));
    }
    List<String> messages = controller.monitor();
    assertEquals(expected, messages);
  }

  @Test
  /**
   * GIVEN: a set of ta. sensors, a hot day and no enough doors to open WHEN: control temperature
   * THEN: warning messages opening doors, warning message no enough doors to open
   */
  void tooHotNotEnoughDoors() {
    String[] m = {"Current temp is 25.0. It is too hot", "3 doors must be opened",
        "WARNING: Can not open enough doors. 3 more doors should be opened"};
    List<String> expected = Arrays.asList(m);
    Door door;

    for (int i = 0; i < 10; i++) {
      controller.add(new FakeTemperatureSensor(i, HOT));
      door = new AutomaticDoor(i);
      door.open();
      controller.add(door);
    }
    List<String> messages = controller.monitor();
    assertEquals(messages, expected);
  }

  @Test
  /**
   * GIVEN: a set of ta. sensors, a cold day and enough doors to close WHEN: control temperature
   * THEN: warning messages closing doors
   */
  void tooColdEnoughDoors() {
    String[] m = {"Current temp is 16.0. It is too cold", "3 doors must be closed",
        "Automatic door 0 is closing", "Automatic door 1 is closing",
        "Automatic door 2 is closing"};
    List<String> expected = Arrays.asList(m);
    Door door;

    for (int i = 0; i < 10; i++) {
      controller.add(new FakeTemperatureSensor(i, COLD));
      door = new AutomaticDoor(i);
      door.open();
      controller.add(door);
    }
    List<String> messages = controller.monitor();
    assertEquals(messages, expected);
  }

  @Test
  /**
   * GIVEN: a set of ta. sensors, a cold day and no enough doors to close WHEN: control temperature
   * THEN: warning messages opening doors, warning message no enough doors to close
   */
  void tooColdNotEnoughDoors() {
    String[] m = {"Current temp is 16.0. It is too cold", "3 doors must be closed",
        "WARNING: Can not close enough doors. 3 more doors should be closed"};
    List<String> expected = Arrays.asList(m);

    for (int i = 0; i < 10; i++) {
      controller.add(new FakeTemperatureSensor(i, COLD));
      controller.add(new AutomaticDoor(i));
    }
    List<String> messages = controller.monitor();
    assertEquals(messages, expected);
  }
}
