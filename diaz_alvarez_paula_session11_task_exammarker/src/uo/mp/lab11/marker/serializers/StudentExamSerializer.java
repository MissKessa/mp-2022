package uo.mp.lab11.marker.serializers;

import java.util.ArrayList;
import java.util.List;

import uo.mp.lab11.marker.model.StudentExam;

public class StudentExamSerializer {
	public List<String> serialize(List<StudentExam> exams) {
		final List<String> result = new ArrayList<String>(exams.size());
		for (StudentExam exam : exams) {
			result.add(exam.serialize());
		}
		return result;
	}
}
