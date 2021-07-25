package com.egybank.security.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Here we have changed the filtering, same as before but we added part for CORS
 * policy, we are using our own customizations like
 * {@link EgyBankUserDetailService} or
 * {@link EgyBankUserPwdAuthenticationProvider} with
 * {@link SCryptPasswordEncoder} password encoder.
 * 
 * @author Abdelrahman
 *
 */
@Configuration
public class SecurityConfigV5 extends WebSecurityConfigurerAdapter {

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests((requests) -> requests.antMatchers("/public/*").permitAll() // Always passes
				.antMatchers("/secret").denyAll() // Always fails
				.anyRequest().authenticated()); // Must be authenticated to pass
		http.cors().configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration corsConfig = new CorsConfiguration();
				corsConfig.addAllowedOrigin("http://localhost:4200");
				corsConfig.addAllowedMethod("*");
				corsConfig.addAllowedHeader("*");
				corsConfig.setAllowCredentials(true);
				return corsConfig;
			}
		});
		http.formLogin();
		http.httpBasic();
	}
	// @formatter:on

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new SCryptPasswordEncoder();
	}

}
