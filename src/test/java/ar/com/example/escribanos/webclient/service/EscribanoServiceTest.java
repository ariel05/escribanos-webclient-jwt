package ar.com.example.escribanos.webclient.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ar.com.example.escribanos.webclient.model.EscribanoDTO;

@ExtendWith(MockitoExtension.class)
class EscribanoServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private JwtTokenHolder tokenHolder;

	@InjectMocks
	private EscribanoService escribanoService;

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(escribanoService, "url", "http://test-url/");
		ReflectionTestUtils.setField(escribanoService, "endpoint", "escribanos/");

		when(tokenHolder.getToken()).thenReturn("mocked_token");
	}

	@Test
	void testObtenerDatosEscribanoPorCuit_Exitoso() {
		EscribanoDTO escribanoDTO = new EscribanoDTO();
		ResponseEntity<EscribanoDTO> responseEntity = new ResponseEntity<>(escribanoDTO, HttpStatus.OK);

		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(EscribanoDTO.class)))
				.thenReturn(responseEntity);

		String cuit = "20-12345678-9";
		ResponseEntity<EscribanoDTO> result = escribanoService.obtenerDatosEscribanoPorCuit(cuit);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertNotNull(result.getBody());
	}

	@Test
	void testObtenerDatosEscribanoPorCuit_Error404() {
	        when(restTemplate.exchange(
	                anyString(),
	                eq(HttpMethod.GET),
	                any(HttpEntity.class),
	                eq(EscribanoDTO.class))
	        ).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

	        String cuit = "20-12345678-9";
	        ResponseEntity<EscribanoDTO> result = escribanoService.obtenerDatosEscribanoPorCuit(cuit);

	        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	    }

	@Test
	void testObtenerDatosEscribanoPorCuit_ErrorUnauthorized() {
	        when(restTemplate.exchange(
	                anyString(),
	                eq(HttpMethod.GET),
	                any(HttpEntity.class),
	                eq(EscribanoDTO.class))
	        ).thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

	        String cuit = "20-12345678-9";
	        assertThrows(RuntimeException.class, () -> {
	            escribanoService.obtenerDatosEscribanoPorCuit(cuit);
	        });
	    }

	@Test
	void testObtenerDatosEscribanoPorCuit_ErrorForbidden() {
	        when(restTemplate.exchange(
	                anyString(),
	                eq(HttpMethod.GET),
	                any(HttpEntity.class),
	                eq(EscribanoDTO.class))
	        ).thenThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

	        String cuit = "20-12345678-9";
	        assertThrows(RuntimeException.class, () -> {
	            escribanoService.obtenerDatosEscribanoPorCuit(cuit);
	        });
	    }

}
