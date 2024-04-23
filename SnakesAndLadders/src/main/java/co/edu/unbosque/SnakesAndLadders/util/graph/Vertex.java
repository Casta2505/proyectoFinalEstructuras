package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.io.Serializable;

import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;

public class Vertex<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 525360871608085167L;
	private E info;
	private MyLinkedList<Edge> caminosAdyacentes;

	public Vertex() {
		
	}

	public Vertex(E info) {
		this.info = info;
		caminosAdyacentes = new MyLinkedList<>();
	}

	public void addEdge(Edge e) {
		caminosAdyacentes.addLast(e);
	}

	public E getInfo() {
		return info;
	}

	public void setInfo(E info) {
		this.info = info;
	}

	public MyLinkedList<Edge> getCaminosAdyacentes() {
		return caminosAdyacentes;
	}

	public void setCaminosAdyacentes(MyLinkedList<Edge> caminosAdyacentes) {
		this.caminosAdyacentes = caminosAdyacentes;
	}

	@Override
	public String toString() {
		return "\nVertex " + info + "\n" + caminosAdyacentes;
	}

}
