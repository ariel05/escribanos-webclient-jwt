package ar.com.example.escribanos.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.example.escribanos.webclient.model.EscribanoDTO;
import ar.com.example.escribanos.webclient.service.EscribanoService;

@Controller
public class EscribanosController {
	
	@Autowired
	private EscribanoService escribanoService;
	
	@GetMapping(value = {"/", "/index", "/form"})
	public String index(Model model) {
		
		model.addAttribute("titulo", "Nómina de Escribanos");
		return "form";
	}
	
	@GetMapping("/escribano")
	public String getEscribano(@RequestParam String cuit, Model model) {
		
		EscribanoDTO escribano = escribanoService.obtenerDatosEscribanoPorCuit(cuit);
		
		model.addAttribute("escribano", escribano);
		model.addAttribute("titulo", "Nómina de Escribanos");
		
		return "form";
	}
}
