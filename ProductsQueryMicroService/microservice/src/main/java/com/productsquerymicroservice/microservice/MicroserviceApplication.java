package com.productsquerymicroservice.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroserviceApplication {
	static final Logger log=LoggerFactory.getLogger(MicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
		log.info("CORRIENDO LA APLICACIÓN EXITOSAMENTE");
	}

}
