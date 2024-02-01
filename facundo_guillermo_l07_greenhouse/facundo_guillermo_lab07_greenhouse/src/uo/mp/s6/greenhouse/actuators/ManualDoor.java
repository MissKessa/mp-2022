package uo.mp.s6.greenhouse.actuators;

public class ManualDoor extends Door {

  public ManualDoor(int id) {
    super(id);
  }

  @Override
  public String open() {
    if (!super.isOpened()) {
      super.isOpened = true;;
      return "Please, open door " + getId();
    }
    return "";
  }

  @Override
  public String close() {
    if (super.isOpened()) {
      super.isOpened = false;
      return "Please, close door" + getId();
    }
    return "";
  }

}
