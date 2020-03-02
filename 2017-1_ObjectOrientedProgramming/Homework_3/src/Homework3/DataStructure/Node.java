package Homework3.DataStructure;

public class Node<T extends Comparable<T>> {
	private Node<T> leftNode;
	private Node<T> rightNode;
	private T data;

	public Node(T nodeData) {
		setData(nodeData);
		setLeftNode(setRightNode(null));
	}

	public void insert(T insertValue) {
		
		if (insertValue.compareTo(getData()) < 0) { //지금 value보다 작으면 왼쪽
			if (getLeftNode() == null)
				setLeftNode(new Node<T>(insertValue));
			else
				getLeftNode().insert(insertValue);
		} 
		
		else if (insertValue.compareTo(getData()) > 0) { //지금 value보다 크면 오른쪽
			if (getRightNode() == null)
				setRightNode(new Node<T>(insertValue));
			else
				getRightNode().insert(insertValue);
		} 
		
		else { //같으면 왼쪽으로 구현
			if (getLeftNode() == null)
				setLeftNode(new Node<T>(insertValue));
			else
				getLeftNode().insert(insertValue);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node<T> leftNode) {
		this.leftNode = leftNode;
	}

	public Node<T> getRightNode() {
		return rightNode;
	}

	public Node<T> setRightNode(Node<T> rightNode) {
		this.rightNode = rightNode;
		return rightNode;
	}
}