package uo.mp.calculator.operations;

import java.util.Scanner;

public class Minimum {

  private final Scanner console;

  public Minimum() {
    this.console = new Scanner(System.in);
    this.run();
    this.console.close();
  }

  private void run() {
    System.out.print("Enter the numbers separated by and space: ");
    final String userInput = console.nextLine();
    final String[] stringNumbers = userInput.split(" ");
    final int[] numbers = new int[stringNumbers.length];

    for (int i = 0; i < stringNumbers.length; i++) {
      numbers[i] = Integer.parseInt(stringNumbers[i]);
    }

    int minNumber = Integer.MAX_VALUE;
    for (int number : numbers) {
      if (number < minNumber) {
        minNumber = number;
      }
    }

    System.out.printf("The minimum number of [%s] is %d.\n\n", userInput, minNumber);
  }
}
