package uo.mp.lab02.example01;

public class DNI {

  private static final int LENGTH = 9;

  private String number;

  public DNI(String number) {
    setNumber(number);
  }

  public void setNumber(String number) {
    if (number.length() != LENGTH) {
      throw new IllegalArgumentException("The length of the DNI must be 9 chars");
    }
    this.number = number;
  }

  public String getNumber() {
    return this.number;
  }

  @Override
  public String toString() {
    return String.format("DNI [number=%s]", number);
  }

}
