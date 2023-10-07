package uo.mp.minesweeper.ranking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.ranking.parser.RankingParser;
import uo.mp.minesweeper.ranking.serializer.RankingSerializer;
import uo.mp.minesweeper.util.check.ArgumentChecks;
import uo.mp.minesweeper.util.exceptions.UserInteractionException;
import uo.mp.minesweeper.util.fileutil.FileUtil;
import uo.mp.minesweeper.util.fileutil.ObjectUtil;
import uo.mp.minesweeper.util.log.SimpleLogger;

/**
 * This class shall store a list of GameRankingEntry objects representing
 * completed games and provide methods to query this list.
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class GameRanking implements Serializable {

	/**
	 * It's the error message shown if the ranking file wans't found
	 */
	private static final String ERROR_MESSAGE_RANKING_FILE_NOT_FOUND = "The ranking file (.rnk) wasn't found";
	/**
	 * It's the error message shown if the given GameRankingEntry is null
	 */
	private static final String ERROR_MESSAGE_THE_ENTRY_CANNOT_BE_NULL = "The gameRankingEntry cannot be null";
	/**
	 * It's the error message shown if the userName is empty
	 */
	private static final String ERROR_MESSAGE_THE_USER_NAME_CANNOT_BE_EMPTY = "The userName cannot be empty";
	/**
	 * It's the error message shown if the userName is blank
	 */
	private static final String ERROR_MESSAGE_THE_USER_NAME_CANNOT_BE_BLANK = "The userName cannot be blank";
	/**
	 * It's the error message shown if the userName is null
	 */
	private static final String ERROR_MESSAGE_THE_USER_NAME_CANNOT_BE_NULL = "The userName cannot be null";
	/**
	 * It's the error message shown if the fileName is empty
	 */
	private static final String ERROR_MESSAGE_THE_FILENAME_CANNOT_BE_EMPTY = "The fileName cannot be empty";
	/**
	 * It's the error message shown if the fileName is blank
	 */
	private static final String ERROR_MESSAGE_THE_FILENAME_CANNOT_BE_BLANK = "The fileName cannot be blank";
	/**
	 * It's the error message shown if the fileName is null
	 */
	private static final String ERROR_MESSAGE_THE_FILENAME_CANNOT_BE_NULL = "The fileName cannot be null";
	/**
	 * It's the error message shown if the file wans't found
	 */
	private static final String ERROR_MESSAGE_FILE_NOT_FOUND = "The file to import wasn't found";
	/**
	 * It's the error message shown if the logger was null when importing form a
	 * file (parser)
	 */
	private static final String ERROR_MESSAGE_NULL_LOGGER_WHEN_IMPORTING = "The logger cannot be null when you want to import from a file";
	/**
	 * It's the error message shown if a null logger is introduced
	 */
	private static final String ERROR_MESSAGE_NULL_LOGGER = "The logger cannot be null";
	private static final long serialVersionUID = 1L;
	/**
	 * It's the ranking file name
	 */
	private static final String RANKING_FILE = "minesweeper.rnk";
	/**
	 * It's the real path of the ranking file name
	 */
	private static final String RANKING_FILE_PATH = new File(RANKING_FILE).getAbsolutePath();

	private List<GameRankingEntry> entries = new ArrayList<>();
	private SimpleLogger logger;

	/**
	 * This constructor initializes the entries with the entries stored in the given
	 * file.
	 * <p>
	 * If the rankingFile is the same as RANKING_FILE, then those rankings are
	 * uploaded.
	 * <p>
	 * If not, the rankings of the given file are uploaded, and the RANKING_FILE is
	 * updated with that content. If the file is not found, then the entries are
	 * initializes empty
	 * 
	 * @param rankingFile is the name of the file with the rankings stored
	 * @throws UserInteractionException if the file to import wasn't found
	 * @throws IllegalArgumentException if the fileName is null, blank or empty
	 * @throws IllegalStateException    if the logger is null
	 */
	public GameRanking(String rankingFile) throws UserInteractionException {
		checkFileName(rankingFile);

		if (rankingFile.equals(RANKING_FILE))
			importEntriesFromRankingFile();
		else
			importEntries(rankingFile);
	}

	/**
	 * It sets the logger to the given one
	 * 
	 * @param logger is the given logger
	 * @throws IllegalArgumentException is the logger is null
	 */
	public void setLogger(SimpleLogger logger) {
		ArgumentChecks.notNull(logger, ERROR_MESSAGE_NULL_LOGGER);
		this.logger = logger;
	}

	/**
	 * It exports the entries to the given file
	 * 
	 * @param fileName is the given file
	 * @throws IllegalArgumentException if the fileName is null, blank or empty
	 * 
	 */
	public void exportEntries(String fileName) {
		checkFileName(fileName);
		List<String> lines = RankingSerializer.serialize(entries);
		FileUtil util = new FileUtil();

		util.writeLines(fileName, lines);
	}

	/**
	 * Imports the entries from the given file, then the ranking file is updated
	 * 
	 * @param fileName is the given file
	 * @throws UserInteractionException if the file to import wasn't found
	 * @throws IllegalArgumentException if the fileName is null, blank or empty
	 * @throws IllegalStateException    if the logger is null
	 */
	public void importEntries(String fileName) throws UserInteractionException {
		checkFileName(fileName);
		FileUtil util = new FileUtil();
		if (logger == null) {
			throw new IllegalStateException(ERROR_MESSAGE_NULL_LOGGER_WHEN_IMPORTING);
		}
		RankingParser parser = new RankingParser(this.logger);

		try {
			List<String> lines = util.readLines(fileName);
			this.entries = parser.parse(lines);
		} catch (FileNotFoundException e) {
			throw new UserInteractionException(ERROR_MESSAGE_FILE_NOT_FOUND);
		}
		updateRankingFile();

	}

	/**
	 * It checks that the fileName is not null, blank or empty
	 * 
	 * @param fileName is the fileName to be checked
	 * @throws IllegalArgumentException if the fileName is null, blank or empty
	 */
	private void checkFileName(String fileName) {
		ArgumentChecks.notNull(fileName, ERROR_MESSAGE_THE_FILENAME_CANNOT_BE_NULL);
		ArgumentChecks.notBlank(fileName, ERROR_MESSAGE_THE_FILENAME_CANNOT_BE_BLANK);
		ArgumentChecks.notEmpty(fileName, ERROR_MESSAGE_THE_FILENAME_CANNOT_BE_EMPTY);
	}

	/**
	 * Imports the entries from the ranking file. If it's not found then, the
	 * entries start empty
	 */
	public void importEntriesFromRankingFile() {
		ObjectUtil<GameRankingEntry> util = new ObjectUtil<GameRankingEntry>();

		try {
			this.entries = util.readObjects(RANKING_FILE_PATH);

		} catch (FileNotFoundException e) {
			this.entries = new ArrayList<>();
		}
	}

	/**
	 * Updates the ranking file with the current entries
	 * 
	 * @throws RuntimeException if the ranking file wasn't found
	 */
	public void updateRankingFile() {
		ObjectUtil<GameRankingEntry> util = new ObjectUtil<GameRankingEntry>();
		try {
			util.writeObjects(RANKING_FILE_PATH, this.entries);

		} catch (FileNotFoundException e) {
			throw new RuntimeException(ERROR_MESSAGE_RANKING_FILE_NOT_FOUND);
		}
	}

	/**
	 * Adds the GameRankingEntry object to the end of the list of gameRankingEntries
	 * if the user has won. The ranking file is updated
	 * 
	 * @param gameRankingEntry is the given object to add
	 * @throws IllegalArgumentException if the gameRankingEntry is null
	 */
	public void append(GameRankingEntry gameRankingEntry) {
		ArgumentChecks.notNull(gameRankingEntry, ERROR_MESSAGE_THE_ENTRY_CANNOT_BE_NULL);
		if (gameRankingEntry.hasWon()) {
			this.entries.add(gameRankingEntry);
			updateRankingFile();
		}
	}

	/**
	 * 
	 * @return a copy of the list of gameRankingEntries.
	 */
	public List<GameRankingEntry> getAllEntries() {
		if (this.entries == null) {
			return null;
		}
		List<GameRankingEntry> copy = new ArrayList<>(this.entries.size());
		for (GameRankingEntry entry : this.entries) {
			copy.add(entry);
		}
		return copy;
	}

	/**
	 * Searchs for the entries with the given userName
	 * 
	 * @param userName is the given userName
	 * @return a list containing only those gameRankingEntries whose user matches
	 *         the userName received as parameter.
	 * @throws IllegalArgumentException if the userName is null, blank or empty
	 */
	public List<GameRankingEntry> getEntriesForUsername(String userName) {
		ArgumentChecks.notNull(userName, ERROR_MESSAGE_THE_USER_NAME_CANNOT_BE_NULL);
		ArgumentChecks.notBlank(userName, ERROR_MESSAGE_THE_USER_NAME_CANNOT_BE_BLANK);
		ArgumentChecks.notEmpty(userName, ERROR_MESSAGE_THE_USER_NAME_CANNOT_BE_EMPTY);

		List<GameRankingEntry> entriesOfTheUser = new ArrayList<>();
		if (this.entries == null) {
			return null;
		}
		for (GameRankingEntry entry : this.entries) {
			if (entry.getUserName().equals(userName)) {
				entriesOfTheUser.add(entry);
			}
		}
		return entriesOfTheUser;
	}
}
