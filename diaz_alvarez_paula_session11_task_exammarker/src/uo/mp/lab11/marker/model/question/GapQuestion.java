package uo.mp.lab11.marker.model.question;

import uo.mp.util.check.ArgumentChecks;

public class GapQuestion extends Question {

	private String rightAnswer;

	/**
	 * 
	 * @param weight
	 * @param rightAnswer
	 * @throws IllegalArgumentException if * weight <= 0 * rightAnswer is null or
	 *                                  blank
	 */
	public GapQuestion(int number, double weight, String rightAnswer) {
		super(number, weight);
		ArgumentChecks.notNull(rightAnswer, "The rightAnswer cannot be null");
		ArgumentChecks.notBlank(rightAnswer, "The rightAnswer cannot be blank");
		this.rightAnswer = rightAnswer;
	}

	/**
	 * 
	 * @param answer
	 * @return
	 * @throws IllegalArgumentException if answer is null or blank
	 */
	@Override
	public double mark(String answer) {
		ArgumentChecks.notNull(answer, "The answer cannot be null");
		ArgumentChecks.notBlank(answer, "The answer cannot be blank");
		return rightAnswer.equals(answer) ? getWeight() : 0.0;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}
}
