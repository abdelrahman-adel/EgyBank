package com.egybank.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Here we are simply filtering which requests should be authenticated and which
 * not, we are using {@link InMemoryUserDetailsManagerConfigurer} with "no"
 * password encoder.
 * 
 * @author Abdelrahman
 *
 */
public class SecurityConfigV01 extends WebSecurityConfigurerAdapter {

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests((requests) -> requests.antMatchers("/public/*").permitAll() // Always passes
				.antMatchers("/secret").denyAll() // Always fails
				.anyRequest().authenticated()); // Must be authenticated to pass
		http.formLogin();
		http.httpBasic();

		// Another way to do the same

//		http.authorizeRequests().antMatchers("/public").permitAll() // Always passes
//				.antMatchers("/secret").denyAll() // Always fails
//				.anyRequest().authenticated() // Must be authenticated to pass
//				.and().formLogin()
//				.and().httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("12345").authorities("admin")
			.and().withUser("user").password("12345").authorities("read")
			.and().passwordEncoder(passwordEncoder());
	}
	// @formatter:on

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
