package co.edu.unbosque.SnakesAndLadders.controller;

import java.util.Arrays;

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
import co.edu.unbosque.SnakesAndLadders.model.Player;
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
		game.setDifficulty(difficulty);
		return "personalize";
	}

	@PostMapping("/updateThemes")
	public String updateThemes(@ModelAttribute("game") Game game, @RequestParam("themes") String theme, Model model) {
		game.setTheme(theme);
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
			return "treePlayers";
		} else {
			game.setPlayerNum(4);
			return "fourPlayers";
		}
	}

	@GetMapping("/StartPlaying")
	public String getPlay(Model model, @ModelAttribute("game") Game game) {
		model.addAttribute("diceNumber", game.getDiceNumber());
		model.addAttribute("theme", game.getTheme());
		if (game.getTheme().equals("green")) {
			return "tableroV";
		} else {
			if (game.getTheme().equals("pink")) {
				return "tableroR";
			} else {
				if (game.getTheme().equals("gray")) {
					return "tableroG";
				}
			}
		}
		return null;
	}

	@GetMapping("/goBackMenu")
	public String goBack(Model model) {
		return "menu";
	}

	@GetMapping("/ResumeGame")
	public String resumeG(Model model) {
		return "ResumeGame";
	}

	@GetMapping("/ganador")
	public String goWinner(Model model) {
		return "ganador";
	}

	@PostMapping("/SelectTwoCharacters")
	public String getCharacters(@ModelAttribute("game") Game game, Model model,
			@RequestParam("player1Name") String player1Name, @RequestParam("player2Name") String player2Name) {
		Player player1 = new Player(player1Name, 1, true, game,1);
		Player player2 = new Player(player2Name, 1, false, game,2);
		game.setPlayers(Arrays.asList(player1, player2));
		model.addAttribute("players", 2);
		return "characters";
	}

	@PostMapping("/SelectThreeCharacters")
	public String getCharacters(@ModelAttribute("game") Game game, Model model,
			@RequestParam("player1Name") String player1Name, @RequestParam("player2Name") String player2Name,
			@RequestParam("player3Name") String player3Name) {
		Player player1 = new Player(player1Name, 1, true, game,1);
		Player player2 = new Player(player2Name, 1, false, game,2);
		Player player3 = new Player(player3Name, 1, false, game,3);
		game.setPlayers(Arrays.asList(player1, player2, player3));
		model.addAttribute("players", 3);
		return "characters";
	}

	@PostMapping("/SelectFourCharacters")
	public String getCharacters(@ModelAttribute("game") Game game, Model model,
			@RequestParam("player1Name") String player1Name, @RequestParam("player2Name") String player2Name,
			@RequestParam("player3Name") String player3Name, @RequestParam("player4Name") String player4Name) {
		Player player1 = new Player(player1Name, 1, true, game,1);
		Player player2 = new Player(player2Name, 1, false, game,2);
		Player player3 = new Player(player3Name, 1, false, game,3);
		Player player4 = new Player(player4Name, 1, false, game,4);
		game.setPlayers(Arrays.asList(player1, player2, player3, player4));
		model.addAttribute("players", 4);
		return "characters";
	}
}