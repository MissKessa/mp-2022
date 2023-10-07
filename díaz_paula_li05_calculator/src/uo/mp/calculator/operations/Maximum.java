package uo.mp.calculator.operations;

import java.util.Scanner;

public class Maximum {

  private final Scanner console;

  public Maximum() {
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

    int maxNumber = 0;
    for (int number : numbers) {
      number = Math.abs(number);
      if (number > maxNumber) {
        maxNumber = number;
      }
    }

    System.out.printf("The max number of [%s] is %d.\n\n", userInput, maxNumber);
  }
}
