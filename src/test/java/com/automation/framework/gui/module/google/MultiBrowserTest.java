package com.automation.framework.gui.module.google;

import com.automation.framework.gui.BaseTestNGTest;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

public class MultiBrowserTest extends BaseTestNGTest {


    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void browserTest() {
        this.applicationContext.getBean("getChromeInstance", WebDriver.class)
                .get("http://www.google.com");

        /*this.applicationContext.getBean("getGeckoInstance", WebDriver.class)
                .get("http://www.google.com");*/


    }


}
