package co.edu.unbosque.SnakesAndLadders.model;

import java.io.Serializable;

import co.edu.unbosque.SnakesAndLadders.util.graph.Graph;
import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;

public class Board implements Serializable {
	private static final long serialVersionUID = -4692012718865825199L;
	private Graph graphData;
	private MyLinkedList<Components> snakes;
	private MyLinkedList<Components> ladders;
	private int height;
	private int width;

	public MyLinkedList<Components> getSnakes() {
		return snakes;
	}

	public void setSnakes(MyLinkedList<Components> snakes) {
		this.snakes = snakes;
	}

	public MyLinkedList<Components> getLadders() {
		return ladders;
	}

	public void setLadders(MyLinkedList<Components> ladders) {
		this.ladders = ladders;
	}

	public Graph getGraphData() {
		return graphData;
	}

	public void setGraphData(Graph graphData) {
		this.graphData = graphData;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
