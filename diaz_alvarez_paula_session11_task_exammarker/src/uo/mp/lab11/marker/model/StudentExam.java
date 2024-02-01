package uo.mp.lab11.marker.model;

import java.util.ArrayList;
import java.util.List;

import uo.mp.lab11.marker.model.question.Question;
import uo.mp.lab11.marker.parser.InvalidLineFormatException;
import uo.mp.util.check.ArgumentChecks;

public class StudentExam {

	private String id;
	private ArrayList<String> answers;

	/**
	 * 
	 * @param id
	 * @param answers
	 * @throws IllegalArgumentException if - id is null or blank - id is not length
	 *                                  five or does not contain just digits -
	 *                                  answers is null
	 */
	public StudentExam(String id, List<String> answers) {
		ArgumentChecks.notNull(id, "The id cannot be null");
		ArgumentChecks.notBlank(id, "The id cannot be blank");
		ArgumentChecks.isTrue(id.length() == 5, "The id must have length 5");
		try {
			parseInteger(id);
		} catch (InvalidLineFormatException e) {
			throw new IllegalArgumentException("The id must contain only digits");
		}

		this.id = id;
		this.answers = new ArrayList<>(answers);
	}

	/**
	 * 
	 * @param questions
	 * @return
	 * @throws IllegalArgumentException if questions is null
	 */
	public double mark(List<Question> questions) {
		ArgumentChecks.notNull(questions, "The questions cannot be null");
		double mark = 0;

		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			ArgumentChecks.notNull(question, "The questions cannot have null questions");
			String answer;
			try {
				answer = this.answers.get(i);
				mark += question.mark(answer);
			} catch (Exception e) {
				break;
			}
		}
		return mark;

	}

	public String getId() {
		return id;
	}

	public String serialize() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id);
		for (String answer : this.answers) {
			sb.append(", ");
			sb.append(answer);
		}
		return sb.toString();
	}

	private Integer parseInteger(String string) throws InvalidLineFormatException {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException exception) {
			throw new InvalidLineFormatException(exception.getMessage());
		}
	}
}
