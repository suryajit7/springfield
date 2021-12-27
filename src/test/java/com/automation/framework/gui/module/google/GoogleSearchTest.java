package com.automation.framework.gui.module.google;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.app.google.GoogleSearchPage;
import com.automation.framework.util.ScreenshotService;
import org.testng.annotations.Test;

import static com.automation.framework.core.AssertWebElement.assertThat;
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

        assertThat(googleSearchPage.getSearchComponent().getSearchInputField())
                .isDisplayed()
                .isNotNull();

        this.screenshotService.takeWebPageScreenshot(getTestMethod(), "");
        this.googleSearchPage.changeLanguage();
        this.screenshotService.assertWebPageScreenshots(getTestMethod(), "");
    }


}
