package uo.mp.calculator.operations;

import java.util.Scanner;

public class Multiplication {

  private final Scanner console;

  public Multiplication(Scanner console) {
    this.console = console;
    this.run();
  }

  private void run() {
    System.out.print("Enter the first number: ");
    final int firstNumber = console.nextInt();

    System.out.print("Enter the second number: ");
    final int secondNumber = console.nextInt();

    int result = 0;
    for (int i = 0; i < secondNumber; i++) {
      result = result + firstNumber;
    }
    System.out.printf("The result of %s x %s is %s.\n\n", firstNumber, secondNumber, result);
  }
}
