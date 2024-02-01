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
			double balance = new Accounter(fileName).processFile();
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
		Console.show( "There has been problems that require you attention" );
		Console.show( e.getMessage() );
	}

	/**
	 * Shows a message to the final user, but logs the real problem onto a 
	 * log file for the technical staff to be able to analyze the problem
	 * @param e
	 */
	private void handleDefaultRuntimeException(RuntimeException e) {
		Console.show("Due to some technical issues the program have had internal errors.");
		Console.show("Please contact the technical staff with the content of this message.");
		Log.log( e.getMessage() );
	}

}
