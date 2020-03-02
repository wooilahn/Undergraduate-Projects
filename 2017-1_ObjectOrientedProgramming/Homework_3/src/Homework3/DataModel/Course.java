package Homework3.DataModel;

public abstract class Course implements Comparable<Course> {
	private String courseId = "";
	private String courseName = "";
	private int courseMaximum;
	private int courseCurrent;

	public Course(String courseId, String courseName, int maximum, int current) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseMaximum = maximum;
		this.courseCurrent = current;
	}

	public Course() {

	}

	public void courseRegister() {
		this.courseCurrent++;
	}

	@Override
	public String toString() {
		return this.getCourseId();
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseMaximum() {
		return courseMaximum;
	}

	public void setCourseMaximum(int courseMaximum) {
		this.courseMaximum = courseMaximum;
	}

	public int getCourseCurrent() {
		return courseCurrent;
	}

	public void setCourseCurrent(int courseCurrent) {
		this.courseCurrent = courseCurrent;
	}
	
}