package co.edu.unbosque.SnakesAndLadders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SelectPlayers {
	
	@GetMapping("/TwoPlayers")
	public String getTwoPlayers(Model model){
		return "twoPlayers";
	}
	
	@GetMapping("/TreePlayers")
	public String getTreePlayers(Model model){
		return "treePlayers";
	}
	
	@GetMapping("/FourPlayers")
	public String getFourPlayers(Model model){
		return "fourPlayers";
	}

}
