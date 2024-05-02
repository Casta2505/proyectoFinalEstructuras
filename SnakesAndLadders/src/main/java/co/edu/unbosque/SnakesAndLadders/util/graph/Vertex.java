package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Vertex {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer position;
	private List<String> jugadores;
	@OneToMany(mappedBy = "camino", cascade = CascadeType.ALL)
	private List<Edge> caminosAdyacentes;
	@ManyToOne
	@JoinColumn(name = "graph_id")
	private Graph graph;

	public Vertex() {

	}

	public Vertex(Integer position) {
		this.position = position;
		caminosAdyacentes = new ArrayList<Edge>();
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public List<String> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<String> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Edge> getCaminosAdyacentes() {
		return caminosAdyacentes;
	}

	public void setCaminosAdyacentes(List<Edge> caminosAdyacentes) {
		this.caminosAdyacentes = caminosAdyacentes;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public void addEdge(Edge e) {
		caminosAdyacentes.add(e);
	}

	@Override
	public String toString() {
		return "\nVertex " + id + "\n" + caminosAdyacentes;
	}

}
