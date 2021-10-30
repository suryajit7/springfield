package com.automation.framework;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Slf4j
@SpringBootTest
@Execution(CONCURRENT)
@TestPropertySource(locations = {"classpath:application.properties"})
@TestInstance(Lifecycle.PER_CLASS)
public class AutomationSuiteApplicationTests {


	@BeforeAll
	public static void setup(){}

	@Test
	void verifySpringContextLoad() {

		log.info("******Spring Context loaded******");
	}



}
