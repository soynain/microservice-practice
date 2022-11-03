package com.oauthserver.auth.AuthServerConfiguration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.oauthserver.auth.AuthErrorHandlers.AuthFailureHandler;
import com.oauthserver.auth.AuthErrorHandlers.AuthLogoutHandler;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class AuthConfiguration {
	public static final Logger log = LoggerFactory.getLogger(AuthConfiguration.class);

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		// return http.formLogin(Customizer.withDefaults()).build();
		  return http
		  .authorizeRequests(auth->auth.antMatchers("/**").authenticated())
		 // .authorizeRequests(auth->auth.antMatchers("/login**").permitAll())
		  //.authorizeRequests(auth->auth.antMatchers("/microservice**").permitAll())
		  .formLogin()
		  .loginPage("/login")
		  .usernameParameter("username")
		  .passwordParameter("password")
		  .permitAll()
		  .defaultSuccessUrl("/another", true)
		  .failureHandler(authenticationFailureHandler())
		  .permitAll()
		  .and()
		  .logout()
		  //.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		  //.logoutUrl("/microservice/logout")
		  .deleteCookies("JESSIONID")
		  .logoutSuccessHandler(authenticationLogoutHandler())
		  .permitAll()
		  .and()
		  .csrf()
		  .disable()
		  .build();

	}

	@Bean
	public InMemoryUserDetailsManager user() {

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new InMemoryUserDetailsManager(
				User.withUsername("n")
						.password(encoder.encode("1"))
						.authorities("read")
						.build());
	}

	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId("messaging-client")
				.clientSecret("{noop}secret")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.redirectUri("http://127.0.0.1:9000/login/oauth2/code/messaging-client-oidc")
				.redirectUri("http://127.0.0.1:9000/authorized")
				.redirectUri("http://spring.io/auth")
				.scope(OidcScopes.OPENID)
				.scope("message.read")
				.scope("message.write")

				// .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
				.build();

		return new InMemoryRegisteredClientRepository(registeredClient);
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		KeyPair keyPair = generateRsaKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAKey rsaKey = new RSAKey.Builder(publicKey)
				.privateKey(privateKey)
				.keyID(UUID.randomUUID().toString())
				.build();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<>(jwkSet);
	}

	@Bean
	private static KeyPair generateRsaKey() {
		KeyPair keyPair;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
		return keyPair;
	} 

 	@Bean
	public ProviderSettings providerSettings() {
		return ProviderSettings.builder().build();
	}

	@Bean
	public AuthFailureHandler authenticationFailureHandler() {
		return new AuthFailureHandler();
	}

	@Bean
	public AuthLogoutHandler authenticationLogoutHandler() {
		return new AuthLogoutHandler();
	}

}
