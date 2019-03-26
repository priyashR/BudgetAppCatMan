package com.gmail.ramawthar.priyash.categories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.gmail.ramawthar.priyash.repositories")
@EntityScan("com.gmail.ramawthar.priyash.models")
@ComponentScan("com.gmail.ramawthar.priyash.controllers")
public class CategoriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoriesApplication.class, args);
		
	}

}
