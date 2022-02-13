package org.forsrc.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off 
		http.authorizeRequests(a -> a.antMatchers("/actuator/**").permitAll())
			.mvcMatcher("/api/**")
			.authorizeRequests()
			.mvcMatchers("/api/**").access("hasAuthority('SCOPE_api')")
			.and()
			.oauth2ResourceServer()
			.jwt();
		// @formatter:on
		return http.build();
	}
}