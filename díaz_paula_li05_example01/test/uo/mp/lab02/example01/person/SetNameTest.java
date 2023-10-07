package uo.mp.lab02.example01.person;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import uo.mp.lab02.example01.Person;

class SetNameTest {

  /**
   * GIVEN: a valid Person object, created from a valid String name.
   * <p>
   * WHEN: the setName method is invoked with valid parameters.
   * <p>
   * THEN: no exception should be thrown.
   */
  @Test
  void testSetNameWithValidParameterDoesNotThrowExceptions() {
    final String validPersonName = "willy";
    final String anotherValidPersonName = "willy2";
    final Person willy = new Person(validPersonName);

    try {
      willy.setName(anotherValidPersonName);
    } catch (Exception exception) {
      fail("No exception should be thrown with valid parameters");
    }
  }
}
