package uo.mp.lab11.marker.model.question;

import uo.mp.util.check.ArgumentChecks;

public abstract class Question implements Comparable<Question> {

	private int questionNumber;
	private Double weight;

	/**
	 * @param number
	 * @param weight
	 * @throws IllegalArgumentException if question <= 0
	 * @throws IllegalArgumentException if weight <= 0
	 */
	public Question(int number, double weight) {
		ArgumentChecks.isTrue(number > 0, "The number must be greater than 0");
		ArgumentChecks.isTrue(weight > 0, "The weight must be greater than 0");
		this.questionNumber = number;
		this.weight = weight;
	}

	/**
	 * 
	 * @param answer
	 * @return
	 * @throws IllegalArgumentException
	 */
	public abstract double mark(String answer);

	public double getWeight() {
		return weight;
	}

	public int getNumber() {
		return this.questionNumber;
	}

	@Override
	public String toString() {
		return "Question [questionNumber=" + questionNumber + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(Question other) {
		return this.getNumber() - other.getNumber();
	}
}
