package org.forsrc.sso.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	
	@Value("${my.logout-url}")
	private String logoutUrl;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
 

		http
			.authorizeRequests(a -> a
			.antMatchers("/actuator/**").permitAll()
				  )
			.authorizeRequests(authorizeRequests ->
				authorizeRequests.anyRequest().authenticated()
			)
			.oauth2Login(oauth2Login ->
				oauth2Login.loginPage("/oauth2/authorization/oauth2-client-oidc"))
					.oauth2Client(withDefaults()
			)
			.logout(logoutCustomizer -> logoutCustomizer.logoutSuccessUrl(logoutUrl)
					.clearAuthentication(true)
					.deleteCookies()
					.invalidateHttpSession(true))
				
		;
		// @formatter:on
		return http.build();
	}
}
