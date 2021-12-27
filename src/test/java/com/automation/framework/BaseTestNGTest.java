package com.automation.framework;

import com.automation.framework.report.TestExecutionListener;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.util.Optional;
import java.util.stream.Collectors;


@SpringBootTest
@Listeners(TestExecutionListener.class)
public class BaseTestNGTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected ApplicationContext appCtx;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        logger.info("***** Before Suite Setup *****");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        logger.info("***** Tear Down Setup *****");

        Optional<WebDriver> driver = Optional.of(appCtx.getBean(WebDriver.class));
        driver.get().quit();
        logger.info("Quiting all browser instances.");

        appCtx.getBean(WebDriver.class).quit();

        logger.info(driver.stream().distinct().collect(Collectors.toList()));

/*        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();

        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) appCtx.getBean("taskExecutor");

        threadSet.stream().forEach(thread -> {
            logger.info("Active Thread: ".concat(thread.getName().concat(String.valueOf(thread.getId()))));
            taskExecutor.shutdown();
        });*/

        logger.info("***** Suite Tear Down *****");
    }

}
