package uo.mp.minesweeper.util.log;

import java.io.FileNotFoundException;

/**
 * The SimpleLogger class is used to record the errors
 * 
 * @author Paula Díaz Álvarez
 *
 */
public interface SimpleLogger {
	/**
	 * Logs (records) information about the exception.
	 * 
	 * @param ex is the exception recorded
	 * @throws FileNotFoundException
	 */
	void log(Exception ex);
}
