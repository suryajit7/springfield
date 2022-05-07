package com.automation.framework;

import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.util.PropertyDecryptService;
import com.automation.framework.util.ScreenshotService;
import com.automation.framework.util.TestExecutionListener;
import com.github.javafaker.Faker;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;


@SpringBootTest
@ContextConfiguration(loader= AnnotationConfigContextLoader.class, classes = SpringfieldApplication.class)
@Listeners(TestExecutionListener.class)
public class BaseTestNGTest extends AbstractTestNGSpringContextTests {

    @LazyAutowired
    protected ApplicationContext appCtx;

    @LazyAutowired
    protected PropertyDecryptService decryptService;

    @LazyAutowired
    protected ScreenshotService screenshotService;

    @LazyAutowired
    protected Faker faker;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        logger.info("****** Before Suite Setup ******");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        logger.info("****** Tear Down Setup ******");
        logger.info("****** Suite Tear Down ******");
    }

}
