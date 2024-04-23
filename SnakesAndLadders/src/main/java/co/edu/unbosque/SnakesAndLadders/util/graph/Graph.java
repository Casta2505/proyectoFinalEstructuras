package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.io.Serializable;

import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;


public class Graph implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1597013700542252615L;
	private MyLinkedList<Vertex<?>> listaNodos;

	public Graph() {
		// TODO Auto-generated constructor stub
		listaNodos = new MyLinkedList<>();
	}

	public MyLinkedList<Vertex<?>> getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(MyLinkedList<Vertex<?>> listaNodos) {
		this.listaNodos = listaNodos;
	}

	public void addVertex(Vertex<?> v) {
		listaNodos.add(v);
	}

	@Override
	public String toString() {
		return "Lista de nodos en el grafo:" + listaNodos + "\n";
	}

}
