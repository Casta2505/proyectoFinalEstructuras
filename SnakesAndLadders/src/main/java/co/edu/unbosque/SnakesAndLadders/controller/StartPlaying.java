package co.edu.unbosque.SnakesAndLadders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class StartPlaying {
	
	@GetMapping("/StartPlaying")
	public String getPlay(Model model) {
		return "tablero";
	}

}
