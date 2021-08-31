package com.automation.framework.core.module.google;

import com.automation.framework.core.BaseTestNGTest;
import com.automation.framework.core.pages.google.GoogleSearchPage;
import com.automation.framework.core.util.ScreenshotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.automation.framework.core.reporting.TestExecutionListener.getTestMethod;

public class GoogleSearchTest extends BaseTestNGTest {


    @Autowired
    private GoogleSearchPage googleSearchPage;

    @Autowired
    private ScreenshotUtil screenshotUtil;

    @Test
    public void googleSearchTest() {
        this.googleSearchPage.goToURL();
        this.googleSearchPage.performSearch();
        this.screenshotUtil.takeScreenshot(getTestMethod(), "test1");

    }


}
