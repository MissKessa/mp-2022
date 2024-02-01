package uo.mp.exception;

import uo.mp.exception.util.Console;
import uo.mp.exception.util.Log;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	/**
	 * Starts the program and add the default handlers for any type of
	 * uncaught exception 
	 */
	private void run() {
		try {

			String fileName = Console.readString("File name");
			Accounter accounter = new Accounter(fileName);
			double balance = accounter.processFile();
			Console.show("The balance in your account is: " + balance);

		} catch (RuntimeException e) {
			handleDefaultRuntimeException(e);
		} catch (Exception e) {
			handleDefaultException(e);
		}
	}
	
	/**
	 * Shows a polite message to the user about the nature of the issue
	 * @param e
	 */
	private void handleDefaultException(Exception e) {
		String msg = "There has been problems that require you atention:\n"
				+ "\t" + e.getMessage() +  "\n"
				+ "Please try again.";
		
		Console.show( msg );
	}

	/**
	 * Shows a message to the final user, but logs the real problem onto a 
	 * log file for the technical staff to be able to analyze the problem
	 * @param e
	 */
	private void handleDefaultRuntimeException(RuntimeException e) {
		String msg = 
			"Due to some technical issues the program have had internal errors.\n"
			+ "Please contact the technical staff with the content of this message.\n"
			+ e.getMessage();

		Console.show( msg );
		Log.log( e );
	}

}
