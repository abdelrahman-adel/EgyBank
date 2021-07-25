package com.egybank.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Here we have the simple filtration same as previous version, we are using
 * {@link InMemoryUserDetailsManager} with "no" password encoder.
 * 
 * @author Abdelrahman
 *
 */
public class SecurityConfigV2 extends WebSecurityConfigurerAdapter {

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests((requests) -> requests.antMatchers("/public/*").permitAll() // Always passes
				.antMatchers("/secret").denyAll() // Always fails
				.anyRequest().authenticated()); // Must be authenticated to pass
		http.formLogin();
		http.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
		UserDetails user = User.withUsername("user").password("12345").authorities("read").build();
		userDetailsManager.createUser(admin);
		userDetailsManager.createUser(user);
		auth.userDetailsService(userDetailsManager);
	}
	// @formatter:on

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
