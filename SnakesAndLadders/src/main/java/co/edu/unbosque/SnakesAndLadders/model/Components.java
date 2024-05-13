package co.edu.unbosque.SnakesAndLadders.model;

import java.io.Serializable;

public class Components implements Serializable{
	private static final long serialVersionUID = 2228366698129865215L;
	int inicio;
	int fin;

	public Components(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
	}

	public Components() {
		// TODO Auto-generated constructor stub
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "De " + inicio + " a " + fin;
	}
}
