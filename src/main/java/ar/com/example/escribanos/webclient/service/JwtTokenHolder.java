package ar.com.example.escribanos.webclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenHolder {

	private String token;
	
	public JwtTokenHolder(@Value("${jwt.secret}")String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
