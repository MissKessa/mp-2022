package uo.mp.lab11.marker.parser;

import java.util.ArrayList;
import java.util.List;

import uo.mp.lab11.marker.model.question.ChoiceQuestion;
import uo.mp.lab11.marker.model.question.GapQuestion;
import uo.mp.lab11.marker.model.question.Question;
import uo.mp.lab11.marker.model.question.ValueQuestion;
import uo.mp.lab11.marker.service.exception.ExamMarkerException;
import uo.mp.util.check.ArgumentChecks;

public class QuestionParser {
	private final static String CHOICE_QUESTION_TYPE_NAME = "choice";
	private final static String GAP_QUESTION_TYPE_NAME = "gap";
	private final static String VALUE_QUESTION_TYPE_NAME = "value";
	private final static int EXACT_LINE_LENGTH = 3;
	private final static String SEPARATOR = "\t";

	private final static int QUESTION_TYPE_POS = 0;
	private final static int QUESTION_WEIGHT_POS = 1;
	private final static int QUESTION_RIGHT_ANSWER_POS = 2;

	private int lineNumber = 1;

	/**
	 * 
	 * @param lines
	 * @return
	 * @throws IllegalArgumentException if lines is null
	 */
	public List<Question> parse(List<String> lines) throws ExamMarkerException {
		ArgumentChecks.noNullElements(lines, "Lines cannot have null elements");
		ArgumentChecks.notNull(lines, "");
		List<Question> questions = new ArrayList<>();
		for (String line : lines) {
			try {
				final String[] parts = splitLine(line);
				final Question question = parseQuestion(parts);
				questions.add(question);
			} catch (InvalidLineFormatException exception) {
			}
			lineNumber++;
		}
		return questions;
	}

	private String[] splitLine(String line) {
		return line.split(SEPARATOR);
	}

	private Question parseQuestion(String[] parts) throws InvalidLineFormatException {
		ArgumentChecks.notNull(parts, "The line cannot be null");
		ArgumentChecks.noNullElements(parts, "The line cannot have null elements");
		if (parts.length != EXACT_LINE_LENGTH) {
			throw new InvalidLineFormatException(lineNumber,
					String.format("The line must contain %s elements", EXACT_LINE_LENGTH));
		}

		String type = parts[QUESTION_TYPE_POS];

		return switch (type) {
		case CHOICE_QUESTION_TYPE_NAME -> parseChoiceQuestion(parts);
		case GAP_QUESTION_TYPE_NAME -> parseGapQuestion(parts);
		case VALUE_QUESTION_TYPE_NAME -> parseValueQuestion(parts);
		default -> throw new InvalidLineFormatException(lineNumber, String.format("Not such that type", type));
		};
	}

	private ValueQuestion parseValueQuestion(String[] parts) throws InvalidLineFormatException {
		double weigth = parseDouble(parts[QUESTION_WEIGHT_POS]);
		if (weigth < 0) {
			throw new InvalidLineFormatException(this.lineNumber,
					String.format("wrong format, has a negative number for the weight"));
		}

		double rightAnswer = parseDouble(parts[QUESTION_RIGHT_ANSWER_POS]);

		return new ValueQuestion(lineNumber, weigth, rightAnswer);
	}

	private GapQuestion parseGapQuestion(String[] parts) throws InvalidLineFormatException {

		double weigth = parseDouble(parts[QUESTION_WEIGHT_POS]);
		if (weigth < 0) {
			throw new InvalidLineFormatException(this.lineNumber,
					String.format("wrong format, has a negative number for the weight"));
		}

		String rightAnswer = parts[QUESTION_RIGHT_ANSWER_POS];

		return new GapQuestion(lineNumber, weigth, rightAnswer);
	}

	private ChoiceQuestion parseChoiceQuestion(String[] parts) throws InvalidLineFormatException {

		double weigth = parseDouble(parts[QUESTION_WEIGHT_POS]);
		if (weigth < 0) {
			throw new InvalidLineFormatException(this.lineNumber,
					String.format("wrong format, has a negative number for the weight"));
		}

		String rightAnswer = parts[QUESTION_RIGHT_ANSWER_POS];

		return new ChoiceQuestion(lineNumber, weigth, rightAnswer);
	}

	private Double parseDouble(String string) throws InvalidLineFormatException {
		try {
			return Double.parseDouble(string);
		} catch (NumberFormatException exception) {
			throw new InvalidLineFormatException(this.lineNumber, exception.getMessage());
		}
	}

}
