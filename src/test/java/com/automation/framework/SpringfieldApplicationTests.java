package com.automation.framework;


import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.core.config.ConfigurableBean;
import com.automation.framework.util.AppContextProvider;
import com.automation.framework.util.file.FileReader;
import com.automation.framework.util.file.PathFinder;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import com.github.javafaker.Faker;
import io.restassured.module.jsv.JsonSchemaValidatorSettings;
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

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.module.jsv.JsonSchemaValidator.settings;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@SpringBootTest
@Isolated
@TestPropertySource(locations = {"classpath:application.properties"})
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(PER_CLASS)
//@ExtendWith(TestDBSetup.class)
@ComponentScan
public class SpringfieldApplicationTests {

	protected static AppContextProvider appCtx = new AppContextProvider();

	@Autowired
	protected ConfigurableBean myBean;

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

	protected JsonValidator validator;

	protected final ValidationConfiguration validationConfig = ValidationConfiguration.newBuilder()
			.setDefaultVersion(DRAFTV4).freeze();

	protected final JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
			.setValidationConfiguration(validationConfig).freeze();

	@BeforeAll
	public void setup(){
		logger.info("****** Spring Context loaded ******");
		logger.info("Thread: ".concat(String.valueOf(Thread.currentThread().getId())));

		settings = JsonSchemaValidatorSettings.settings()
				.with().jsonSchemaFactory(jsonSchemaFactory)
				.and().with().checkedValidation(true);

		validator = jsonSchemaFactory.getValidator();

		myBean = appCtx.getBeanOfType(ConfigurableBean.class);
		myBean.setExpiredAccessToken(false);

	}

	@AfterAll
	public void quitSetup(){
		logger.info("****** Spring Context Destroyed ******");
	}



}
