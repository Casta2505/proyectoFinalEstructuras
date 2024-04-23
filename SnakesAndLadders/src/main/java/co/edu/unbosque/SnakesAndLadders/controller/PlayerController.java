package co.edu.unbosque.SnakesAndLadders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.SnakesAndLadders.model.Player;
import co.edu.unbosque.SnakesAndLadders.repository.PlayerRepository;

@Controller
@RequestMapping
public class PlayerController {
	@Autowired
	private PlayerRepository playerRep;
	
	@PostMapping("/createPlayer")
	public void createPlayer(@RequestParam("name") String name, @RequestParam("piece") String piece, @RequestParam("boardPosition")int boardPosition, @RequestParam("turn")boolean turn) {
		Player player = new Player();
		player.setName(name);
		player.setPiece(piece);
		player.setBoardPosition(boardPosition);
		player.setTurn(turn);
		playerRep.save(player);
	}
}
