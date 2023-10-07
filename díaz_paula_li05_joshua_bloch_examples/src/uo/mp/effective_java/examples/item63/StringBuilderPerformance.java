package uo.mp.effective_java.examples.item63;

import java.util.Random;

public class StringBuilderPerformance {

  public static void main(String[] args) {
    final Random randomGenerator = new Random();

    for (int problemIndex = 0; problemIndex < ProblemSize.PROBLEM_NAME.length; problemIndex++) {
      final String problemName = ProblemSize.PROBLEM_NAME[problemIndex];
      final int problemSize = ProblemSize.PROBLEM_VALUE[problemIndex];

      System.out.printf("Executing [%s] with size [%d]...\n", problemName, problemSize);
      final int[] myCollection = new int[problemSize];

      System.out.println("Generating random values...");
      for (int i = 0; i < myCollection.length; i++) {
        myCollection[i] = randomGenerator.nextInt();
      }

      System.out.println("Generating string representation by means of String Concatenation...");
      long initTime = System.currentTimeMillis();

      StringBuilder result = new StringBuilder();
      for (int i = 0; i < myCollection.length; i++) {
        result.append(myCollection[i]);
      }

      long endTime = System.currentTimeMillis();
      System.out.println(result.toString().length());
      System.out.printf("Time employed: %s millis.\n\n\n", endTime - initTime);
    }
  }

}
