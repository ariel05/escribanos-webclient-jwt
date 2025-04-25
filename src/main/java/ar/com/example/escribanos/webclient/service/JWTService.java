package ar.com.example.escribanos.webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.example.escribanos.webclient.utils.JWTUtils;

@Service
public class JWTService {

	@Value("${jwt.issuer}")
	private String issuer;
	@Value("${jwt.subject}")
	private String subject;
	@Value("${jwt.key}")
	private String key;
	@Value("${jwt.audience}")
	private String audience;
	@Value("${jwt.role}")
	private String role;
	
	@Autowired
	private JwtTokenHolder tokenHolder;
	private long MINUTOS; 
	
	public JWTService() {
		MINUTOS = 2;
	}
	
	public String getTokenV2() {
		MINUTOS = 10;
		return getToken();
	}
	
	public String getToken() {
		String token = JWTUtils.buildToken(issuer, audience, subject, role, key, MINUTOS);
		tokenHolder.setToken(token);
		
		return token;
	}

	
}
