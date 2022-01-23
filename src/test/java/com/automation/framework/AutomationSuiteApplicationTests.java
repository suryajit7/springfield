package com.automation.framework;


import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.core.config.ConfigurableBean;
import com.automation.framework.util.AppContextProvider;
import com.automation.framework.util.file.FileReader;
import com.automation.framework.util.file.PathFinder;
import com.github.javafaker.Faker;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@SpringBootTest
@Isolated
@TestPropertySource(locations = {"classpath:application.properties"})
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(PER_CLASS)
@ComponentScan
public class AutomationSuiteApplicationTests {

	public MySQLContainer mySql;
	public GenericContainer genericContainer;

	private static AppContextProvider appCtx = new AppContextProvider();

	@Autowired
	protected static ConfigurableBean myBean;

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
		
		genericContainer = new GenericContainer("mongo:3.2.4")
				.withExposedPorts(27017);

		myBean = appCtx.getBeanOfType(ConfigurableBean.class);
		myBean.setExpiredAccessToken(false);

	}

	@AfterAll
	public void quitSetup(){
		logger.info("****** Spring Context Destroyed ******");
	}



}
