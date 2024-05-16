package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.io.Serializable;
import java.util.ArrayList;



public class Graph implements Serializable {
	private static final long serialVersionUID = -3313277376541604505L;
	private ArrayList<Vertex> listOfNodes;

	public Graph() {
		listOfNodes = new ArrayList<Vertex>();

	}

	public void addVertex(Vertex v) {
		listOfNodes.add(v);
	}

	public ArrayList<Vertex> getListOfNodes() {
		return listOfNodes;
	}

	public void setListOfNodes(ArrayList<Vertex> listOfNodes) {
		this.listOfNodes = listOfNodes;
	}

}