package uo.mp.minesweeper.util.fileutil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * A utility class to read/write objects of type T from/to a file
 * 
 * @param <T> is the type of objects going to be read/written
 */
public class ObjectUtil<T> {
	/**
	 * It's the error message thrown if the list of objects have some null objects
	 */
	private static final String THE_LIST_OF_OBJECTS_CANNOT_HAVE_NULL_OBJECTS = "The list of objects cannot have null objects";
	/**
	 * It's the error message thrown if the list of objects is null
	 */
	private static final String THE_LIST_OF_OBJECTS_CANNOT_BE_NULL = "The list of objects cannot be null";
	/**
	 * It's the error message thrown if the fileName is null
	 */
	private static final String ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_NULL = "The filename cannot be null";
	/**
	 * It's the error message thrown if the fileName is blank
	 */
	private static final String ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_BLANK = "The filename cannot be made of blanks";
	/**
	 * It's the error message thrown if the fileName is empty
	 */
	private static final String ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_EMPTY = "The filename cannot be empty";

	/**
	 * Reads the objects of a given file
	 * 
	 * @param inFileName is the name of the file
	 * @return a list of objects corresponding to the objects of the file
	 * @throws FileNotFoundException if the given file doesn't exist
	 */
	@SuppressWarnings("unchecked")
	public List<T> readObjects(String inFileName) throws FileNotFoundException {

		List<T> objects = new ArrayList<>();
		T object;
		try (final ObjectInputStream buffer = createReaderChain(inFileName)) {
			while ((object = (T) buffer.readObject()) != null) {
				objects.add(object);
			}
		} catch (EOFException exception) {

		} catch (FileNotFoundException exception) {
			throw exception;
		} catch (IOException | ClassNotFoundException exception) {
			throw new RuntimeException(exception);
		}
		return objects;

	}

	/**
	 * Checks if the fileName is null, blank or empty
	 * 
	 * @param fileName is the fileName to be checked
	 * @throws IllegalArgumentException if the name of the file is null, blank or
	 *                                  empty
	 */
	private void checkFileName(String fileName) {
		ArgumentChecks.notNull(fileName, ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_NULL);
		ArgumentChecks.notBlank(fileName, ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_BLANK);
		ArgumentChecks.notEmpty(fileName, ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_EMPTY);
	}

	/**
	 * Writes the given objects in a given file
	 * 
	 * @param outFileName is the name of the file
	 * @param objects     is the list of objects to be written
	 */
	public void writeObjects(String outFileName, List<T> objects) throws FileNotFoundException {
		ArgumentChecks.notNull(objects, THE_LIST_OF_OBJECTS_CANNOT_BE_NULL);
		ArgumentChecks.noNullElements(objects.toArray(), THE_LIST_OF_OBJECTS_CANNOT_HAVE_NULL_OBJECTS);

		try (final ObjectOutputStream buffer = createWriterChain(outFileName)) {
			for (T object : objects) {
				buffer.writeObject(object);
			}
		} catch (FileNotFoundException exception) {
			throw exception;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

	/**
	 * It creates a ObjectInputStream chain to read objects in a file
	 * 
	 * @param inFileName is the name of the file read
	 * @return the ObjectInputStream according to the type of file read
	 * @throws FileNotFoundException    if the file doesn't exist
	 * @throws IllegalArgumentException if the name of the file is null, blank or
	 *                                  empty
	 */
	private ObjectInputStream createReaderChain(String inFileName) throws FileNotFoundException, EOFException {
		checkFileName(inFileName);
		try {
			FileInputStream file = new FileInputStream(inFileName);
			final BufferedInputStream buffer = new BufferedInputStream(file);
			final ObjectInputStream objectStream = new ObjectInputStream(buffer);
			return objectStream;
		} catch (FileNotFoundException | EOFException exception) {
			throw exception;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

	/**
	 * It creates a ObjectOutputStream chain to write objects in a file
	 * 
	 * @param outFileName is the name of the file written
	 * @return the ObjectOutputStream according to the type of file written
	 * @throws FileNotFoundException    if the file doesn't exist
	 * @throws IllegalArgumentException if the name of the file is null, blank or
	 *                                  empty
	 */
	private ObjectOutputStream createWriterChain(String outFileName) {
		checkFileName(outFileName);

		try {
			FileOutputStream file = new FileOutputStream(outFileName);
			final BufferedOutputStream buffer = new BufferedOutputStream(file);
			final ObjectOutputStream objectStream = new ObjectOutputStream(buffer);

			return objectStream;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
