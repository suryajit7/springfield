package com.automation.framework.module.google;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.app.google.GoogleSearchPage;
import com.automation.framework.util.service.ScreenshotService;
import org.testng.annotations.Test;

import static com.automation.framework.report.TestExecutionListener.getTestMethod;


public class GoogleSearchTest extends BaseTestNGTest {


    @LazyAutowired
    private GoogleSearchPage googleSearchPage;

    @LazyAutowired
    private ScreenshotService screenshotService;

    @Test
    public void googleSearchTest() {
        this.googleSearchPage.goToURL();
        logger.info("url passed");
        this.googleSearchPage.performSearch("test1");
        this.screenshotService.takeScreenshot(getTestMethod(), "test1");

    }


}
