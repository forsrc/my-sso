package org.forsrc.sso.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private DataSource dataSource;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
 
		http
			.authorizeRequests(a -> a
				.antMatchers("/actuator/**", "/.well-known/openid-configuration").permitAll()
			)
			.authorizeRequests(
					authorizeRequests -> authorizeRequests.anyRequest().authenticated()
			)
			.formLogin(withDefaults())
			.formLogin()
			.failureUrl("/login?error")
			;
		// @formatter:on
		return http.build();
	}

	
	
	@Bean
	UserDetailsService users() {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
		manager.setAuthoritiesByUsernameQuery("SELECT username,authority FROM authorities WHERE username = ?");
//		manager.setGroupAuthoritiesByUsernameQuery(
//				"SELECT g.id, g.group_name, ga.authority FROM groups g, group_members gm, group_authorities ga WHERE gm.username = ? and g.id = ga.group_id and g.id = gm.group_id");
		return manager;
	}

}