package co.edu.unbosque.SnakesAndLadders.util.graph;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Edge {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "origen_id")
	private Vertex origen;
	@ManyToOne
	@JoinColumn(name = "destino_id")
	private Vertex destino;
	@ManyToOne
	@JoinColumn(name = "camino_id")
	private Vertex camino;

	private double valor;

	public Edge() {

	}

	public Vertex getCamino() {
		return camino;
	}

	public void setCamino(Vertex camino) {
		this.camino = camino;
	}

	public Vertex getOrigen() {
		return origen;
	}

	public void setOrigen(Vertex origen) {
		this.origen = origen;
	}

	public Vertex getDestino() {
		return destino;
	}

	public void setDestino(Vertex destino) {
		this.destino = destino;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "desde: " + origen + ", hasta: " + destino + ", Peso=" + valor + "\n";
	}
}
