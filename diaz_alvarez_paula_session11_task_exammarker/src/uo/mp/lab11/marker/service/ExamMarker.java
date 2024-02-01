package uo.mp.lab11.marker.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uo.mp.lab11.marker.model.StudentExam;
import uo.mp.lab11.marker.model.StudentMark;
import uo.mp.lab11.marker.model.comparators.StudentExamIdComparator;
import uo.mp.lab11.marker.model.question.Question;
import uo.mp.lab11.marker.parser.ExamParser;
import uo.mp.lab11.marker.parser.QuestionParser;
import uo.mp.lab11.marker.service.exception.ExamMarkerException;
import uo.mp.util.check.ArgumentChecks;

public class ExamMarker {
	/*
	 * file questions.txt is read and parsed into questions list
	 */
	private List<Question> questions = new ArrayList<>();
	/*
	 * file answers.gz is read and parsed into answers list
	 */
	private List<StudentExam> answers = new ArrayList<>();
	/*
	 * Student marks are computed and stored into marks list
	 */
	private List<StudentMark> marks = new ArrayList<>();

	/**
	 * 
	 * @param examModelFile
	 * @throws IllegalArgumentException if examModelFile is null or blank
	 * @throws FileNotFoundException    if examModelFile cannot be found
	 */
	public void loadQuestions(String questionsFilename) throws ExamMarkerException {
		List<String> lines = readQuestionLines();
		List<Question> questions = new QuestionParser().parse(lines);

		Collections.sort(questions);
		Collections.reverse(questions);
		addQuestions(questions);
	}

	/*
	 * TODO : FAKE IMPLEMENTATION
	 */
	private List<String> readQuestionLines() {
		List<String> lines = new ArrayList<String>();
		lines.add("choice	1.0	a");
		lines.add("choice	1.0	b");
		lines.add("gap	0.5	stuff");
		lines.add("gap	0.5	computer");
		lines.add("value	1.5	12.5");
		lines.add("value	1.5	100.0");
		lines.add("gap	1.0	polymorphism");
		lines.add("value	1.0	256.0");
		lines.add("choice	0.5	c");
		lines.add("gap	1.5	abstract");
		return lines;
	}

	/*
	 * TODO : FAKE IMPLEMENTATION
	 */
	private void addQuestions(List<Question> quests) {
		this.questions = quests;

	}

	/**
	 * 
	 * @param answersFilename
	 * @throws FileNotFoundException    if answersFile cannot be found
	 * @throws IllegalArgumentException if answersFilename is null or blank
	 * @throws ExamMarkerException      when there are more than one exam for the
	 *                                  same student
	 */
	public void loadAnswers(String answersFilename) {

		List<String> lines = readAnswerLines();
		List<StudentExam> exams = new ExamParser().parse(lines);
		Collections.sort(exams, new StudentExamIdComparator());
		addExams(exams);
	}

	/*
	 * FAKE IMPLEMENTATION
	 */
	private List<String> readAnswerLines() {
		List<String> res = new ArrayList<>();
		res.add("20209	c	b	cosa	computer	10.5	100	inheritance	256.0	a	abstract");
		res.add("20210	a	b	stuff	processor	11.5	120	inheritance	128.0	a	abstract");
		res.add("20211	b	a	other	process	12.5	110	inheritance	256.0	a	blank");
		res.add("20215	a	b	stuff	thread	13.5	80	inheritance	0.0	a	n/a");
		res.add("20214	b	c	thing	computer	14.5	0	inheritance	512.0	a	abstract");
		res.add("20213	c	d	cosa	computer	12.5	10	inheritance	1021.0	a	abstract");

		return res;
	}

	/*
	 * TODO: FAKE IMPLEMENTATION
	 */
	private void addExams(List<StudentExam> exams) {
		this.answers = exams;
	}

	/**
	 * 
	 * @return the list of marks ordered by student id in ascending order
	 */
	public List<StudentMark> getMarksByStudent() {

		// FAKE IMPLEMENTATION

		return this.marks;
	}

	/**
	 * 
	 * @return the list of marks ordered by grade in ascending order For the same
	 *         grade, by ascending student id
	 */
	public List<StudentMark> getMarksByMark() {

		// FAKE IMPLEMENTATION

		return this.marks;

	}

	/**
	 * calculates the mark for each exam. Generates StudentMark instances
	 */
	public void mark() {
		// TODO FAKE IMPLEMENTATION
		marks.add(new StudentMark("20209", 5.2));
		marks.add(new StudentMark("20210", 3.9));
		marks.add(new StudentMark("20211", 2.0));
		marks.add(new StudentMark("20215", 2.4));
		marks.add(new StudentMark("20214", 1.5));
		marks.add(new StudentMark("20213", 3.0));
	}

	/**
	 * 
	 * @param resultsFilename
	 * @throws IllegalArgumentException if resultsFilename is null or blank
	 */
	public void saveResults(String resultsFilename) {
		// TODO
	}

	public List<StudentExam> getAnswers() {
		// TODO FAKE IMPLEMENTATION
		return answers;
	}

	public List<Question> getQuestions() {
		// TODO FAKE IMPLEMENTATION
		return questions;
	}

	public double findMark(String id) throws ExamMarkerException {
		ArgumentChecks.isTrue(id != null && !id.isBlank());
		for (StudentMark mark : this.marks) {
			if (id.equals(mark.getStudentId())) {
				return mark.getMark();
			}
		}
		throw new ExamMarkerException("El alumno " + id + " no está en la lista de notas");
	}

}
