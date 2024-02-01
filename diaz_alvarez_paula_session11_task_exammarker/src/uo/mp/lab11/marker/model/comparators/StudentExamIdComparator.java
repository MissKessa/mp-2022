package uo.mp.lab11.marker.model.comparators;

import java.util.Comparator;

import uo.mp.lab11.marker.model.StudentExam;

public class StudentExamIdComparator implements Comparator<StudentExam> {
	@Override
	public int compare(StudentExam exam1, StudentExam exam2) {
		return exam1.getId().compareTo(exam2.getId());
	}
}
