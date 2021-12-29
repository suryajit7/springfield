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

        WebDriver driver = appCtx.getBean(WebDriver.class);

        driver.quit();

        logger.info("Quiting all browser instances.");
        logger.info("****** Suite Tear Down ******");
    }

}
