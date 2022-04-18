package com.automation.framework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@Configuration
@ComponentScan
@SpringBootApplication
@EntityScan({"com.automation"})
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.automation")
public class AutomationSuiteApplication {

	public static void main(String[] args) {

		log.info("***** AutomationSuiteApplication *****");
		SpringApplication.run(AutomationSuiteApplication.class, args);
	}

}
