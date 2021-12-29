package com.automation.framework;


import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.util.FileReader;
import com.automation.framework.util.PathFinder;
import com.github.javafaker.Faker;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@SpringBootTest
@Isolated
@TestPropertySource(locations = {"classpath:application.properties"})
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(PER_CLASS)
public class AutomationSuiteApplicationTests {

	@LazyAutowired
	protected PathFinder pathFinder;

	@LazyAutowired
	protected FileReader fileReader;

	@LazyAutowired
	protected Faker faker;

	@LazyAutowired
	protected Log logger;

	@Value("${app.postman.url}")
	protected String postmanUrl;

	@Value("${app.postman.mock.url}")
	protected String postmanMockServerUrl;

	@BeforeAll
	public void setup(){
		logger.info("****** Spring Context loaded ******");
		logger.info("Thread: ".concat(String.valueOf(Thread.currentThread().getId())));
	}

	@AfterAll
	public void quitSetup(){
		logger.info("****** Spring Context Destroyed ******");
	}



}
