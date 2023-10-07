package uo.mp.minesweeper.util.log;

/**
 * The BasicSimple Logger implements the SimpleLogger and displays the content
 * to be logged in the standard error output
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class BasicSimpleLogger implements SimpleLogger {

	/**
	 * Displays the content to be logged in the standard error output, i.e. by
	 * executing System.err.println() statements
	 */
	@Override
	public void log(Exception ex) {
		System.err.println("This is a fake implementation of a simple logger");
		System.err.println("This information should go to a more sofisticated log system");
		ex.printStackTrace();
	}

}
