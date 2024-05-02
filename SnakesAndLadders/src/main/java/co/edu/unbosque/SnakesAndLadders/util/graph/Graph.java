package co.edu.unbosque.SnakesAndLadders.util.graph;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Graph {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToMany(mappedBy = "graph", cascade = CascadeType.ALL)
	private List<Vertex> listaNodos;

	public Graph() {
		this.listaNodos = new ArrayList<Vertex>();
	}

	public List<Vertex> getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(List<Vertex> listaNodos) {
		this.listaNodos = listaNodos;
	}

	public void addVertex(Vertex v) {
		listaNodos.add(v);
	}

	@Override
	public String toString() {
		return "Lista de nodos en el grafo:" + listaNodos + "\n";
	}

}
