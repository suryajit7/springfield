package com.automation.framework.module.google;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.config.annotation.LazyAutowired;
import com.automation.framework.page.google.GoogleSearchPage;
import com.automation.framework.util.ScreenshotService;
import org.testng.annotations.Test;

import static com.automation.framework.reporting.TestExecutionListener.getTestMethod;


public class GoogleSearchTest2 extends BaseTestNGTest {


    @LazyAutowired
    private GoogleSearchPage googleSearchPage;

    @LazyAutowired
    private ScreenshotService screenshotService;

    @Test
    public void googleSearchTest() {
        this.googleSearchPage.goToURL();
        this.googleSearchPage.performSearch("test2");
        this.screenshotService.takeScreenshot(getTestMethod(), "test2");

    }


}
