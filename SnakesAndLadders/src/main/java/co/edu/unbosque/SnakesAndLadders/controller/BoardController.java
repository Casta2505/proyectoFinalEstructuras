package co.edu.unbosque.SnakesAndLadders.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import co.edu.unbosque.SnakesAndLadders.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.edu.unbosque.SnakesAndLadders.repository.BoardRepository;
import co.edu.unbosque.SnakesAndLadders.util.graph.Edge;
import co.edu.unbosque.SnakesAndLadders.util.graph.Graph;
import co.edu.unbosque.SnakesAndLadders.util.graph.Vertex;
import co.edu.unbosque.SnakesAndLadders.util.linkedlist.MyLinkedList;

@Controller
@RequestMapping
@SessionAttributes("game")
public class BoardController {
	@Autowired
	private BoardRepository boardRep;

	@PostMapping("/generateBoard2")
	public String generateBoard2(@ModelAttribute("game") Game game, @RequestParam("characters") List<String> characters,
			Model model) {
		Board board = game.getBoard();
		int height = game.getBoard().getHeight();
		int width = game.getBoard().getWidth();
		String difficulty = game.getDifficulty();
		int dice = game.getDiceNumber();
		int total = height * width;
		Graph graph = new Graph();
		graph.setListOfNodes(new MyLinkedList<Vertex>());
		int totalLaddersAndSnakes = 0;
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
		// -------------------------------------------
		for (int i = 0; i < game.getPlayerNum(); i++) {
			game.getPlayers().get(i).setPiece(characters.get(i));
		}
		graph.getListOfNodes().get(0).setJugadores(game.getPlayers());
		board.setGraphData(serializeGraph(graph));
		board.setHeight(height);
		board.setWidth(width);
		board.setLadders(ladders);
		board.setSnakes(snakes);
		generateBoardMatrix(game, model);
		model.addAttribute("diceNumber", dice / 6);
		return "tablero";

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
		v.setJugadores(new ArrayList<Player>());
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

	@PostMapping("/generateBoard")
	public String generateBoard(@ModelAttribute("game") Game game, @RequestParam("characters") List<String> characters,
			Model model) {
		Board board = game.getBoard();
		int height = game.getBoard().getHeight();
		int width = game.getBoard().getWidth();
		String difficulty = game.getDifficulty();
		int dice = 0;
		int total = height * width;
		Graph graph = new Graph();
		graph.setListOfNodes(new MyLinkedList<Vertex>());
		int totalLaddersAndSnakes = 0;
		if (difficulty.equals("Easy")) {
			totalLaddersAndSnakes = (int) (total * 0.02);
			dice = 3;
		} else if (difficulty.equals("Medium")) {
			totalLaddersAndSnakes = (int) (total * 0.05);
			dice = 2;
		} else if (difficulty.equals("Tricky")) {
			dice = 1;
			totalLaddersAndSnakes = (int) (total * 0.07);
		} else {
			dice = 1;
			totalLaddersAndSnakes = (int) (total * 0.1);
		}
		game.setDiceNumber(dice);
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
			System.out.println(ladders.get(i).getInicio() + " ... " + ladders.get(i).getFin());
			snakeLadderMap.put(ladders.get(i).getInicio(), ladders.get(i).getFin());
		}
		System.out.println("snakes");
		for (int i = 0; i < snakes.size(); i++) {
			System.out.println(snakes.get(i).getInicio() + " ... " + snakes.get(i).getFin());
			snakeLadderMap.put(snakes.get(i).getInicio(), snakes.get(i).getFin());
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
			Vertex v = new Vertex();
			v.setAdyacentEdges(new MyLinkedList<Edge>());
			v.setJugadores(new ArrayList<Player>());
			v.setPosition(i + 1);
			graph.addVertex(v);
		}
		for (int i = 0; i < total; i++) {
			Vertex aux = graph.getListOfNodes().get(i);
			int possible = Math.min(dice, total - i - 1);
			for (int j = 1; j <= possible; j++) {
				int destination = i + j + 1;
				if (destination <= total) {
					if (snakeLadderMap.containsKey(destination)) {
						destination = snakeLadderMap.get(destination);
					}
					if (destination - 1 < graph.getListOfNodes().size()) {
						aux.addEdge(new Edge(aux, graph.getListOfNodes().get(destination - 1), 1));
					}
				}
			}
		}
		// -------------------------------------------
		for (int i = 0; i < game.getPlayerNum(); i++) {
			game.getPlayers().get(i).setPiece(characters.get(i));
		}
		graph.getListOfNodes().get(0).setJugadores(game.getPlayers());
		board.setGraphData(serializeGraph(graph));
		game.setPlayerTurn(game.getPlayers().get(0));
		board.setHeight(height);
		board.setWidth(width);
		board.setLadders(ladders);
		board.setSnakes(snakes);
		generateBoardMatrix(game, model);
		model.addAttribute("diceNumber", dice / 6);
		return "tablero";
	}

	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("game") Game game, @RequestParam("resultDices") int resultDices,
			Model model) {
		// PRIMERO LO ELIMINO DE LA LISTA DEL VERTEX
		Graph g = deserializeGraph(game.getBoard().getGraphData());
		boolean playerFound = false;

		for (int i = 0; i < g.getListOfNodes().size(); i++) {
		    List<Player> list = new ArrayList<>(g.getListOfNodes().get(i).getJugadores()); // Crear una copia de la lista original
		    for (int j = 0; j < list.size(); j++) {
		        if (game.getPlayerTurn().getOrder() == list.get(j).getOrder()) {
		            list.remove(j);
		            playerFound = true;
		            break;
		        }
		    }
		    if (playerFound) {
		        g.getListOfNodes().get(i).setJugadores(list); // Actualizar la lista original con la lista modificada
		        break;
		    }
		}
		// LUEGO CONSULTO A QUE VERTEX TENGO QUE IR
		MyLinkedList<Edge> list = g.getListOfNodes().get(game.getPlayerTurn().getBoardPosition() - 1)
				.getAdyacentEdges();
		Vertex aux2 = list.get(resultDices - 1).getDestination();
		// BUSCO EL VERTEX Y LO PONGO EN LA LISTA Y ACTUALIZAR
		game.getPlayerTurn().setBoardPosition(aux2.getPosition());
		g.getListOfNodes().get(aux2.getPosition() - 1).getJugadores().add(game.getPlayerTurn());
		game.getBoard().setGraphData(serializeGraph(g));
		// ACTUALIZO EL TURNO AL NUEVO JUGADOR
		int aux;
		if(game.getPlayerTurn().getOrder()+1>game.getPlayers().size()) {
			aux = 0;
		}else {
			aux = game.getPlayerTurn().getOrder();
		}
		game.setPlayerTurn(game.getPlayers().get(aux));
		// ACTUALIZAMOS EL TABLERO
		generateBoardMatrix(game, model);
		model.addAttribute("diceNumber", game.getDiceNumber());
		return "tablero";
	}

	private void generateBoardMatrix(Game game, Model model) {
		int height = game.getBoard().getHeight();
		int width = game.getBoard().getWidth();
		Graph g = deserializeGraph(game.getBoard().getGraphData());
		Vertex[][] matriz = new Vertex[height][width];
		boolean izquierdaDerecha = true;
		int contador = 0;
		for (int i = height - 1; i >= 0; i--) {
			if (izquierdaDerecha) {
				for (int j = 0; j < width; j++) {
					matriz[i][j] = g.getListOfNodes().get(contador++);
				}
			} else {
				for (int j = width - 1; j >= 0; j--) {
					matriz[i][j] = g.getListOfNodes().get(contador++);
				}
			}
			izquierdaDerecha = !izquierdaDerecha;
		}
		model.addAttribute("matriz", matriz);
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
