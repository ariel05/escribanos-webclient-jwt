package ar.com.example.escribanos.webclient.utils;

import java.security.Key;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTUtils {

	public static String buildToken(String issuer, String audience, String subject, String role, String secret) {

        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + (2 * 60 * 1000); // 2 minutos

        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer(issuer)
                .setAudience(audience)
                .setSubject(subject)
                .claim("role", List.of(role))
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(expMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("JWT: " + jwt);
        return jwt;
	}
}
