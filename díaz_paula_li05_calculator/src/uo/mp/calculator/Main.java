package uo.mp.calculator;

import java.util.Scanner;

import uo.mp.calculator.operations.Addition;
import uo.mp.calculator.operations.Division;
import uo.mp.calculator.operations.Maximum;
import uo.mp.calculator.operations.Minimum;
import uo.mp.calculator.operations.Multiplication;
import uo.mp.calculator.operations.RandomArray;
import uo.mp.calculator.operations.Substraction;
import uo.mp.calculator.operations.Summation;

public class Main {

	public static final int ADDITION_OPTION_NUMBER = 1;
	public static final int SUBSTRACTION_OPTION_NUMBER = 2;
	public static final int MULTIPLICATION_OPTION_NUMBER = 3;
	public static final int DIVISION_OPTION_NUMBER = 4;
	public static final int MINIMUM_OPTION_NUMBER = 5;
	public static final int MAXIMUM_OPTION_NUMBER = 6;
	public static final int SUMMATION_OPTION_NUMBER = 7;
	public static final int RANDOM_ARRAY_OPTION_NUMBER = 8;
	public static final int EXIT_OPTION_NUMBER = 9;

	public static void main(String[] args) {
		final Scanner console = new Scanner(System.in);

		int selectedOption = -1;
		do {
			selectedOption = printMenuAndGetSelectedOption(console);
			System.out.printf("You have selected option number %d. \n\n", selectedOption);
			executeSelectedOption(selectedOption, console);
		} while (selectedOption != EXIT_OPTION_NUMBER);

		System.out.println("See you!!!");
		console.close();

	}

	private static int printMenuAndGetSelectedOption(Scanner console) {
		System.out.println("Calculator options: ");
		System.out.printf("%d - Addition\n", ADDITION_OPTION_NUMBER);
		System.out.printf("%d - Substraction\n", SUBSTRACTION_OPTION_NUMBER);
		System.out.printf("%d - Multiplication\n", MULTIPLICATION_OPTION_NUMBER);
		System.out.printf("%d - Division\n", DIVISION_OPTION_NUMBER);
		System.out.printf("%d - Compute minimum\n", MINIMUM_OPTION_NUMBER);
		System.out.printf("%d - Compute maximum\n", MAXIMUM_OPTION_NUMBER);
		System.out.printf("%d - Compute summation\n", SUMMATION_OPTION_NUMBER);
		System.out.printf("%d - Generate array of random numbers\n", RANDOM_ARRAY_OPTION_NUMBER);
		System.out.printf("%d - Exit\n", EXIT_OPTION_NUMBER);
		System.out.print("Please, enter the desired option number: ");

		final int selectedOption = console.nextInt();

		return selectedOption;
	}

	private static void executeSelectedOption(int selectedOption, Scanner console) {
		switch (selectedOption) {
		case ADDITION_OPTION_NUMBER -> new Addition(console);
		case SUBSTRACTION_OPTION_NUMBER -> new Substraction(console);
		case MULTIPLICATION_OPTION_NUMBER -> new Multiplication(console);
		case DIVISION_OPTION_NUMBER -> new Division(console);
		case MINIMUM_OPTION_NUMBER -> new Minimum();
		case MAXIMUM_OPTION_NUMBER -> new Maximum();
		case SUMMATION_OPTION_NUMBER -> new Summation();
		case RANDOM_ARRAY_OPTION_NUMBER -> new RandomArray(console);
		}
	}
}
