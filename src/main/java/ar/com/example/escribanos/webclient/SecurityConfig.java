package ar.com.example.escribanos.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(auth -> auth.anyRequest().permitAll())
				.exceptionHandling(ex -> ex.accessDeniedPage("/error-page")
				).csrf(csrf -> csrf.disable())
				.formLogin(login -> login.disable())
				.httpBasic(basic -> basic.disable());

		return http.build();
	}
}
