package com.automation.framework;


import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@Slf4j
@SpringBootTest
@Isolated
@TestPropertySource(locations = {"classpath:application.properties"})
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(PER_CLASS)
public class AutomationSuiteApplicationTests {

	protected Log logger;

	@Autowired
	protected Faker faker;

	@Value("${app.postman.url}")
	protected String postmanUrl;

	@Value("${app.postman.mock.url}")
	protected String postmanMockServerUrl;

	@PostConstruct
	private void setupData() {
		this.logger = LogFactory.getLog(getClass());
	}

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
