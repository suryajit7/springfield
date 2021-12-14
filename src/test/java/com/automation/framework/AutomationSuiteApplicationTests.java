package com.automation.framework;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@Slf4j
@SpringBootTest
@Isolated
@TestPropertySource(locations = {"classpath:application.properties"})
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(PER_CLASS)
public class AutomationSuiteApplicationTests {

	@Value("${app.api.key}")
	protected String apiKey;

	@Value("${app.postman.url}")
	protected String postmanUrl;

	@Value("${app.postman.mock.url}")
	protected String postmanMockServerUrl;

	@BeforeAll
	public void setup(){
		log.info("****** Spring Context loaded ******");
		log.info("Display Method Name");
		log.info(String.valueOf(Thread.currentThread().getId()));
	}

	@AfterAll
	public void quitSetup(){
		log.info("****** Spring Context Destroyed ******");
	}



}
