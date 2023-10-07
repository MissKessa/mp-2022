package uo.mp.calculator.operations;

import java.util.Scanner;

public class Summation {

  private final Scanner console;

  public Summation() {
    this.console = new Scanner(System.in);
    this.run();
    this.console.close();
  }

  @SuppressWarnings("unused")
  private void run() {
    System.out.print("Enter the numbers separated by and space: ");
    final String userInput = console.nextLine();
    final String[] stringNumbers = userInput.split(" ");
    final int[] numbers = new int[stringNumbers.length];

    for (int i = 0; i < stringNumbers.length; i++) {
      numbers[i] = Integer.parseInt(stringNumbers[i]);
    }

    int result = 0;
    int currentNumber = 0;
    for (int number : numbers) {
    	result += numbers[currentNumber];
    	currentNumber++;
    }

    System.out.printf("The summation of [%s] is %d.\n\n", userInput, result);
  }
}
