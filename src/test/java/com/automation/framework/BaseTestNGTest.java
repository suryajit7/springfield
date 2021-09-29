package com.automation.framework;

import com.automation.framework.report.TestExecutionListener;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;

@SpringBootTest
@Listeners(TestExecutionListener.class)
public class BaseTestNGTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected ApplicationContext appCtx;

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        WebDriver driver = appCtx.getBean(WebDriver.class);
        if (null!=driver){
            driver.quit();
        }
    }

}
