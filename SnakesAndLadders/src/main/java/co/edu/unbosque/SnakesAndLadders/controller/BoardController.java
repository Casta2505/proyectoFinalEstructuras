package co.edu.unbosque.SnakesAndLadders.controller;

import java.awt.Component;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import co.edu.unbosque.SnakesAndLadders.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.SnakesAndLadders.model.Board;
import co.edu.unbosque.SnakesAndLadders.repository.BoardRepository;
import co.edu.unbosque.SnakesAndLadders.util.graph.Edge;
import co.edu.unbosque.SnakesAndLadders.util.graph.Graph;
import co.edu.unbosque.SnakesAndLadders.util.graph.Vertex;
import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;

@Controller
@RequestMapping
public class BoardController {
	@Autowired
	private BoardRepository boardRep;
	private Random random;

	// LOGICA CATRE HPTA PARA GENERAR GRAFO
	public void generateBoard(int height, int width, int dice, String difficulty) {
		Board board = new Board();
		int total = height * width;
		Graph graph = new Graph();
		int totalLaddersAndSnakes = 0;
		// GENERAR ESCALERAS Y SERPIENTES EN LISTAS
		if (difficulty.equals("Easy")) {
			totalLaddersAndSnakes = (int) (total * 0.02);
		} else if (difficulty.equals("Medium")) {
			totalLaddersAndSnakes = (int) (total * 0.05);
		} else if (difficulty.equals("Tricky")) {
			totalLaddersAndSnakes = (int) (total * 0.07);
		} else {
			totalLaddersAndSnakes = (int) (total * 0.1);
		}
		MyLinkedList<Components> snakes = new MyLinkedList<>();
		MyLinkedList<Components> ladders = new MyLinkedList<>();
		Random random = new Random();
		HashSet<Integer> usedPositions = new HashSet<>();
		for (int i = 0; i < totalLaddersAndSnakes; i++) {
			int start;
			int end;
			do {
				start = random.nextInt(total) + 1;
				do {
					end = random.nextInt(total) + 1;
				} while (end == start || usedPositions.contains(end) || end >= start || end <= 1);
			} while (usedPositions.contains(start));
			usedPositions.add(start);
			usedPositions.add(end);
			Components snake = new Components(start, end);
			snakes.add(snake);
		}
		for (int i = 0; i < totalLaddersAndSnakes; i++) {
			int start;
			int end;
			do {
				start = random.nextInt(total) + 1;
				do {
					end = random.nextInt(total) + 1;
				} while (end == start || usedPositions.contains(end) || end <= start || end >= total);
			} while (usedPositions.contains(start));
			usedPositions.add(start);
			usedPositions.add(end);
			Components ladder = new Components(start, end);
			ladders.add(ladder);
		}
		HashMap<Integer, Integer> snakeLadderMap = new HashMap<>();
		System.out.println("ladders");
		for (int i = 0; i < ladders.size(); i++) {
			System.out.println(ladders.get(i).getInfo().getInicio() + " ... " + ladders.get(i).getInfo().getFin());
			snakeLadderMap.put(ladders.get(i).getInfo().getInicio(), ladders.get(i).getInfo().getFin());
		}
		System.out.println("snakes");
		for (int i = 0; i < snakes.size(); i++) {
			System.out.println(snakes.get(i).getInfo().getInicio() + " ... " + snakes.get(i).getInfo().getFin());
			snakeLadderMap.put(snakes.get(i).getInfo().getInicio(), snakes.get(i).getInfo().getFin());
		}
		// -------------------------------------------
		// LOGICA AGREGAR ARISTAS, ESCALERAS Y SERPIENTES
		if (dice == 1) {
			dice = 6;
		} else if (dice == 2) {
			dice = 12;
		} else {
			dice = 18;
		}
		for (int i = 0; i < total; i++) {
			graph.addVertex(new Vertex<Integer>(i + 1));
		}
		for (int i = 0; i < total; i++) {
			Vertex<Integer> aux = (Vertex<Integer>) graph.getListaNodos().get(i).getInfo();
			int possible = Math.min(dice, total - i - 1);
			for (int j = 1; j <= possible; j++) {
				int destination = i + j + 1;
				if (destination <= total) {
					if (snakeLadderMap.containsKey(destination)) {
						destination = snakeLadderMap.get(destination);
					}
					if (destination - 1 < graph.getListaNodos().size()) {
						aux.addEdge(new Edge(aux, graph.getListaNodos().get(destination - 1).getInfo(), 1));
					}
				}
			}
		}
		// -------------------------------------------
		board.setGraph(graph);
		board.setHeight(height);
		board.setWidth(width);
//		boardRep.save(board);
		System.out.println(graph.toString());
	}

}
