package com.automation.framework;


import com.automation.framework.core.bean.FakerConfig;
import com.automation.framework.util.service.PropertyDecryptService;
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
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.PostConstruct;

import static com.automation.framework.data.Constants.JASYPT_ENCRYPTOR_KEY;
import static com.automation.framework.data.Constants.JASYPT_ENCRYPTOR_VALUE;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@Slf4j
@SpringBootTest
@Isolated
@TestPropertySource(locations = {"classpath:application.properties"})
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(PER_CLASS)
public class AutomationSuiteApplicationTests {

	protected Log logger;
	protected Faker faker;
	protected PropertyDecryptService decryptService;

	@Autowired
	protected ApplicationContext appCtx;

	@Autowired
	protected FakerConfig fakerConfig;

	@Value("${app.postman.url}")
	protected String postmanUrl;

	@Value("${app.postman.mock.url}")
	protected String postmanMockServerUrl;

	@PostConstruct
	private void init() {

		System.setProperty(JASYPT_ENCRYPTOR_KEY, JASYPT_ENCRYPTOR_VALUE);

		this.decryptService = appCtx.getBean(PropertyDecryptService.class);
		this.faker = fakerConfig.getFakerConfig();
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
