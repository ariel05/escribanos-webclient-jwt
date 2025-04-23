package ar.com.example.escribanos.webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.com.example.escribanos.webclient.model.EscribanoDTO;

@Service
public class EscribanoService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${jwt.secret}")
	private String jwtToken;
	@Value("${url.servicio.escribanos}")
	private String url;
	@Value("${servicio.escribanos.method}")
	private String endpoint;
	
	private final String bearer = "Bearer ";
	
	public EscribanoDTO obtenerDatosEscribanoPorCuit(String cuit) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearer.concat(jwtToken));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EscribanoDTO> entity = new HttpEntity<>(headers);

        ResponseEntity<EscribanoDTO> response = restTemplate.exchange(
            url.concat(endpoint).concat(cuit.replaceAll("-", "")),
            HttpMethod.GET,
            entity,
            EscribanoDTO.class
        );
        
        return response.getBody();
	}
}
