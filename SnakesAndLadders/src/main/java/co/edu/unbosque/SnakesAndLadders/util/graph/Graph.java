package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.io.Serializable;

import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;

public class Graph implements Serializable {
	private static final long serialVersionUID = -3313277376541604505L;
	private MyLinkedList<Vertex> listOfNodes;

	public Graph() {
		listOfNodes = new MyLinkedList<Vertex>();
	}

	public void addVertex(Vertex v) {
		listOfNodes.addLast(v);
	}

	public MyLinkedList<Vertex> getListOfNodes() {
		return listOfNodes;
	}

	public void setListOfNodes(MyLinkedList<Vertex> listOfNodes) {
		this.listOfNodes = listOfNodes;
	}

	@Override
	public String toString() {
		return "lista de nodos en el grafo: " + listOfNodes + " \n";
	}

}