package uo.mp.s6.greenhouse.actuators;

/**
 * This class models a door that has an id and can be opened or closed.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 202301111648
 */
public abstract class Door {

  private final int id;
  protected boolean isOpened = false;

  /**
   * Creates a door instance with the given integer id.
   * 
   * @param id is the index of the door.
   */
  public Door(int id) {
    this.id = id;
  }

  /**
   * @return the id of the door.
   */
  public int getId() {
    return this.id;
  }

  /**
   * @returns true if the door is opened, close otherwise.
   */
  public boolean isOpened() {
    return this.isOpened;
  }

  /**
   * If the door is closed opens it and returns the message "Please, open the door %s.".
   * 
   * @return the message "Please, open the door %s." if the door was closed. Otherwise "".
   */
  public abstract String open();

  /**
   * If the door is opened closes it and returns the message "Please, close the door %s.".
   * 
   * @return the message "Please, close the door %s." if the door was opened. Otherwise "".
   */
  public abstract String close();

  @Override
  public String toString() {
    return String.format("Door [id=%s, isOpened=%s]", id, isOpened);
  }
}
