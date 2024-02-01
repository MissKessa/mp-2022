package uo.mp.lab11.marker.model;

public class StudentMark {
	private String id;
	private Double mark;
	
	/** 
	 * @param id
	 * @param mark If is < 0, attribute mark is set to zero.  
	 * @throws IllegalArgumentException if 
	 * 			- id is null or blank
	 */
	public StudentMark(String id, double mark) {
		this.id = id;
		this.mark = mark;
	}

	public double getMark() {
		return this.mark;
	}

	public String getStudentId() {
		return this.id;
	}



}
