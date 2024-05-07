package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.io.Serializable;

import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;

public class Vertex2 implements Serializable {
	private static final long serialVersionUID = 43890622329103446L;
	private Integer position;
	private MyLinkedList<String> jugadores;
	private MyLinkedList<Edge2> adyacentEdges;

	public Vertex2() {
		this.adyacentEdges = new MyLinkedList<Edge2>();
	}

	public void addEdge(Edge2 e) {
		adyacentEdges.addLast(e);
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public MyLinkedList<String> getJugadores() {
		return jugadores;
	}

	public void setJugadores(MyLinkedList<String> jugadores) {
		this.jugadores = jugadores;
	}

	public MyLinkedList<Edge2> getAdyacentEdges() {
		return adyacentEdges;
	}

	public void setAdyacentEdges(MyLinkedList<Edge2> adyacentEdges) {
		this.adyacentEdges = adyacentEdges;
	}

	@Override
	public String toString() {
		return "\nVertex [info=" + position + ", adyacentEdges=" + adyacentEdges + "]";
	}
}
