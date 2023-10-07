package uo.mp.lab02.example01.dni;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import uo.mp.lab02.example01.DNI;

class ConstructorTest {

  /**
   * GIVEN: an string made up of 9 characters.
   * <p>
   * WHEN: a DNI is created using the given String.
   * <p>
   * THEN: no exception should be thrown.
   */
  @Test
  void testConstructorWith9CharsDNIDoesNotThrowExceptions() {
    final String validDNI = "123456789";

    try {
      new DNI(validDNI);
    } catch (Exception e) {
      fail("No exception should be thrown with valid params");
    }
  }

  /**
   * GIVEN: an string made up of 7 characters.
   * <p>
   * WHEN: a DNI is created using the given String.
   * <p>
   * THEN: an exception should be thrown as the DNIs must be created from a 9 character strings.
   */
  @Test
  void testConstructorWith8CharsDNIDoesThrowAnIllegalArgumentException() {
    final String inValidDNI = "12345678";

    try {
      new DNI(inValidDNI);
      fail("An IllegalArgumentException should be thrown with valid params");
    } catch (IllegalArgumentException e) {
    }
  }

  /**
   * GIVEN: an string made up of 10 characters.
   * <p>
   * WHEN: a DNI is created using the given String.
   * <p>
   * THEN: an exception should be thrown as the DNIs must be created from a 9 character strings.
   */
  @Test
  void testConstructorWith10CharsDNIDoesThrowAnIllegalArgumentException() {
    final String inValidDNI = "1234567890";

    try {
      new DNI(inValidDNI);
      fail("An IllegalArgumentException should be thrown with valid params");
    } catch (IllegalArgumentException e) {
    }
  }
}
