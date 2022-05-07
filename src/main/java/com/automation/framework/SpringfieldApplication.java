package com.automation.framework;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static org.springframework.boot.SpringApplication.run;


@Configuration
@ComponentScan("com.automation")
@SpringBootApplication
@EntityScan({"com.automation"})
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.automation")
public class SpringfieldApplication {

	public static void main(String[] args) {
		run(SpringfieldApplication.class, args);
	}

}
