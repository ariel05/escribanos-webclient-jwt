package ar.com.example.escribanos.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@GetMapping(value = { "/", "/index", "/nomina" })
	public String index(Model model) {

		model.addAttribute("titulo", "Nómina de Escribanos");
		return "form";
	}

	@GetMapping("/nomina/buscar")
	public String getEscribano(@RequestParam String cuit, Model model) {
		try {
			ResponseEntity<EscribanoDTO> escribano = escribanoService.obtenerDatosEscribanoPorCuit(cuit);
			if(escribano.getBody() != null) {
				model.addAttribute("escribano", escribano.getBody());
				model.addAttribute("imgAvatar", escribanoService.getImgMock(cuit));
			}
			else {
				model.addAttribute("sinResultado", true);
				model.addAttribute("sinResultadoMensaje", "Escribano no encontrado para el CUIT proporcionado.");
			}
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
		}

		model.addAttribute("resultado", true);
		model.addAttribute("titulo", "Nómina de Escribanos");
		return "form";
	}
}
