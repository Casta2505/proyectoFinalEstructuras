package co.edu.unbosque.SnakesAndLadders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PruebaController {
	
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

	@GetMapping("/tablero")
	public String generar(Model model) {
		return "tablero";
	}

}
