package com.egybank.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Here we have the simple filtration same as previous version, we are using
 * {@link JdbcUserDetailsManager} with {@link SCryptPasswordEncoder} password
 * encoder.
 * 
 * @author Abdelrahman
 *
 */
public class SecurityConfigV03 extends WebSecurityConfigurerAdapter {

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests((requests) -> requests.antMatchers("/public/*").permitAll() // Always passes
				.antMatchers("/secret").denyAll() // Always fails
				.anyRequest().authenticated()); // Must be authenticated to pass
		http.formLogin();
		http.httpBasic();
	}
	// @formatter:on

	@Bean
	public UserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new SCryptPasswordEncoder();
	}

}
