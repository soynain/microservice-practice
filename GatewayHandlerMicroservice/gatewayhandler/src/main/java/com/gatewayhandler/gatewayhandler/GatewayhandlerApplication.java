package com.gatewayhandler.gatewayhandler;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

//import com.google.common.net.HttpHeaders;

@SpringBootApplication
public class GatewayhandlerApplication  {
	

	

	public static void main(String[] args) {
		SpringApplication.run(GatewayhandlerApplication.class, args);
	}
}
