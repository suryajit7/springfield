package com.automation.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.testng.TestNG;


@Configuration
@ComponentScan
@SpringBootApplication
@EntityScan({"com.automation"})
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.automation")
public class SpringfieldApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestNG.class, args);
	}

}
