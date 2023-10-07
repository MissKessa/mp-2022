package uo.mp.calculator.operations;

import java.util.Scanner;

public class Division {

  private final Scanner console;

  public Division(Scanner console) {
    this.console = console;
    this.run();
  }

  private void run() {
    System.out.print("Enter the first number: ");
    final int firstNumber = console.nextInt();

    System.out.print("Enter the second number: ");
    final int secondNumber = console.nextInt();

    final int result = firstNumber / secondNumber;
    System.out.printf("The result of %s / %s is %s.\n\n", firstNumber, secondNumber, result);
  }
}
