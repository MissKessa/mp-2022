package es.uniovi.eii.mp2023.lab01.airplane.model;

/**
 * A person is understood as the abstraction of a name and an age. The name is represented as a
 * character string and the age as an integer. Likewise, the name cannot be a null reference and the
 * age must be between 0 and 120 years, not including the upper range.
 * <p>
 * In the case of this class, once the instance of a person has been created, neither its name nor
 * its age can be modified, but the values of each field can be accessed using the corresponding
 * getters.
 * 
 * @author Programming Methodology Teaching Staff
 * @version 2023
 */
public class Person {

  public static final int MIN_AGE = 0;
  public static final int MAX_AGE = 120;
  public static final int ADULTHOOD_AGE = 18;
  public static final int RETIREMENT_AGE = 65;

  private String name;
  private int age;

  /**
   * Default constructor for the Person type. This constructor takes two arguments, the name and the
   * age of the person. The name is a string that cannot be null. The age is an integer that must be
   * in the range ({@code MIN_AGE}-{@code MAX_AGE}]. If the name is null then an
   * IllegalArgumentException is thrown. If the age is not in the range
   * ({@code MIN_AGE}-{@code MAX_AGE}] then an IllegalArgumentException is thrown.
   * 
   * @param name is the name of the person. Cannot be null.
   * @param age is the age of the person as an integer. The value must be in the range
   *        ({@code MIN_AGE}-{@code MAX_AGE}].
   * 
   * @throws IllegalArgumentException if the name is null or if the age value is not within the
   *         range ({@code MIN_AGE}-{@code MAX_AGE}].
   */
  public Person(String name, int age) {
    setName(name);
    setAge(age);
  }

  /**
   * This method modifies the state of the instance by changing the name value. Access to this
   * method is private so that it can only be used from within the class itself. The purpose of the
   * method is to encapsulate the validation of the name value so that the current instance cannot
   * end up in an invalid state. If the given name value is not null then the state of the instance
   * is modified. Otherwise an IllegalArumentException is thrown
   * 
   * @param name is the name value to set to the person. Cannot be null.
   * 
   * @throws IllegalArgumentException if the name is null.
   */
  private void setName(String name) {
    checkParam(name != null, "The name cannot be null");
    this.name = name;
  }



  /**
   * This method modifies the state of the instance by changing the age value. Access to this method
   * is private so that it can only be used from within the class itself. The purpose of the method
   * is to encapsulate the validation of the age value so that the current instance cannot end up in
   * an invalid state. If the given age value is not in the range ({@code MIN_AGE}-{@code MAX_AGE}]
   * then the state of the instance will be modified. Otherwise an IllegalArumentException is
   * thrown.
   * 
   * @param age is the age value to set to the person. Must be in range
   *        ({@code MIN_AGE}-{@code MAX_AGE}].
   * 
   * @throws IllegalArgumentException if the age value is not in range
   *         ({@code MIN_AGE}-{@code MAX_AGE}].
   */
  private void setAge(int age) {
    checkParam(age >= MIN_AGE && age < MAX_AGE,
        String.format("The age must be in range (%d-%d]", MIN_AGE, MAX_AGE));
    this.age = age;
  }

  /**
   * This method allows access to the value of the person's name. Its visibility is public so that
   * it can be accessed from anywhere. The return value shall not be null in any case.
   * 
   * @return the name of the person as a non-null String.
   */
  public String getName() {
    return name;
  }

  /**
   * This method allows access to the value of the person's age. Its visibility is public so that it
   * can be accessed from anywhere.
   * 
   * @return the age of the person as a String.
   */
  public int getAge() {
    return age;
  }

  /**
   * This method indicates the critical age. The critical age is defined as the number of years a
   * person has left before reaching the age of majority, retirement or the number of years he/she
   * has been retired. @see {@code ADULTHOOD_AGE}, {@code RETIREMENT_AGE}.
   *
   * @return the number of years a person has left before reaching the age of majority, retirement
   *         or the number of years he/she has been retired.
   */
  public int getCriticalAge() {
    int criticalAge = -1; // Init as -1 as a safety measure.

    if (this.getAge() < ADULTHOOD_AGE) {
      criticalAge = ADULTHOOD_AGE - this.getAge();
    } else if (getAge() >= ADULTHOOD_AGE && this.getAge() < RETIREMENT_AGE) {
      criticalAge = RETIREMENT_AGE - this.getAge();
    } else {
      criticalAge = getAge() - RETIREMENT_AGE;
    }

    return criticalAge;
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

  /**
   * This method returns the String representation of the person. The format of the returned
   * representation is Name: NAME Age: age.
   *
   * @return the String representation of the person. The format of the returned representation is
   *         Name: NAME Age: age.
   */
  @Override
  public String toString() {
    return "Name: " + getName().toUpperCase() + " Age: " + getAge();
  }

}
