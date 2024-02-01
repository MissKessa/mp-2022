package uo.mp.s6.greenhouse.actuators;

import java.util.Random;
import uo.mp.s6.greenhouse.controllers.Checkable;

public class AutomaticDoor extends Door implements Checkable {

  public AutomaticDoor(int id) {
    super(id);
  }

  @Override
  public String open() {
    if (!super.isOpened()) {
      super.isOpened = true;
      return "Automatic door " + getId() + " is opening";
    }
    return "";
  }

  @Override
  public String close() {
    if (super.isOpened()) {
      super.isOpened = false;
      return "Automatic door " + getId() + " is closing";
    }
    return "";
  }

  @Override
  public String toString() {
    return "[AutomaticDoor] " + getId();
  }

  @Override
  public boolean check() {
    return new Random().nextDouble() >= 0.05;
  }
}
