package uo.mp.lab02.example01.dni;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import uo.mp.lab02.example01.DNI;

class SetNumberTest {

  /**
   * GIVEN: a valid DNI object, created from a valid String.
   * <p>
   * WHEN: the setNumber method is invoked with valid parameters.
   * <p>
   * THEN: no exception should be thrown.
   */
  @Test
  void testSetNumberWith9CharsDNIDoesNotThrowExceptions() {
    final String validDNI = "123456789";

    DNI dni = new DNI(validDNI);
    try {
      dni.setNumber(validDNI);
    } catch (Exception e) {
      fail("No exception should be thrown with valid params");
    }
  }

  /**
   * GIVEN: a valid DNI object, created from a valid String.
   * <p>
   * WHEN: the setNumber method is invoked with one less character dni parameter.
   * <p>
   * THEN: an exception should be thrown.
   */
  @Test
  void testSetNumberWith8CharsDNIDoesThrowAnIllegalArgumentException() {
    final String inValidDNI = "12345678";

    DNI dni = new DNI("123456789");
    try {
      dni.setNumber(inValidDNI);
      fail("An IllegalArgumentException should be thrown with valid params");
    } catch (IllegalArgumentException e) {
    }
  }

  /**
   * GIVEN: a valid DNI object, created from a valid String.
   * <p>
   * WHEN: the setNumber method is invoked with one extra character dni parameter.
   * <p>
   * THEN: an exception should be thrown.
   */
  @Test
  void testSetNumberWith10CharsDNIDoesThrowAnIllegalArgumentException() {
    final String inValidDNI = "1234567890";

    DNI dni = new DNI("123456789");
    try {
      dni.setNumber(inValidDNI);
      fail("An IllegalArgumentException should be thrown with valid params");
    } catch (IllegalArgumentException e) {
    }
  }
}
