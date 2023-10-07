package uo.mp.lab02.example01.person;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import uo.mp.lab02.example01.Person;

class ConstructorTest {

  /**
   * GIVEN: any string.
   * <p>
   * WHEN: a person is created using the given String.
   * <p>
   * THEN: no exception should be thrown.
   */
  @Test
  void testConstructorWithValidParametersDoesNotThrowExceptions() {
    final String validPersonName = "willy";

    try {
      new Person(validPersonName);
    } catch (Exception e) {
      fail("No exception should be thrown with valid params");
    }
  }

}
