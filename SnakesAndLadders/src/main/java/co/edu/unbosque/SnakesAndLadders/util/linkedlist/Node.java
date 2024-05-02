package co.edu.unbosque.SnakesAndLadders.util.linkedlist;

public class Node<E> {
	private E info;
	private Node<E> next;

	public Node() {

	}

	public Node(E info, Node<E> next) {
		super();
		this.info = info;
		this.next = next;
	}

	public E getInfo() {
		return info;
	}

	public void setInfo(E info) {
		this.info = info;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	// truco - constructor using fields only info.
	public Node(E info) {
		this(info, null);
	}

	@Override
	public String toString() {
		if (info != null) {
			return info.toString();
		} else {
			return null;
		}
	}

}
