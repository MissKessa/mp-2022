package uo.mp.lab11.marker.simulator;

import java.util.List;

import uo.mp.lab11.marker.model.StudentExam;
import uo.mp.lab11.marker.model.StudentMark;
import uo.mp.lab11.marker.model.question.Question;
import uo.mp.lab11.marker.service.ExamMarker;
import uo.mp.lab11.marker.service.exception.ExamMarkerException;
import uo.mp.util.log.Logger;

public class Simulator {

	private static final String EXAM_MODEL_FILE = "questions.txt";
	private static final String ANSWERS_FILE = "answers.gz";
	private static final String RESULTS_FILE = "marks.txt";

	public void start() {
		try {
			simulateScenario();
		} catch (RuntimeException e) {
			handleSystemError(e.getMessage());
		} catch (ExamMarkerException e) {
			handleUserException(e.getMessage());
		}
	}

	private void handleSystemError(String message) {
		System.out.println("ERROR IRRECUPERABLE:" + message);
		Logger.log(message);

	}

	private void handleUserException(String message) {
		System.out.println("ERROR:" + message + " Ejecute de nuevo");

	}

	/**
	 * There is no user interface for this small program. This method simulates an
	 * example scenario of use.
	 * 
	 * @throws ExamMarkerException
	 */
	private void simulateScenario() throws ExamMarkerException {
		ExamMarker ex = new ExamMarker();
		ex.loadQuestions(EXAM_MODEL_FILE);
		showQuestions(ex.getQuestions());
		ex.loadAnswers(ANSWERS_FILE);
		showExams(ex.getAnswers());

		ex.mark();

		showMarks(ex.getMarksByMark(), " by ascending mark");
		showMarks(ex.getMarksByStudent(), " by ascending student id");
		ex.saveResults(RESULTS_FILE);

		showMark(ex, "UO00000");
	}

	private void showMark(ExamMarker ex, String id) throws ExamMarkerException {
		// si pide que si la excepción, se pase de ella y siga todo, ponemos el
		// try-catch aquí +log
		double mark = ex.findMark(id);
		System.out.println("La nota del alumno " + id + " es: " + mark);

	}

	private void showQuestions(List<Question> questions) {
		System.out.println("---------------------------------------------");
		System.out.println("List of questions");
		for (Question q : questions) {
			System.out.println(q.toString());
		}
	}

	private void showExams(List<StudentExam> exams) {
		System.out.println("---------------------------------------------");
		System.out.println("List of exams");
		for (StudentExam se : exams) {
			System.out.println(se.toString());
		}
	}

	private void showMarks(List<StudentMark> marks, String string) {
		System.out.println("---------------------------------------------");
		System.out.println("List of marks" + string);
		for (StudentMark mark : marks) {
			System.out.println(mark.toString());
		}
	}

}
