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
	
	public String getToken() {
		String token = JWTUtils.buildToken(issuer, audience, subject, role, key);
		tokenHolder.setToken(token);
		
		return token;
	}

	
}
