package com.automation.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class AutomationSuiteApplication {

	public static void main(String[] args) {
		System.out.println("***** AutomationSuiteApplication *****");
		SpringApplication.run(AutomationSuiteApplication.class, args);
	}

}
