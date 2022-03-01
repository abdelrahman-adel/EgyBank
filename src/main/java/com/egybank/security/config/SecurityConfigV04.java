package com.egybank.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * Here we have the simple filtration same as previous version, we are using our
 * own customizations like {@link EgyBankUserDetailService} or
 * {@link EgyBankUserPwdAuthenticationProvider} with
 * {@link SCryptPasswordEncoder} password encoder.
 * 
 * @author Abdelrahman
 *
 */
public class SecurityConfigV04 extends WebSecurityConfigurerAdapter {

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
	public PasswordEncoder passwordEncoder() {
		return new SCryptPasswordEncoder();
	}

}
