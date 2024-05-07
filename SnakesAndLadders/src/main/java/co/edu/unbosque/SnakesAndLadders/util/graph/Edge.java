package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.io.Serializable;

public class Edge implements Serializable {
	private static final long serialVersionUID = -1404521109697682021L;
	private Vertex source;
	private Vertex destination;
	private double value;

	public Edge() {

	}

	public Edge(Vertex source, Vertex destination, double value) {
		super();
		this.source = source;
		this.destination = destination;
		this.value = value;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getDestination() {
		return destination;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "\n\tEdge [source=" + source + ", destination=" + destination + ", value=" + value + "]";
	}

}
