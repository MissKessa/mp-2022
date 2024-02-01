package uo.mp.keyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadingIntegersFromKeyboard {

	public static void main(String[] args) {
		int i = askForAnInt();

		System.out.println("You have typed " + i);
	}

	private static int askForAnInt() {
		System.out.print("Please type an Integer: ");
		boolean valid = false;
		int result = 0;
		
		BufferedReader keyboard = 
				new BufferedReader(
						new InputStreamReader( System.in )
				);

		while ( ! valid) {
			try {
				String line = keyboard.readLine();
				result = Integer.parseInt(line);
				valid = true;
				
			} catch (IOException e) {
				System.out.println("Try again please");
				valid = false; // Error in readLine
				
			} catch (NumberFormatException e) {
				System.out.println("Try again please");
				valid = false; // The value is not a number
			}
		}
		return result;
	}

}
