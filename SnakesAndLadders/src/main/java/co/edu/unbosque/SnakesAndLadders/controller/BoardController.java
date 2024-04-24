package co.edu.unbosque.SnakesAndLadders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.SnakesAndLadders.model.Board;
import co.edu.unbosque.SnakesAndLadders.repository.BoardRepository;
import co.edu.unbosque.SnakesAndLadders.util.graph.Edge;
import co.edu.unbosque.SnakesAndLadders.util.graph.Graph;
import co.edu.unbosque.SnakesAndLadders.util.graph.Vertex;

@Controller
@RequestMapping
public class BoardController {
	@Autowired
	private BoardRepository boardRep;

	@PostMapping("/addBoard")
	public void generateBoard(@RequestParam("heightBoard") int height, @RequestParam("widthBoard") int width,
			int dice) {
		Board board = new Board();
		int total = height * width;
		Graph graph = new Graph();
		int possible;
		if (dice == 1) {
			possible = 6;
		} else if (dice == 2) {
			possible = 12;
		} else {
			possible = 18;
		}
		for (int i = 0; i < total; i++) {
			@SuppressWarnings("unchecked")
			Vertex<Integer> aux = (Vertex<Integer>) graph.getListaNodos().get(i).getInfo();
			for (int j = 1; j <= possible && i + j <= total; j++) {
				aux.addEdge(new Edge(aux, graph.getListaNodos().get(i + j).getInfo(), 1));
			}
			possible = Math.min(possible, total - i - 1);
		}
		board.setGraph(graph);
		board.setHeight(height);
		board.setWidth(width);
		boardRep.save(board);
	}
}
