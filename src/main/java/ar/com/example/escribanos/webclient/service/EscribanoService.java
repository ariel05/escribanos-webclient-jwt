package ar.com.example.escribanos.webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ar.com.example.escribanos.webclient.model.EscribanoDTO;

@Service
public class EscribanoService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JwtTokenHolder tokenHolder;
	@Value("${url.servicio.escribanos}")
	private String url;
	@Value("${servicio.escribanos.method}")
	private String endpoint;
	
	private final String bearer = "Bearer ";
	
	public ResponseEntity<EscribanoDTO> obtenerDatosEscribanoPorCuit(String cuit) {
		if(!validarCuit(cuit))
			throw new IllegalArgumentException("Formato de CUIT inválido.");
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", bearer.concat(tokenHolder.getToken()));
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<EscribanoDTO> entity = new HttpEntity<>(headers);

	    try {
	        ResponseEntity<EscribanoDTO> response = restTemplate.exchange(
	            url.concat(endpoint).concat(cuit.replaceAll("-", "")),
	            HttpMethod.GET,
	            entity,
	            EscribanoDTO.class
	        );
	        return response;
	    } catch (HttpClientErrorException e) {
	        if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
	            throw new RuntimeException("No autorizado para acceder a la información del escribano.");
	        } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
	            throw new RuntimeException("Acceso prohibido al recurso del escribano.");
	        } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
	        	return new ResponseEntity<EscribanoDTO>(HttpStatus.NOT_FOUND);
	        } else {
	            throw new RuntimeException("Error al obtener los datos del escribano.");
	        }
	    } catch (Exception e) {
	    	String msj;
	    	if(e.getMessage().contains("Invalid token"))
	    		msj = "El token generado es inválido o ha expirado, por favor genere uno nuevo.";
	    	else
	    		msj = "Error inesperado al consultar escribano, vuelva a intentarlo más tarde.";
	        throw new RuntimeException(msj);
	    }
	}
	
	private boolean validarCuit(String cuit) {
	    if (cuit.matches("^\\d{11}|\\d{2}-\\d{8}-\\d{1}$"))
	        return false;
	    
	    return true;
	}
}
