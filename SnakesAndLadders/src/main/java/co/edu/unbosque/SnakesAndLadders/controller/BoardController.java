package co.edu.unbosque.SnakesAndLadders.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import co.edu.unbosque.SnakesAndLadders.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
//	@Autowired
//	private GraphRepository graphRep;
//	@Autowired
//	private VertexRepository vertexRep;
//	@Autowired
//	private EdgeRepository edgeRep;

	@GetMapping("eliana")
	public String eliana(Model model) {
		generateBoard(8, 8, 3, "Easy");
		return "personalize";
	}

	// LOGICA CATRE HPTA PARA GENERAR GRAFO
	public void generateBoard(int height, int width, int dice, String difficulty) {
		Board board = new Board();
		int total = height * width;
		Graph graph = new Graph();
		graph.setListOfNodes(new MyLinkedList<Vertex>());
//		graphRep.save(graph);
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

		agregarSerpientes(0, totalLaddersAndSnakes, total, usedPositions, snakes, random);
		agregarEscaleras(0, totalLaddersAndSnakes, total, usedPositions, ladders, random);

		// -------------------------------------------------

		HashMap<Integer, Integer> snakeLadderMap = new HashMap<>();
		System.out.println("ladders");
		verEscalerasYSerpientes(0, ladders, total, snakeLadderMap);

		System.out.println("snakes");
		verEscalerasYSerpientes(0, snakes, total, snakeLadderMap);

		// -------------------------------------------
		// LOGICA AGREGAR ARISTAS, ESCALERAS Y SERPIENTES
		if (dice == 1) {
			dice = 6;
		} else if (dice == 2) {
			dice = 12;
		} else {
			dice = 18;
		}

		agregarAristas(0, graph, total);
		MyLinkedList<Edge> list = new MyLinkedList<Edge>();
		logics(0, total, snakeLadderMap, graph, dice, list);
		// edgeRep.saveAll(list);

		// -------------------------------------------
//		graphRep.save(graph);
		board.setGraphData(serializeGraph(graph));
		board.setHeight(height);
		board.setWidth(width);
		boardRep.save(board);
//		System.out.println(graph.toString());
	}

	// Recursividad--------------------------------------------------------------------------------

	public void logics(int i, int total, HashMap<Integer, Integer> snakeLadderMap, Graph graph, int dice,
			MyLinkedList<Edge> list) {
		if (i == total) {
			return;
		}
		Vertex aux = graph.getListOfNodes().get(i);
		int possible = Math.min(dice, total - i - 1);
		logicsR(i, 0, total, snakeLadderMap, graph, dice, possible, aux, list);
		i++;
		logics(i, total, snakeLadderMap, graph, dice, list);
		return;
	}

	public void logicsR(int i, int j, int total, HashMap<Integer, Integer> snakeLadderMap, Graph graph, int dice,
			int possible, Vertex aux, MyLinkedList<Edge> list) {
		if (j > possible) {
			return;
		}
		int destination = i + j + 1;
		if (destination <= total) {
			if (snakeLadderMap.containsKey(destination)) {
				destination = snakeLadderMap.get(destination);
			}
			if (destination - 1 < graph.getListOfNodes().size()) {
				Edge edge = new Edge();
				edge.setSource(aux);
				edge.setDestination(graph.getListOfNodes().get(destination - 1));
				edge.setValue(1);
				list.add(edge);
				aux.addEdge(edge);
//				vertexRep.save(aux);
			}
		}

		j++;
		logicsR(i, j, total, snakeLadderMap, graph, dice, possible, aux, list);
		return;
	}

	public void verEscalerasYSerpientes(int i, MyLinkedList<Components> ladders, int total,
			HashMap<Integer, Integer> snakeLadderMap) {
		if (i == ladders.size()) {
			return;
		}

		System.out.println(ladders.get(i).getInicio() + " ... " + ladders.get(i).getFin());
		snakeLadderMap.put(ladders.get(i).getInicio(), ladders.get(i).getFin());

		verEscalerasYSerpientes(i + 1, ladders, total, snakeLadderMap);
		return;
	}

	public void agregarAristas(int i, Graph graph, int total) {
		if (i == total) {
			return;
		}
		Vertex v = new Vertex();
		v.setAdyacentEdges(new MyLinkedList<Edge>());
		v.setJugadores(new MyLinkedList<String>());
		v.setPosition(i + 1);
		graph.addVertex(v);
		i++;
		agregarAristas(i, graph, total);
		return;
	}

	public void agregarSerpientes(int i, int totalLaddersAndSnakes, int total, HashSet<Integer> usedPositions,
			MyLinkedList<Components> snakes, Random random) {
		if (i < totalLaddersAndSnakes) {
			int start = generateStart(total, usedPositions, random);
			int end = generateEndSnake(total, start, usedPositions, random);
			usedPositions.add(start);
			usedPositions.add(end);
			Components snake = new Components(start, end);
			snakes.add(snake);
			agregarSerpientes(i + 1, totalLaddersAndSnakes, total, usedPositions, snakes, random);
		}
	}

	public int generateStart(int total, HashSet<Integer> usedPositions, Random random) {
		int start = random.nextInt(total) + 1;
		if (usedPositions.contains(start)) {
			return generateStart(total, usedPositions, random);
		} else {
			return start;
		}
	}

	public int generateEndLadder(int total, int start, HashSet<Integer> usedPositions, Random random) {
		int end = random.nextInt(total) + 1;
		if (end == start || usedPositions.contains(end) || end <= start || end <= 1) {
			return generateEndLadder(total, start, usedPositions, random);
		} else {
			return end;
		}
	}

	public int generateEndSnake(int total, int start, HashSet<Integer> usedPositions, Random random) {
		int end = random.nextInt(total) + 1;
		if (end == start || usedPositions.contains(end) || end >= start || end <= 1) {
			return generateEndSnake(total, start, usedPositions, random);
		} else {
			return end;
		}
	}

	public void agregarEscaleras(int i, int totalLaddersAndSnakes, int total, HashSet<Integer> usedPositions,
			MyLinkedList<Components> ladders, Random random) {
		if (i < totalLaddersAndSnakes) {
			int start = generateStart(total, usedPositions, random);
			int end = generateEndLadder(total, start, usedPositions, random);
			usedPositions.add(start);
			usedPositions.add(end);
			Components ladder = new Components(start, end);
			ladders.add(ladder);
			agregarEscaleras(i + 1, totalLaddersAndSnakes, total, usedPositions, ladders, random);
		}
	}

	@GetMapping("/crearMatriz")
	public String crearMatriz(Model model) {
		int filas = 10;
		int columnas = 10;
		int[][] matriz = new int[filas][columnas];
		boolean izquierdaDerecha = true;
		int contador = 1;
		for (int i = filas - 1; i >= 0; i--) {
			if (izquierdaDerecha) {
				for (int j = 0; j < columnas; j++) {
					matriz[i][j] = contador++;
				}
			} else {
				for (int j = columnas - 1; j >= 0; j--) {
					matriz[i][j] = contador++;
				}
			}
			izquierdaDerecha = !izquierdaDerecha;
		}
		model.addAttribute("matriz", matriz);
		return "tablero";
	}

	public byte[] serializeGraph(Graph graph) {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(graph);
			oos.flush();
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Graph deserializeGraph(byte[] graph) {
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(graph);
			ois = new ObjectInputStream(bis);
			return (Graph) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
