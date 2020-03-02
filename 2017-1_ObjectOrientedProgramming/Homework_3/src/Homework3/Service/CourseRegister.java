package Homework3.Service;

import Homework3.DataModel.Course;
import Homework3.DataModel.Student;
import Homework3.DataStructure.Node;
import Homework3.DataStructure.Queue;
import Homework3.DataStructure.Tree;

public class CourseRegister {

	public void processRegister(Queue<Student> studentQueue, Tree<Course> courseTree) {
		// Tree내에서 수강과목을 검색하고 등록하는 로직이 여기있다.
		// 만약 수강과목의 정원이 다 찼다면, 수강등록을 하지 않는다.
		
		while (!(studentQueue.isEmpty())) {

			String requested = studentQueue.dequeue().getRequestCourse();

			Node<Course> applyCourse = courseTree.search(requested, courseTree.getRoot());

			if(applyCourse == null){
				continue;
			}
			
			// [Maximum > Current] 일때만 수강신청하도록 구현
			if (applyCourse.getData().getCourseMaximum() > applyCourse.getData().getCourseCurrent())
					applyCourse.getData().courseRegister();
			else
				continue;
		}
	}
}
