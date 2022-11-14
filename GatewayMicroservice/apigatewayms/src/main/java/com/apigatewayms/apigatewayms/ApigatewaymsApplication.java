package com.apigatewayms.apigatewayms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEurekaServer
public class ApigatewaymsApplication implements WebMvcConfigurer{
	
	public static void main(String[] args) {
		SpringApplication.run(ApigatewaymsApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
		WebMvcConfigurer.super.addCorsMappings(registry);
	}

}
