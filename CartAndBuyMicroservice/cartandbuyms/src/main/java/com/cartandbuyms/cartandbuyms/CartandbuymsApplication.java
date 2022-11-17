package com.cartandbuyms.cartandbuyms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CartandbuymsApplication implements CommandLineRunner{

	@Autowired
	MongoTemplate mongoTemplate;
	public static void main(String[] args) {
		SpringApplication.run(CartandbuymsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.getAllProductDetails();
		
	}

}
