package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.SnakesAndLadders.model.Player;

public class Vertex implements Serializable {
	private static final long serialVersionUID = 43890622329103446L;
	private Integer position;
	private ArrayList<Player> jugadores;
	private String snakeOrLadder;
	private ArrayList<Edge> adyacentEdges;

	public Vertex() {
		this.adyacentEdges = new ArrayList<Edge>();
	}

	public String getSnakeOrLadder() {
		return snakeOrLadder;
	}

	public void setSnakeOrLadder(String snakeOrLadder) {
		this.snakeOrLadder = snakeOrLadder;
	}

	public void addEdge(Edge e) {
		adyacentEdges.add(e);
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

	public void setJugadores(ArrayList<Player> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Edge> getAdyacentEdges() {
		return adyacentEdges;
	}

	public void setAdyacentEdges(ArrayList<Edge> adyacentEdges) {
		this.adyacentEdges = adyacentEdges;
	}

}
