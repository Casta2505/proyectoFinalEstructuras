package co.edu.unbosque.SnakesAndLadders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.edu.unbosque.SnakesAndLadders.model.Board;
import co.edu.unbosque.SnakesAndLadders.model.Game;
import co.edu.unbosque.SnakesAndLadders.repository.GameRepository;

@Controller
@RequestMapping
@SessionAttributes("game")
public class GameController {
	@Autowired
	private GameRepository gameRep;

	@GetMapping("/")
	public String getSucursales() {
		return "menu";
	}

	@ModelAttribute("game")
	public Game setUpUser() {
		Game game = new Game();
		Board board = new Board();
		game.setBoard(board);
		return game;
	}

	@GetMapping("/SelectPlayers")
	public String getPlayers(Model model) {
		return "personalize";
	}

	@PostMapping("/updateDifficulty")
	public String updateDifficulty(@ModelAttribute("game") Game game, @RequestParam("difficulty") String difficulty,
			Model model) {
		System.out.println(game.getDifficulty());
		game.setDifficulty(difficulty);
		System.out.println(game.getDifficulty());
		return "personalize";
	}

	@PostMapping("/updateThemes")
	public String updateThemes(@ModelAttribute("game") Game game, @RequestParam("themes") String theme, Model model) {
		System.out.println(game.getTheme());
		game.setTheme(theme);
		System.out.println(game.getTheme());
		return "personalize";
	}

	@PostMapping("/updatePlayers")
	public String updatePlayers(@ModelAttribute("game") Game game, @RequestParam("playerSelected") String player,
			@RequestParam("height") int height, @RequestParam("width") int width, Model model) {
		if (game.getTheme() == null || game.getDifficulty() == null) {
			model.addAttribute("mensaje", "Select theme and diffilculty before create players");
			return "personalize";
		}
		game.getBoard().setHeight(height);
		game.getBoard().setWidth(width);
		if (player.equals("two")) {
			game.setPlayerNum(2);
			return "twoPlayers";
		} else if (player.equals("three")) {
			game.setPlayerNum(3);
			return "threePlayers";
		} else {
			game.setPlayerNum(4);
			return "fourPlayers";
		}
	}

}
