package com.automation.framework;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Slf4j
@SpringBootTest
@Execution(CONCURRENT)
@TestPropertySource(locations = {"classpath:application.properties"})
@TestMethodOrder(OrderAnnotation.class)
@DisplayNameGeneration(TestDisplayNameFormat.class)
@TestInstance(PER_CLASS)
public class AutomationSuiteApplicationTests {

	@BeforeAll
	public static void setup(){
		log.info("****** Spring Context loaded ******");
	}



}
