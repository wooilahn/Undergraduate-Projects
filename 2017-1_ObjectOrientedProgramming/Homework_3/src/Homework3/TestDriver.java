package Homework3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Homework3.DataModel.Basic;
import Homework3.DataModel.Course;
import Homework3.DataModel.LiberalArt;
import Homework3.DataModel.Major;
import Homework3.DataModel.Student;
import Homework3.DataStructure.Node;
import Homework3.DataStructure.Queue;
import Homework3.DataStructure.Tree;
import Homework3.Service.CourseRegister;

public class TestDriver {
	public static void main(String[] args) {

		//***Course***
		System.out.println("\n***Course***");

		File CourseFile = new File("course.txt");
		BufferedReader CourseReader = null;

		try {
			CourseReader = new BufferedReader(new FileReader(CourseFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String CourseLine;
		String[] CourseValue = new String[4];
		Tree<Course> CourseTree = new Tree<Course>();
		Queue<Node<Course>> CourseQueue = new Queue<Node<Course>>();   //Course Preorder Queue
		Queue<Node<Course>> CourseQueue2 = new Queue<Node<Course>>();  //Course Levelorder Queue
		Course course = null;

		try {
			while ((CourseLine = CourseReader.readLine()) != null) {

				CourseValue = CourseLine.split(" ");

				if (CourseLine.charAt(0) == 'C') {
					course = new Major();
				} else if (CourseLine.charAt(0) == 'L') {
					course = new LiberalArt();

				} else if (CourseLine.charAt(0) == 'B') {
					course = new Basic();
				}

				course.setCourseId(CourseValue[0]);
				course.setCourseName(CourseValue[1]);
				course.setCourseMaximum(Integer.parseInt(CourseValue[2]));
				course.setCourseCurrent(Integer.parseInt(CourseValue[3]));

				CourseTree.insertNode(course);
			}

			CourseReader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		//////////////////////////////////////////////////////////////////////////////////

		//***Student***

		File StudentFile = new File("student.txt");
		BufferedReader StudentReader = null;

		try {
			StudentReader = new BufferedReader(new FileReader(StudentFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String StudentLine;
		String[] StudentValue = new String[4];
		Queue<Student> StudentQueue = new Queue<Student>();
		

		try {
			while ((StudentLine = StudentReader.readLine()) != null) {

				Student student = new Student();
				
				StudentValue = StudentLine.split(" ");

				student.setStudentId(Integer.parseInt(StudentValue[0]));
				student.setStudentName(StudentValue[1]);
				student.setRequestCourse(StudentValue[2]);

				StudentQueue.enqueue(student);
			
			}
			
			StudentReader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		CourseRegister courseRegister = new CourseRegister();
		courseRegister.processRegister(StudentQueue, CourseTree);

		System.out.println("\n---Postorder---\n"); //Course Preorder 출력
		CourseQueue = CourseTree.preorderTraversal();

		try {
			BufferedWriter CourseWriter = new BufferedWriter(new FileWriter("CourseOutput(Preorder).txt"));

			while (!CourseQueue.isEmpty()) {
				CourseWriter.write(CourseQueue.dequeue().getData().toString());
				CourseWriter.newLine();
			}

			CourseWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\n---Levelorder---\n"); //Course Levelorder 출력
		CourseQueue2 = CourseTree.levelorderTraversal();

		try {
			BufferedWriter CourseWriter2 = new BufferedWriter(new FileWriter("CourseOutput(Levelorder).txt"));

			while (!CourseQueue2.isEmpty()) {
				CourseWriter2.write(CourseQueue2.dequeue().getData().toString());
				CourseWriter2.newLine();
			}

			CourseWriter2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//////////////////////////////////////////////////////////////////////////////////

		//***String***
		System.out.println("\n***StringInput***");

		File StringFile = new File("StringInput.txt");
		BufferedReader StringReader = null;

		try {
			StringReader = new BufferedReader(new FileReader(StringFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String StringLine;
		Tree<String> StringTree = new Tree<String>();
		Queue<Node<String>> StringQueue = new Queue<Node<String>>();   //String Preorder Queue
		Queue<Node<String>> StringQueue2 = new Queue<Node<String>>();  //String Preorder Queue

		try {
			while ((StringLine = StringReader.readLine()) != null) {
				StringTree.insertNode(StringLine);
			}
			System.out.println("\n---Postorder---\n"); //String Preorder 출력
			StringQueue = StringTree.preorderTraversal();

			System.out.println("\n---Levelorder---\n"); //String Levelorder 출력
			StringQueue2 = StringTree.levelorderTraversal();

			StringReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedWriter StringWriter = new BufferedWriter(new FileWriter("StringOutput(Preorder).txt"));

			while (!StringQueue.isEmpty()) {
				StringWriter.write(StringQueue.dequeue().getData().toString());
				StringWriter.newLine();
			}

			StringWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedWriter StringWriter2 = new BufferedWriter(new FileWriter("StringOutput(Levelorder).txt"));

			while (!StringQueue2.isEmpty()) {
				StringWriter2.write(StringQueue2.dequeue().getData().toString());
				StringWriter2.newLine();
			}

			StringWriter2.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
