package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.io.Serializable;

public class Edge2 implements Serializable {
	private static final long serialVersionUID = -1404521109697682021L;
	private Vertex2 source;
	private Vertex2 destination;
	private double value;

	public Edge2() {

	}

	public Edge2(Vertex2 source, Vertex2 destination, double value) {
		super();
		this.source = source;
		this.destination = destination;
		this.value = value;
	}

	public Vertex2 getSource() {
		return source;
	}

	public void setSource(Vertex2 source) {
		this.source = source;
	}

	public Vertex2 getDestination() {
		return destination;
	}

	public void setDestination(Vertex2 destination) {
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
