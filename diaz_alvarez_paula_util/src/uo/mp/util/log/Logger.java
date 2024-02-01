package uo.mp.util.log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

/**
 * A very basic implementation of a logger utility. For this the date are sent
 * to the System.err standard output. The format of every lines is: <timestamp>
 * <message>
 */
public class Logger {

	private static PrintStream out = System.err;

	/**
	 * Sends the string received as message to the log prefixing it with a timestamp
	 * 
	 * @param message
	 */
	public static void log(String message) {
		try (FileOutputStream file = new FileOutputStream("mp.log", true)) { // append mode
			out = new PrintStream(file);
			out.print(new Date() + " ");
			out.println(message);
		} catch (IOException e) {
			out.println(e.getMessage());
		}
	}

	/**
	 * Sends the full stack trace of the exception received to the log prefixing it
	 * with a timestamp
	 * 
	 * @param t, the exception to be logged
	 */
	public static void log(Throwable t) {
		try (FileOutputStream file = new FileOutputStream("mp.log", true)) { // append mode
			out = new PrintStream(file);
			out.print(new Date() + " ");
			t.printStackTrace(out);
		} catch (IOException e) {
			out.println(e.getMessage());
		}
	}
}
