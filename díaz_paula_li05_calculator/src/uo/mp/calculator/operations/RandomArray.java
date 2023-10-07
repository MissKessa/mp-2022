package uo.mp.calculator.operations;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RandomArray {

  private final Scanner console;

  public RandomArray(Scanner console) {
    this.console = console;
    this.run();
  }

  private void run() {
    System.out.print("how many numbers you want to generate: ");
    int size = console.nextInt();

    final int[] randomNumbers = new int[size];

    for (int i = 0; i <= size; i++) {
      randomNumbers[i] = new Random().nextInt();
    }

    System.out.printf("Here is your array [%s].\n\n", Arrays.toString(randomNumbers));
  }
}
