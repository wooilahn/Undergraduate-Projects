package Homework3.DataModel;

public class Student {
	private int studentId;
	private String studentName;
	private String requestCourse;

	public Student(int id, String name, String course) {
		this.studentId = id;
		this.studentName = name;
		this.requestCourse = course;
	}
	
	public Student() {
	
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getRequestCourse() {
		return requestCourse;
	}

	public void setRequestCourse(String requestCourse) {
		this.requestCourse = requestCourse;
	}

}