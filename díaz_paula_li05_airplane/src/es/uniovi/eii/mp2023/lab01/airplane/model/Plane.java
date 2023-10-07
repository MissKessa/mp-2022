package es.uniovi.eii.mp2023.lab01.airplane.model;

/**
 * The aircraft class represents a basic aircraft where we have the aircraft identifier, the pilot
 * and the amount of fuel. In addition, the identifier must be a character between A and Z, both
 * included, the default identifier being A. The default fuel is 0 and shall always be represented
 * by a positive integer.
 * <p>
 * All values, from the identifier to the pilot cannot change their value after instantiating an
 * airplane.
 * 
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class Plane {

  public static final char DEFAULT_IDENTIFIER = 'A';
  public static final int DEFAULT_FUEL = 0;
  public static final int MIN_FUEL = 0;

  public static final char MIN_IDENTIFIER = 'A';
  public static final char MAX_IDENTIFIER = 'Z';

  private Person pilot;
  private char identifier;
  private int fuel;

  /**
   * This default constructor with no parameters creates an aircraft with no pilot (pilot value is
   * null) and with identifier and fuel initialized to default values.
   */
  public Plane() {
    setIdentifier(DEFAULT_IDENTIFIER);
    setFuel(DEFAULT_FUEL);
  }

  /**
   * This constructor takes three parameters, the pilot, the identifier and the aircraft fuel. After
   * validating the parameters, it assigns them to the instance it creates. If any of the parameters
   * is not valid then an IllegalArgumentException is thrown.
   * 
   * @param pilot is the reference to the instance representing the aircraft pilot.
   * @param identifier is the aircraft identifier, represented by a character between A-Z. If the
   *        value of the passed character is not in this range of allowed values an
   *        IllegalArgumentException is thrown.
   * @param fuel is the amount of fuel the aircraft has when it is created. It must be a positive
   *        amount. Otherwise an IllegalArgumentException is thrown.
   * 
   * @throws IllegalArgumentException if any of the arguments is not valid.
   */
  public Plane(Person pilot, char identifier, int fuel) {
    this();
    setPilot(pilot);
    setIdentifier(identifier);
    setFuel(fuel);
  }

  /**
   * This method modifies the reference of the pilot of this instance. In the case of this method no
   * validation is performed, so a null reference is allowed to be assigned. As it has private
   * visibility it is not allowed to be used from outside this class.
   * 
   * @param pilot is the person reference of the pilot. Can be null.
   */
  private void setPilot(Person pilot) {
    this.pilot = pilot;
  }

  /**
   * This method modifies the identifier of this instance. In the case of this method the value of
   * the identifier is validated so that the character is within the range
   * MIN_IDENTIFIER-MAX_IDENTIFIER. As it has private visibility it is not allowed to be used from
   * outside this class.
   * 
   * @param pilot is the person reference of the pilot. Can be null.
   * 
   * @throws IllegalArgumentException if the identifier is not valid.
   */
  private void setIdentifier(char identifier) {
    checkParam(identifier >= MIN_IDENTIFIER && identifier <= MAX_IDENTIFIER,
        "Identificador fuera de l�mites");
    this.identifier = identifier;
  }

  /**
   * This method mutates the state of the current instance by modifying the amount of fuel in the
   * aircraft. To do so, it receives the quantity to be set as an integer. If the value of the
   * number received is not greater than or equal to the minimum amount of fuel set by MIN_FUEL then
   * an IllegalArgumentException is thrown.
   * 
   * @param fuel is the fuel quantity to be set as an integer. If the value is not greater than or
   *        equals to the minimum amount set by MIN_FUEL then an IlleganArgumentException is thrown.
   * @throws IllegarArgumentException if the fuel quantity is below MIN_FUEL.
   */
  private void setFuel(int fuel) {
    checkParam(fuel >= MIN_FUEL, "Valor del combustible por debajo del l�mite");
    this.fuel = fuel;
  }

  /**
   * Gets the pilot of this instance. If no pilot set then null.
   * 
   * @return the pilot of this instance. If no pilot set then null.
   */
  public Person getPilot() {
    return pilot;
  }

  /**
   * Gets the identifier of this instance.
   * 
   * @return the identifier of this instance.
   */
  public char getIdentifier() {
    return identifier;
  }

  /**
   * This private method is useful to return the String representation of the instance pilot. As the
   * pilot is a reference that can potentially have the value null then this method translates the
   * null value to NO PILOT and any other value delegates to the object's own toString method.
   * 
   * @return the string representation of the pilot. If null " NO PILOT". Otherwise, the toString of
   *         the pilot.
   */
  private String getPilotToString() {
    if (getPilot() == null)
      return " NO PILOT";
    else
      return getPilot().toString();
  }

  /**
   * Gets the amount of fuel of this instance.
   * 
   * @return the amount of fuel of this instance.
   */
  public int getFuel() {
    return fuel;
  }

  /**
   * This method simulates flying during a fuel drive. If the fuel level has reached the minimum
   * then it returns false. Otherwise, it decrements the fuel by one unit and returns true.
   * 
   * @return true if the plane was able to fly. False otherwise.
   */
  public boolean fly() {
    if (getFuel() == MIN_FUEL)
      return false;
    else {
      setFuel(getFuel() - 1);
      return true;
    }
  }

  /**
   * This private method is simply used to validate the parameters. To do so, it takes a boolean
   * condition as the first parameter and a message as the second parameter. If the value of the
   * boolean expression is not true, an exception is thrown with the given message.
   * 
   * @param condition is the boolean expression that must be true to validate the parameter. If the
   *        condition is not true an IllegalArgumentException is thrown.
   * @param msg is the message to pass to the exception in the case the condition is false.
   * 
   * @throws IllegalArgumentException if the boolean condition is not true.
   */
  private void checkParam(boolean condition, String msg) {
    if (!condition) {
      throw new IllegalArgumentException(msg);
    }
  }

  @Override
  public String toString() {
    return String.format("%s %d %s", this.getIdentifier(), this.getFuel(), this.getPilotToString());
  }
}
