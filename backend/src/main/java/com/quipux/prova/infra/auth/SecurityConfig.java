package com.quipux.prova.infra.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService, CorsProperties corsProperties)
			throws Exception {
		var jwtFilter = new JwtAuthenticationFilter(jwtService);
		http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(cors -> cors.configurationSource(corsConfigurationSource(corsProperties)))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
				.exceptionHandling(handler -> handler.authenticationEntryPoint((request, response, authException) -> {
					response.setStatus(401);
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					response.getWriter().write("{\"status\":401,\"error\":\"unauthorized\",\"message\":\"Autenticação necessária\"}");
				}))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.requestMatchers("/error", "/h2-console/**").permitAll()
						.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**", "/v3/api-docs.yaml").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/login", "/registrarName").permitAll()
						.requestMatchers(HttpMethod.GET, "/list", "/list/*").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/list/*").authenticated()
						.requestMatchers(HttpMethod.GET, "/findNacionalityByPerson/*").authenticated()
						.anyRequest().denyAll()
				)
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	private CorsConfigurationSource corsConfigurationSource(CorsProperties corsProperties) {
		var config = new CorsConfiguration();
		config.setAllowedOrigins(corsProperties.origins());
		config.setAllowedMethods(List.of("GET", "POST", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		var source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
