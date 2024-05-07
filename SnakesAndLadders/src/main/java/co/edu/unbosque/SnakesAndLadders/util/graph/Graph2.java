package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.io.Serializable;

import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;

public class Graph2 implements Serializable {
	private static final long serialVersionUID = -3313277376541604505L;
	private MyLinkedList<Vertex2> listOfNodes;

	public Graph2() {
		listOfNodes = new MyLinkedList<Vertex2>();
	}

	public void addVertex(Vertex2 v) {
		listOfNodes.addLast(v);
	}

	public MyLinkedList<Vertex2> getListOfNodes() {
		return listOfNodes;
	}

	public void setListOfNodes(MyLinkedList<Vertex2> listOfNodes) {
		this.listOfNodes = listOfNodes;
	}

	@Override
	public String toString() {
		return "lista de nodos en el grafo: " + listOfNodes + " \n";
	}

}