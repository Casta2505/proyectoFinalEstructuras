package co.edu.unbosque.SnakesAndLadders.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.SnakesAndLadders.model.Components;
import co.edu.unbosque.SnakesAndLadders.repository.GameRepository;

@Service
public class GameService {
	@Autowired
	private GameRepository gameRepo;
	int tamano;
	int tamanoA;
	int tamanoL;
	List<Components> escaleras;
	List<Components> serpientes;

	public GameService() {
		tamanoA =3;
		tamanoL = 3;
		this.tamano = tamanoA * tamanoL;
	}

	void randomizeSNL(int tamano, int numEscaleras, int numSerpientes) {
		addLadders(tamano, numEscaleras, 0);
		addSnakes(tamano, numSerpientes, 0);
	}

	// Recursividad---------------------------------
	private void addLadders(int tamano, int numEscaleras, int i) {
		if (i == numEscaleras) {
			return;
		}

		Random rand = new Random();

		int inicio = rand.nextInt(this.tamano - 1) + 1;
		int fin = rand.nextInt(this.tamano - inicio) + inicio + 1;
		escaleras.add(new Components(inicio, fin));

		i++;

		addLadders(tamano, numEscaleras, i);
		return;
	}

	private void addSnakes(int tamano, int numSerpientes, int i) {
		if (i == numSerpientes) {
			return;
		}

		Random rand = new Random();

		int inicio = rand.nextInt(this.tamano - 1) + 1;
		int fin = rand.nextInt(inicio - 1) + 1;
		serpientes.add(new Components(inicio, fin));

		i++;

		addSnakes(tamano, numSerpientes, i);
		return;
	}
//---------------------------------------------------

	void mostrar() {

		System.out.println("Escaleras:");
		printLadders(0);

		System.out.println("\nSerpientes:");
		printSnakes(0);
	}

	// Recursividad---------------------------------

	private void printLadders(int i) {
		if (i == escaleras.size()) {
			return;
		}

		Components escalera = escaleras.get(i);
		System.out.println(escalera);

		i++;
		printLadders(i);
		return;
	}

	private void printSnakes(int i) {
		if (i == serpientes.size()) {
			return;
		}

		Components serpiente = serpientes.get(i);
		System.out.println(serpiente);

		i++;
		printSnakes(i);
		return;
	}
	// ---------------------------------------------------
}
