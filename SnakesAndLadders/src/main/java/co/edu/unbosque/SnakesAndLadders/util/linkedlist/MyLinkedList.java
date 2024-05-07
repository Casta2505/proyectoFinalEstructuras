package co.edu.unbosque.SnakesAndLadders.util.linkedlist;

import java.io.Serializable;

public class MyLinkedList<E> implements Serializable {
	private static final long serialVersionUID = 7511964570804990954L;
	protected Node<E> first;
	String textList = "";
	int index = 0;

	public MyLinkedList() {

		this.first = null;

	}

	public Node<E> getFirst() {
		return first;
	}

	public void setFirts(Node<E> firts) {
		this.first = firts;
	}

	public boolean isEmpty() {

		return (this.first == null);
	}

	public void add(E info) {
		this.first = addRecursive(info, this.first);
	}

	private Node<E> addRecursive(E info, Node<E> current) {
		if (current == null) {
			return new Node<>(info);
		}
		current.setNext(addRecursive(info, current.getNext()));
		return current;
	}

	public void insert(E info, Node<E> previous) {

		if (previous != null) {
			Node<E> newNode = new Node<E>(info);
			newNode.setNext(previous.getNext());
			previous.setNext(newNode);
		}

	}

	public void addLast(E info) {

		Node<E> lastNode = getLastNode();

		if (lastNode != null) {
			insert(info, lastNode);
		} else {
			this.first = new Node<E>(info);
		}

	}

	public E extract() {
		E data = null;

		if (this.first != null) {
			data = this.first.getInfo();
			this.first = this.first.getNext();
		}

		return data;

	}

	public E extractByIndex(int index) {
		if (index < 0 || index >= size()) {
			return null;
		}
		return extractByIndex(index, this.first, 0);
	}

	private E extractByIndex(int index, Node<E> currentNode, int counter) {
		if (currentNode == null) {
			return null;
		}
		if (counter == index) {
			E data = currentNode.getInfo();
			if (counter == 0) {
				this.first = this.first.getNext();
			} else {
				Node<E> previous = getInt(index - 1);
				previous.setNext(currentNode.getNext());
			}
			return data;
		}
		return extractByIndex(index, currentNode.getNext(), counter + 1);
	}

	public void extractByIndex(Node<E> previous) {
		@SuppressWarnings("unused")
		E data = null;
		if (previous != null && previous.getNext() != null) {
			data = previous.getNext().getInfo();
			previous.setNext(previous.getNext().getNext());
		}

	}

	public int size() {
		return size(this.first);

	}

	public int size(Node<E> current) {
		if (current == null) {
			return 0;
		}
		return 1 + size(current.getNext());
	}

	public String print() {
		return this.toString();
	}

	public boolean update(int index, E newData) {
		if (newData == null || index < 0 || index >= size()) {
			return false;
		}
		return updateRecursivo(index, newData, this.first, 0);
	}

	private boolean updateRecursivo(int index, E newData, Node<E> currentNode, int counter) {
		if (currentNode == null) {
			return false;
		}
		if (counter == index) {
			currentNode.setInfo(newData);
			return true;
		}
		return updateRecursivo(index, newData, currentNode.getNext(), ++counter);
	}

	public Node<E> getInt(int index) {
		if (index < 0 || index >= size()) {
			return null;
		}
		return get(index, this.first, 0);
	}

	public E get(int index) {
		if (index < 0 || index >= size()) {
			return null;
		}
		return get(index, this.first, 0).getInfo();
	}

	private Node<E> get(int index, Node<E> currentNode, int counter) {
		if (currentNode == null) {
			return null;
		}
		if (counter == index) {
			return currentNode;
		}
		return get(index, currentNode.getNext(), counter + 1);
	}

	public Node<E> getLastNode() {
		return getLastNode(this.first);
	}

	private Node<E> getLastNode(Node<E> currentNode) {
		if (currentNode == null || currentNode.getNext() == null) {
			return currentNode;
		}
		return getLastNode(currentNode.getNext());
	}

	public int indexOfCasta(E info) {
		return indexOfCasta(info, this.first, 0);
	}

	private int indexOfCasta(E info, Node<E> currentNode, int index) {
		if (currentNode == null) {
			return -1;
		}
		if (currentNode.getInfo().equals(info)) {
			return index;
		}
		return indexOfCasta(info, currentNode.getNext(), ++index);
	}

	public String print(int position) {
		Node<E> current = getInt(position);

		if (isEmpty()) {
			return textList;
		}

		textList = current.getInfo().toString();

		return textList;
	}

	public String toString() {
		Node<E> current = getInt(index);

		if (current == null) {
			return textList;
		}

		textList += current.getInfo().toString();
		if (current.getNext() != null) {
			textList += "\n";
		}
		current = current.getNext();
		index++;
		toString();

		return textList;
	}
}
