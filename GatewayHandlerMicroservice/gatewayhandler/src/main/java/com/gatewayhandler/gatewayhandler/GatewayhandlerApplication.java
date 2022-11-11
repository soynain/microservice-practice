package com.gatewayhandler.gatewayhandler;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

//import com.google.common.net.HttpHeaders;

@SpringBootApplication
public class GatewayhandlerApplication  {
	/*Seems this cors filter works for the headers,
	 * so for any header that you plan to use
	 * from your ajax request, you must set those
	 * one by one in this bean, and yeah it works
	*/
	@Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("*"));
        corsConfig.addAllowedHeader("Content-Type");
		corsConfig.addAllowedHeader("token-auth");
		corsConfig.addAllowedHeader("Authorization");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
	

	public static void main(String[] args) {
		SpringApplication.run(GatewayhandlerApplication.class, args);
	}
}
