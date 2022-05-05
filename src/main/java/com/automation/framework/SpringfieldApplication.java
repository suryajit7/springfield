package com.automation.framework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.testng.TestNG;

import java.util.List;

@Slf4j
@Configuration
@ComponentScan
@SpringBootApplication
@EntityScan({"com.automation"})
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.automation")
public class SpringfieldApplication {

	public static void main(String[] args) {

		log.info("***** AutomationSuiteApplication *****");

		TestNG runner = new TestNG();

		List<String> suiteFiles = List.of("src\\test\\java\\com\\automation\\framework\\gui\\testng.xml");

		runner.setTestSuites(suiteFiles);

		runner.run();

		SpringApplication.run(SpringfieldApplication.class, args);
	}

}
