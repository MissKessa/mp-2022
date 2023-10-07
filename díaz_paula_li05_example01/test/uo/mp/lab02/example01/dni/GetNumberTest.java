package uo.mp.lab02.example01.dni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import uo.mp.lab02.example01.DNI;

class GetNumberTest {

  /**
   * GIVEN: a valid DNI object, created from a valid String.
   * <p>
   * WHEN: the getNumber method is invoked after the creation.
   * <p>
   * THEN: the same value from the string used to create the object must be returned.
   */
  @Test
  void testGetNumberAfterConstructor() {
    final String validDNI = "123456789";
    DNI dni = new DNI(validDNI);

    assertEquals(validDNI, dni.getNumber());
  }

  /**
   * GIVEN: a valid DNI object, created from a valid String.
   * <p>
   * WHEN: the getNumber method is invoked after invoking the setNumber method with valid params.
   * <p>
   * THEN: the same value from the string used to invoke the setNumber must be returned.
   */
  @Test
  void testGetNumberAftersetNumberWithValidParameter() {
    final String validDNI = "123456789";
    final String anotherValidDNI = "987654321";
    DNI dni = new DNI(validDNI);
    dni.setNumber(anotherValidDNI);

    assertNotEquals(validDNI, dni.getNumber());
    assertEquals(anotherValidDNI, dni.getNumber());
  }

  /**
   * GIVEN: a valid DNI object, created from a valid String.
   * <p>
   * WHEN: the getNumber method is invoked after invoking the setNumber method with invalid params.
   * <p>
   * THEN: the value of the dni should not change.
   */
  @Test
  void testGetNumberAftersetNumberWithInvalidParameter() {
    final String validDNI = "123456789";
    final String invalidDNI = "1234567";
    DNI dni = new DNI(validDNI);

    try {
      dni.setNumber(invalidDNI);
    } catch (IllegalArgumentException exception) {
    }

    assertNotEquals(invalidDNI, dni.getNumber());
    assertEquals(validDNI, dni.getNumber());
  }
}
