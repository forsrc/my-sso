package org.forsrc.sso.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
				.antMatchers("/actuator/**", "/.well-known/openid-configuration", "/static/**").permitAll()
			)
			.authorizeRequests(
					authorizeRequests -> authorizeRequests.anyRequest().authenticated()
			)
			.formLogin(withDefaults())
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.failureUrl("/login?error")
			.permitAll()
			.and()
			.logout()
			.logoutUrl("/logout")
			.permitAll()
			.logoutSuccessUrl("/login?logout")
			.permitAll()
			;
		// @formatter:on
		return http.build();
	}


	@Bean
	UserDetailsService users() {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM t_sso_user WHERE username = LOWER(?)");
		manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM t_sso_authority WHERE username = LOWER(?)");
//		manager.setGroupAuthoritiesByUsernameQuery(
//				"SELECT g.id, g.group_name, ga.authority FROM groups g, group_members gm, group_authorities ga WHERE gm.username = ? and g.id = ga.group_id and g.id = gm.group_id");
		return manager;
	}

//	@Bean
//	public AuthenticationManager bindAuthenticationProvider(@Autowired AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//
//		// authenticationManagerBuilder.authenticationProvider(...);
//		AuthenticationManager authenticationManager = authenticationManagerBuilder.getObject();
//		System.out.println(authenticationManager);
//		return authenticationManager;
//	}

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("forsrc:" + encoder.encode("forsrc"));
		System.out.println("user:" + encoder.encode("user"));
		System.out.println("tcc:" + encoder.encode("tcc"));
		System.out.println("test:" + encoder.encode("test"));
	}
}