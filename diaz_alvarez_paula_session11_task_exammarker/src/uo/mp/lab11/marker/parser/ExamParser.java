package uo.mp.lab11.marker.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import uo.mp.lab11.marker.model.StudentExam;
import uo.mp.util.check.ArgumentChecks;


public class ExamParser {
	/**
	 * 
	 * @param lines
	 * @return
	 * @throws IllegalArgumentException if lines is null
	 */
	public List<StudentExam> parse(List<String> lines) {
		ArgumentChecks.isTrue(lines != null, "Illegal null list");
		List<StudentExam> res = new LinkedList<>();
		for(String line: lines) {
			res.add( parseLine( line ) );
		}
		return res;
	}

	private StudentExam parseLine(String line) {
		String parts[] = line.split("\t");
		String studentCode = parts[0];
		List<String> res = new ArrayList<>();
		
		for(int i = 1; i < parts.length; i++) {
			res.add( parts[i] );
		}
		StudentExam se = new StudentExam( studentCode, res );

		return se;
	}

}
