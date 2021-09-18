package com.automation.framework.core.module.google;

import com.automation.framework.core.BaseTestNGTest;
import com.automation.framework.core.config.annotation.LazyAutowired;
import com.automation.framework.core.page.google.GoogleSearchPage;
import com.automation.framework.core.util.ScreenshotService;
import org.testng.annotations.Test;

import static com.automation.framework.core.reporting.TestExecutionListener.getTestMethod;


public class GoogleSearchTest extends BaseTestNGTest {


    @LazyAutowired
    private GoogleSearchPage googleSearchPage;

    @LazyAutowired
    private ScreenshotService screenshotService;

    @Test
    public void googleSearchTest() {
        this.googleSearchPage.goToURL();
        this.googleSearchPage.performSearch("test1");
        this.screenshotService.takeScreenshot(getTestMethod(), "test1");

    }


}
