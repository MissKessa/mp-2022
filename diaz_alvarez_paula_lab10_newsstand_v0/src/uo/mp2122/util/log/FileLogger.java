package uo.mp2122.util.log;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import uo.mp.util.check.ArgumentChecks;
import uo.mp2122.util.file.FileUtil;

public class FileLogger {
	/**
	 * Sends the string received as message to the log prefixing it with a timestamp
	 * 
	 * @param message
	 * @throws FileNotFoundException
	 */
	public static void log(String message) throws FileNotFoundException {
		ArgumentChecks.notNull(message, "The message cannot be null");
		FileUtil fileUtil = new FileUtil();
		List<String> messages = new ArrayList<>();
		messages.add(message);
		fileUtil.writeLines("Logger", messages);
	}

	/**
	 * Sends the full stack trace of the exception received to the log prefixing it
	 * with a timestamp
	 * 
	 * @param t, the exception to be logged
	 * @throws FileNotFoundException
	 */
	public static void log(Throwable t) throws FileNotFoundException {
		ArgumentChecks.notNull(t, "The throwable cannot be null");
		FileUtil fileUtil = new FileUtil();
		List<String> messages = new ArrayList<>();
		messages.add(t.getStackTrace().toString());
		fileUtil.writeLines("Logger", messages);

	}
}
