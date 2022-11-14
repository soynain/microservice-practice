package com.oauthserver.auth.AuthServerConfiguration;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.oauthserver.auth.AuthErrorHandlers.AuthCustomEntryEndpoint;
import com.oauthserver.auth.Components.JwtRequestFilter;

import lombok.RequiredArgsConstructor;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthConfiguration {
	public static final Logger log = LoggerFactory.getLogger(AuthConfiguration.class);

	/* A bean for getting the customized error handler for requests */
	@Autowired
	private AuthCustomEntryEndpoint authCustomEntryEndpoint() {
		return new AuthCustomEntryEndpoint();
	}

	/* Bean for getting the token and validate it's expiry time */
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	/*
	 * I dont know what happened here but I saw on stack overflow that
	 * with lombok it's possible to initialize the AuthenticationConfiguration
	 * bean without the need of initializing by ourselves, introducing wrong
	 * parameters
	 * and shit. I think getting this bean for work was the most difficult
	 * thing till now, Lombok's a great fucking tool.
	 */
	private final AuthenticationConfiguration authenticationConfiguration;

	/* Authentication manager bean with authentication configuration injected */
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	/*
	 * The security filter chain to protect routes, add the filter to validate
	 * requests
	 * and the customized exception handling class
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.cors(cors->cors.disable())
				.csrf(csrf -> csrf.disable())
				.authorizeRequests(auth -> auth.antMatchers("/redirect/**", "/redirect").authenticated())
				.authorizeRequests().antMatchers("/auth").permitAll()
				.and()
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling().authenticationEntryPoint(authCustomEntryEndpoint())
				.and()
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	/*
	 * This is just a simulation of a database, sooner I'll get to work
	 * the user's database with Jpa or some shit
	 */
	@Bean
	public InMemoryUserDetailsManager user() {
		return new InMemoryUserDetailsManager(
				User.withUsername("n")
						.password(passwordEncoder().encode("1"))
						.authorities("read")
						.build());
	}

	/*
	 * By default the delegating method encrypts passwords in bcrypt, there are
	 * existent prefixes in spring boot to choose other algorithms such as
	 * sha 256, 512, etc
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
