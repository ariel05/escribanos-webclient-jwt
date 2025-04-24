package ar.com.example.escribanos.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.example.escribanos.webclient.model.EscribanoDTO;
import ar.com.example.escribanos.webclient.service.EscribanoService;

@RestController
@RequestMapping("/api/v1")
public class EscribanoRestController {

	@Autowired
	private EscribanoService escribanoService;
	
	@GetMapping("/getEscribano")
	public ResponseEntity<EscribanoDTO> getEscribano(@RequestParam String cuit) {
		return escribanoService.obtenerDatosEscribanoPorCuit(cuit);
		
	}
}
