package com.egybank.security.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Here we have changed the filtering, we're trying different matchers, spring
 * security leverages 3 different matchers (Ant Matchers, MVC Matchers, Regex
 * Matchers) , we are using our own customizations like
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
public class SecurityConfigV09 extends WebSecurityConfigurerAdapter {

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests((requests) -> requests
				.mvcMatchers("/public/**").permitAll() // ** -> means any number of paths, * -> means single number
				.mvcMatchers(HttpMethod.POST, "/secret").denyAll() // Always fails
				.mvcMatchers("/accounts").hasAnyRole("USER", "ADMIN", "ROOT") // Requires special authorization
				.regexMatchers("/balance").hasAnyRole("ADMIN", "ROOT")
				.regexMatchers("/loans").hasRole("ROOT")
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
