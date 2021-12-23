package com.automation.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutomationSuiteApplication {

	public static void main(String[] args) {
		System.out.println("***** AutomationSuiteApplication *****");
		SpringApplication.run(AutomationSuiteApplication.class, args);
	}

}
