package Homework3.DataStructure;

public class Tree<T extends Comparable<T>> {
	private Node<T> root;

	public Tree() {
		root = null;
	}

	public void insertNode(T insertValue) {
		if (root == null)
			root = new Node<T>(insertValue);
		else
			root.insert(insertValue);
	}

	public Queue<Node<T>> preorderTraversal() {
		Queue<Node<T>> resultQueue = new Queue<Node<T>>();
		preorderHelper(root, resultQueue);
		return resultQueue;
	}

	private void preorderHelper(Node<T> node, Queue<Node<T>> queue) {

		if (node == null) {
			return;
		}
		System.out.printf("%s\n", node.getData().toString());
		queue.enqueue(node);
		preorderHelper(node.getLeftNode(), queue);
		preorderHelper(node.getRightNode(), queue);
	}

	public Queue<Node<T>> levelorderTraversal() {

		Queue<Node<T>> resultQueue = new Queue<Node<T>>();
		levelorderHelper(root, resultQueue);

		return resultQueue;
	}

	private void levelorderHelper(Node<T> node, Queue<Node<T>> queue) {
		// Level Order Traversal을 수행하는 메소드(queue를 통해 구현할 수 있다.)

		if (node == null) {
			return;
		}

		Queue<Node<T>> tempQueue = new Queue<Node<T>>();

		queue.enqueue(node);

		while (!(queue.isEmpty())) {

			Node<T> temp = queue.dequeue();
			tempQueue.enqueue(temp);

			System.out.printf("%s\n", temp.getData().toString());

			if (temp.getLeftNode() != null)
				queue.enqueue(temp.getLeftNode());
			if (temp.getRightNode() != null)
				queue.enqueue(temp.getRightNode());
		}

		
		  while (!(tempQueue.isEmpty())) { 
			  queue.enqueue(tempQueue.dequeue());
		 }
		 
	}

	public Node<T> search(String courseId, Node<T> node) {

		Node<T> temp = node;

		if (temp == null)
			return null;

		char base = temp.getData().toString().charAt(0);
		char requested = courseId.charAt(0);

		// C : 전공 , L: 교양, B: 베이직
		
		if (base == requested) {
			if (courseId.compareTo(temp.getData().toString().substring(0, courseId.length())) > 0) {
				return search(courseId, node.getRightNode());
			} else if (courseId.compareTo(temp.getData().toString().substring(0, courseId.length())) < 0) {
				return search(courseId, node.getLeftNode());
			} else if (courseId.compareTo(temp.getData().toString().substring(0, courseId.length())) == 0) {
				return temp;
			}
		}

		else if (base == 'C') {
			return search(courseId, node.getLeftNode());
		}

		else if (base == 'L') {
			if (requested == 'C') {
				return search(courseId, node.getRightNode());
			} else if (requested == 'B') {
				return search(courseId, node.getLeftNode());
			}
		}

		else if (base == 'B') {
			return search(courseId, node.getRightNode());
		}

		return null;
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
}