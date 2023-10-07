package uo.mp.minesweeper.session;

import java.util.List;

import uo.mp.minesweeper.ranking.GameRankingEntry;
import uo.mp.minesweeper.util.exceptions.GameException;
import uo.mp.minesweeper.util.exceptions.UserInteractionException;

/**
 * SessionInteractor provides methods for carrying out all user interaction that
 * has to do with the session (not the game itself).
 * 
 * @author Paula Díaz Álvarez
 *
 */
public interface SessionInteractor {
	public final static int EXIT_OPTION = 0;
	public final static int PLAY_OPTION = 1;
	public final static int SHOW_MY_RESULTS_OPTION = 2;
	public final static int SHOW_ALL_RESULTS_OPTION = 3;

	/**
	 * It prompts the user for a difficulty level
	 * 
	 * @return the answer with a GameLevel
	 * @throws UserInteractionException if the level is incorrect
	 */
	GameLevel askGameLevel() throws UserInteractionException;

	/**
	 * Asks the user for their userName
	 * 
	 * @return a String with the response. The name returned cannot be empty.
	 * @throws UserInteractionException
	 * @throws GameException            if the userName is incorrect
	 */
	String askUserName() throws GameException;

	/**
	 * Prompts the user to enter an option from the session menu.
	 * 
	 * @return an integer representing the option chosen from the possible options.
	 *         A value greater than zero will represent one of the available
	 *         actions. A value of zero will always represent the exit option
	 * @throws UserInteractionException
	 */
	int askNextOption() throws UserInteractionException;

	/**
	 * At the end of a game, asks the user if they want to save their score.
	 * 
	 * @return true if the answer is yes, and false otherwise.
	 * @throws UserInteractionException
	 */
	boolean doYouWantToRegisterYourScore() throws UserInteractionException;

	/**
	 * Displays the ranking with their complete information (tabular format, one
	 * line per GameRankingEntry).
	 * 
	 * @param ranking is a list of GameRankingEntry objects representing all scores
	 *                recorded in the system
	 */
	void showRanking(List<GameRankingEntry> ranking);

	/**
	 * Displays the ranking with its full information (tabular format, one line for
	 * each GameRankingEntry). It omits the information who is the user associated
	 * to each game (it is understood that it is the user stored in the session).
	 * 
	 * @param ranking is a list of GameRankingEntry objects representing all scores
	 *                recorded in the system
	 */
	void showPersonalRanking(List<GameRankingEntry> ranking);

	/**
	 * Displays a farewell message to the user when he/she chooses to log out.
	 */
	void showGoodBye();

	/**
	 * Communicates an error message to the user.
	 * 
	 * @param message is the error message shown
	 */
	void showErrorMessage(String message);

	/**
	 * Communicates severe error messages to the user. This method is to be used to
	 * report that the error cannot be fixed, and the program will terminate
	 * immediately.
	 * 
	 * @param message is the error message shown
	 */
	void showFatalErrorMessage(String message);
}
