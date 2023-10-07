package uo.mp.minesweeper.util.log;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.util.check.ArgumentChecks;
import uo.mp.minesweeper.util.fileutil.FileUtil;

/**
 * The File Logger implements the SimpleLogger and logs the content to be logged
 * in the a given file
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class FileLogger implements SimpleLogger {
	/**
	 * It's the format of the day and time of the errors logged
	 */
	private static final String TIME_FORMAT = "dd/MM/yy - HH:mm:ss";

	/**
	 * It's the format of the message logged
	 */
	private static final String EXCEPTION_FORMAT = "[%s]: %s";
	/**
	 * This is the error message thrown if the exception is null
	 */
	private static final String THE_EXCEPTION_CANNOT_BE_NULL = "The exception cannot be null";
	/**
	 * This is the error message thrown if the logFile name is empty
	 */
	private static final String THE_LOG_FILE_CANNOT_BE_EMPTY = "The logFile cannot be empty";
	/**
	 * This is the error message thrown if the logFile name is blank
	 */
	private static final String THE_LOG_FILE_CANNOT_BE_BLANK = "The logFile cannot be blank";
	/**
	 * This is the error message thrown if the logFile name is null
	 */
	private static final String THE_LOG_FILE_CANNOT_BE_NULL = "The logFile cannot be null";
	/**
	 * It's the usual file where errors are logged
	 */
	public static final String LOG_FILE = "minesweeper.log";
	private String logFile;

	/**
	 * Main constructor of the logger that sets the file where errors are logged to
	 * the given one
	 * 
	 * @param logFile is the fileName where errors are logged
	 * @throws IllegalArgumentException if the logFile is null, blank or empty
	 */
	public FileLogger(String logFile) {
		ArgumentChecks.notNull(logFile, THE_LOG_FILE_CANNOT_BE_NULL);
		ArgumentChecks.notBlank(logFile, THE_LOG_FILE_CANNOT_BE_BLANK);
		ArgumentChecks.notEmpty(logFile, THE_LOG_FILE_CANNOT_BE_EMPTY);

		File file = new File(logFile);
		this.logFile = file.getAbsolutePath();
	}

	@Override
	public void log(Exception ex) {
		ArgumentChecks.notNull(ex, THE_EXCEPTION_CANNOT_BE_NULL);
		String exception = formatException(ex);
		List<String> exceptions = new ArrayList<>();
		exceptions.add(exception);
		FileUtil file = new FileUtil();

		file.appendLines(logFile, exceptions);
	}

	/**
	 * It gives the format to the message of given exception
	 * 
	 * @param ex is the exception to give a format to its message
	 * @return the errors message in the format set
	 */
	private String formatException(Exception ex) {
		ArgumentChecks.notNull(ex, THE_EXCEPTION_CANNOT_BE_NULL);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
		return String.format(EXCEPTION_FORMAT, LocalDateTime.now().format(formatter), ex.getMessage());

	}

}
