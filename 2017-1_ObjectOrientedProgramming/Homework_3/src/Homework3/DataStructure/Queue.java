package Homework3.DataStructure;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Queue<T> {
	private ArrayList<T> queueList;

	public Queue() {
		queueList = new ArrayList<T>();
	}

	public void enqueue(T object) {
		queueList.add(object);
	}

	public T dequeue() throws EmptyStackException {

		return queueList.remove(0);
	}

	public boolean isEmpty() {
		return queueList.isEmpty();
	}

	public void print() {
		System.out.print(queueList);
	}
	
	public T get(int i) throws EmptyStackException {

		return queueList.get(i);
	}

}