package com.egybank.security.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Here we have changed the filtering, same CORS policy configurations, same
 * CSRF configurations, but we've added some authorization configurations based
 * on AUTHORITIES, we are using our own customizations like
 * {@link EgyBankUserDetailService} or
 * {@link EgyBankUserPwdAuthenticationProvider} with
 * {@link SCryptPasswordEncoder} password encoder.
 * 
 * CORS: Cross Origin Resource Sharing
 * 
 * CSRF: Cross Site Request Forgery
 * 
 * @author Abdelrahman
 *
 */
public class SecurityConfigV07 extends WebSecurityConfigurerAdapter {

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests((requests) -> requests.antMatchers("/public/*").permitAll() // Always passes
				.antMatchers("/secret").denyAll() // Always fails
				.antMatchers("/accounts").hasAuthority("READ") // Requires special authorization
				.antMatchers("/balance").hasAuthority("WRITE")
				.antMatchers("/loans").hasAuthority("DELETE")
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
		http.csrf().ignoringAntMatchers("/public/*").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		http.formLogin();
		http.httpBasic();
	}
	// @formatter:on

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new SCryptPasswordEncoder();
	}

}
