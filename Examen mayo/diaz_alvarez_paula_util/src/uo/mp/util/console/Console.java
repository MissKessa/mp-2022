package uo.mp.util.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import uo.mp.util.check.ArgumentChecks;

/**
 * The console utility class includes the basic user console interaction
 * primitives. These primitives allow different types of data to be read from
 * the console. System.in is used as the console.
 * <p>
 * The file is organized as follows. First the console print utilities and then
 * the read utilities. The reading ones are organized in String and Java
 * primitive types. Each primitive has two versions, one where the console type
 * is read without a message and the other where a message is printed and then
 * the read operation is performed.
 *
 * @author Paula
 * @version 2023
 */
public final class Console {

	/**
	 * Format used to print the messages before waiting for the user input.
	 */
	private static final String SIMPLE_MESSAGE_FORMAT = "%s :";

	// --- prints section ---

	/**
	 * Prints the given message on the standard console output. The given message
	 * cannot be null, empty or blank. If any of these three cases occurs, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param string is the String to print in the standard output. Cannot be null,
	 *               empty nor blank.
	 * @throws IllegalArgumentException if the string parameter is not valid.
	 */
	private static void print(final String string) {
		ArgumentChecks.notNull(string, "A null String cannot be printed.");
		ArgumentChecks.notEmpty(string, "An empty String cannot be printed.");
		ArgumentChecks.notBlank(string, "A blank String cannot be printed.");
		System.out.print(string);
	}

	// --- String section ---

	/**
	 * Prints the message given on the screen and then waits until the user enters a
	 * sequence of characters and presses the new line key. It then reads the
	 * contents of that line and returns it as a String. The format of the printed
	 * line is found in SIMPLE_MESSAGE_FORMAT.
	 * 
	 * @param message is the message to be printed. It must be not null, empty not
	 *                blank.
	 * @return the String value the user entered in the standard input console.
	 * @throws IllegalArgumentException if the message parameter is not valid.
	 */
	public static String readString(final String message) {
		ArgumentChecks.notNull(message, "The message cannot be null.");
		ArgumentChecks.notEmpty(message, "The message cannot be empty.");
		ArgumentChecks.notBlank(message, "The message cannot be blank.");
		print(String.format(SIMPLE_MESSAGE_FORMAT, message));
		return readString();
	}

	/**
	 * When this method is invoked, execution is blocked until the user presses the
	 * new line key on the console. Once the new line key is pressed, all the
	 * content typed by the user up to that moment will be read and the value will
	 * be returned as a String.
	 * 
	 * @return all the content typed by the user from the moment this method is
	 *         invoked until the new line key is pressed, as a String.
	 * @throws RuntimeException if any error related to the standard input.
	 */
	public static String readString() {
		try (BufferedReader keyboard = new BufferedReader(
				new InputStreamReader(new UnclosableInputStream(System.in)))) {
			return keyboard.readLine();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	// --- int section ---

	/**
	 * Prints the message given on the screen and then waits until the user enters a
	 * sequence of numbers and presses the new line key. It then reads the contents
	 * of that line and returns it as an int. The format of the printed line is
	 * found in SIMPLE_MESSAGE_FORMAT.
	 * 
	 * @param message is the message to be printed. It must be not null, empty not
	 *                blank.
	 * @return the int value the user entered in the standard input console.
	 * @throws IllegalArgumentException if the message parameter is not valid.
	 * @throws NumberFormatException    if the entered data is not composed
	 *                                  exclusively of digits.
	 */
	public static int readInt(final String message) {
		ArgumentChecks.notNull(message, "The message cannot be null.");
		ArgumentChecks.notEmpty(message, "The message cannot be empty.");
		ArgumentChecks.notBlank(message, "The message cannot be blank.");
		print(String.format(SIMPLE_MESSAGE_FORMAT, message));
		return readInt();
	}

	/**
	 * When this method is invoked, execution is blocked until the user presses the
	 * new line key on the console. Once the new line key is pressed, all the
	 * content typed by the user up to that moment will be read and the value will
	 * be returned as an int.
	 * 
	 * @return all the content typed by the user from the moment this method is
	 *         invoked until the new line key is pressed, as an int.
	 * @throws RuntimeException      if any error related to the standard input.
	 * @throws NumberFormatException if the entered data is not composed exclusively
	 *                               of digits.
	 */
	public static int readInt() {
		final String userInput = readString();
		return Integer.parseInt(userInput);
	}

	// --- long section ---

	/**
	 * Prints the message given on the screen and then waits until the user enters a
	 * sequence of numbers and presses the new line key. It then reads the contents
	 * of that line and returns it as a long. The format of the printed line is
	 * found in SIMPLE_MESSAGE_FORMAT.
	 * 
	 * @param message is the message to be printed. It must be not null, empty not
	 *                blank.
	 * @return the long value the user entered in the standard input console.
	 * @throws IllegalArgumentException if the message parameter is not valid.
	 * @throws NumberFormatException    if the entered data can't be parsed to a
	 *                                  long.
	 */
	public static long readLong(final String message) {
		ArgumentChecks.notNull(message, "The message cannot be null.");
		ArgumentChecks.notEmpty(message, "The message cannot be empty.");
		ArgumentChecks.notBlank(message, "The message cannot be blank.");
		print(String.format(SIMPLE_MESSAGE_FORMAT, message));
		return readLong();
	}

	/**
	 * When this method is invoked, execution is blocked until the user presses the
	 * new line key on the console. Once the new line key is pressed, all the
	 * content typed by the user up to that moment will be read and the value will
	 * be returned as a long.
	 * 
	 * @return all the content typed by the user from the moment this method is
	 *         invoked until the new line key is pressed, as a long.
	 * @throws RuntimeException      if any error related to the standard input.
	 * @throws NumberFormatException if the entered data can't be parsed to a long.
	 */
	public static long readLong() {
		final String userInput = readString();
		return Long.parseLong(userInput);
	}

	// --- double section ---

	/**
	 * Prints the message given on the screen and then waits until the user enters a
	 * sequence of numbers and presses the new line key. It then reads the contents
	 * of that line and returns it as a double. The format of the printed line is
	 * found in SIMPLE_MESSAGE_FORMAT.
	 * 
	 * @param message is the message to be printed. It must be not null, empty not
	 *                blank.
	 * @return the long value the user entered in the standard input console.
	 * @throws IllegalArgumentException if the message parameter is not valid.
	 * @throws NumberFormatException    if the entered data can't be parsed to a
	 *                                  double.
	 */
	public static double readDouble(final String message) {
		ArgumentChecks.notNull(message, "The message cannot be null.");
		ArgumentChecks.notEmpty(message, "The message cannot be empty.");
		ArgumentChecks.notBlank(message, "The message cannot be blank.");
		print(String.format(SIMPLE_MESSAGE_FORMAT, message));
		return readDouble();
	}

	/**
	 * When this method is invoked, execution is blocked until the user presses the
	 * new line key on the console. Once the new line key is pressed, all the
	 * content typed by the user up to that moment will be read and the value will
	 * be returned as a double.
	 * 
	 * @return all the content typed by the user from the moment this method is
	 *         invoked until the new line key is pressed, as a double.
	 * @throws RuntimeException      if any error related to the standard input.
	 * @throws NumberFormatException if the entered data can't be parsed to a
	 *                               double.
	 */
	public static double readDouble() {
		final String userInput = readString();
		return Double.parseDouble(userInput);
	}

	// --- char section ---

	/**
	 * Prints the message given on the screen and then waits until the user enters a
	 * character and presses the new line key. It then reads the contents of that
	 * line (character) and returns it as a char. The format of the printed line is
	 * found in SIMPLE_MESSAGE_FORMAT.
	 * 
	 * @param message is the message to be printed. It must be not null, empty not
	 *                blank.
	 * @return the char value the user entered in the standard input console.
	 * @throws IllegalArgumentException if the message parameter is not valid.
	 * @throws ClassCastException       if the entered data can't be casted to a
	 *                                  char.
	 */
	public static char readChar(final String message) {
		ArgumentChecks.notNull(message, "The message cannot be null.");
		ArgumentChecks.notEmpty(message, "The message cannot be empty.");
		ArgumentChecks.notBlank(message, "The message cannot be blank.");
		print(String.format(SIMPLE_MESSAGE_FORMAT, message));
		return readChar();
	}

	/**
	 * When this method is invoked, execution is blocked until the user presses the
	 * new line key on the console. Once the new line key is pressed, all the
	 * content typed by the user up to that moment will be read and the value will
	 * be returned as a char.
	 * 
	 * @return all the content typed by the user from the moment this method is
	 *         invoked until the new line key is pressed, as a char.
	 * @throws RuntimeException   if any error related to the standard input.
	 * @throws ClassCastException if the entered data can't be casted to a char.
	 */
	public static char readChar() {
		try (BufferedReader keyboard = new BufferedReader(
				new InputStreamReader(new UnclosableInputStream(System.in)))) {
			return (char) keyboard.read();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
