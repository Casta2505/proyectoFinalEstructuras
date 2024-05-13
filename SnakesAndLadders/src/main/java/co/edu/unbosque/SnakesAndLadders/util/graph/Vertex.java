package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.io.Serializable;
import java.util.List;

import co.edu.unbosque.SnakesAndLadders.model.Player;
import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;

public class Vertex implements Serializable {
	private static final long serialVersionUID = 43890622329103446L;
	private Integer position;
	private List<Player> jugadores;
	private MyLinkedList<Edge> adyacentEdges;

	public Vertex() {
		this.adyacentEdges = new MyLinkedList<Edge>();
	}

	public void addEdge(Edge e) {
		adyacentEdges.addLast(e);
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public List<Player> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Player> jugadores) {
		this.jugadores = jugadores;
	}

	public MyLinkedList<Edge> getAdyacentEdges() {
		return adyacentEdges;
	}

	public void setAdyacentEdges(MyLinkedList<Edge> adyacentEdges) {
		this.adyacentEdges = adyacentEdges;
	}

}
