package Homework3.DataModel;

public class LiberalArt extends Course { //교양과목
	
	@Override
	public int compareTo(Course course) {
		// TODO 자동 생성된 메소드 스텁
		//1. 기초과목, 교양과목, 전공과목 순으로 더크다.(전공과목이 제일크다)
		//2. 같은 종류의 과목이라면, 뒤의 숫자가 더 클수록 더크다.
		//3. 이러한 로직을 구현하려면 각 서브클래스별로 구현될 compareTo메소드는 조금씩 다르다.
		
		if(course instanceof Major || course.getCourseId().charAt(0) == 'C')
			return -1;
		else if(course instanceof LiberalArt || course.getCourseId().charAt(0) == 'L')
			return this.getCourseId().compareTo(course.getCourseId());
		else if(course instanceof Basic || course.getCourseId().charAt(0) == 'B')
			return 1;

		return 0;
	}

	@Override
	public String toString() {
		return this.getCourseId() + " " + this.getCourseName() + 
									" " + this.getCourseMaximum() + 
									" " + this.getCourseCurrent();
	}
	

}