package co.edu.unbosque.SnakesAndLadders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class Personalize {

	@GetMapping("/SelectPlayers")
	public String getPlayers(Model model) {
		return "personalize";
	}

	@GetMapping("/seleccion")
	public String mostrarSeleccion(Model model, HttpServletRequest request) {
		String tema = request.getParameter("tema");
		String dificultad = request.getParameter("dificultad");
		System.out.println(tema);
		System.out.println(dificultad);
		request.getSession().setAttribute("tema", tema);
		request.getSession().setAttribute("dificultad", dificultad);
		return "redirect:/redirigirJugadores";
	}

}
