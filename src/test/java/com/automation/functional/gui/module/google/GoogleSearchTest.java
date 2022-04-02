package com.automation.functional.gui.module.google;

import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.site.google.GoogleSearchPage;
import com.automation.functional.BaseTestNGTest;
import org.testng.annotations.Test;

import static com.automation.framework.util.testhelper.AssertWebElement.assertThat;
import static com.automation.framework.util.testhelper.TestExecutionListener.getTestMethod;


public class GoogleSearchTest extends BaseTestNGTest {

    @LazyAutowired
    private GoogleSearchPage googleSearchPage;


    @Test
    public void verifySearchTest() {

        this.googleSearchPage.goToURL();

        assertThat(googleSearchPage.getSearchComponent().getSearchInputField())
                .isDisplayed()
                .isNotNull();

        this.googleSearchPage.changeLanguage();

        logger.info("url passed");

        this.screenshotService.takeWebPageScreenshot(getTestMethod(), "");
        this.screenshotService.assertWebPageScreenshots(getTestMethod(), "");
    }

    @Test
    public void verifyGoogleSearchTest() {

        this.googleSearchPage.goToURL()
                .performSearch("test2");

        this.screenshotService.takeWebPageScreenshot(getTestMethod(), "test2");
    }


}
