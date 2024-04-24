package co.edu.unbosque.SnakesAndLadders.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.SnakesAndLadders.model.Game;
import co.edu.unbosque.SnakesAndLadders.repository.GameRepository;

@Controller
@RequestMapping
public class GameController {
	@Autowired
	private GameRepository gameRep;

	@GetMapping("/SelectPlayers")
	public String getPlayers(Model model) {
		Game game = new Game();
		game.setDifficulty("easy");
		model.addAttribute("currentGame", game);
		return "personalize";
	}

	@PostMapping("/updateGameDifficulty")
	public String updateGameDifficulty(@RequestParam("buttonId") String buttonId,
	        @ModelAttribute("currentGame") Game game, Model model) {
	    if (game.getDifficulty() != null) {
	        System.out.println("Dificultad anterior: " + game.getDifficulty());
	    }else {
	    	System.out.println("null");
	    }
	    game.setDifficulty(buttonId);
	    System.out.println("Nueva dificultad: " + game.getDifficulty());
	    model.addAttribute("currentGame",game);
	    return "personalize";
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

}
