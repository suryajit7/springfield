package com.automation.framework;

import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.report.TestExecutionListener;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.Optional;


@SpringBootTest
@Listeners(TestExecutionListener.class)
public class BaseTestNGTest extends AbstractTestNGSpringContextTests {

    @LazyAutowired
    protected ApplicationContext appCtx;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        logger.info("****** Before Suite Setup ******");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {

        logger.info("****** Tear Down Setup ******");

        Optional<WebDriver> driver = Optional.ofNullable(appCtx.getBean(WebDriver.class));
        if (driver.isPresent()){
            driver.get().quit();
            logger.info("Quiting all browser instances.");
        } else {
            logger.warn("WebDriver instance is empty during tearing down.");
        }
        logger.info("****** Suite Tear Down ******");
    }

}
