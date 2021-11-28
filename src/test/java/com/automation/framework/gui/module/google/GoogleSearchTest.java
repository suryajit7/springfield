package com.automation.framework.gui.module.google;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.app.google.GoogleSearchPage;
import com.automation.framework.util.AssertField;
import com.automation.framework.util.service.ScreenshotService;
import com.google.i18n.phonenumbers.NumberParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.automation.framework.report.TestExecutionListener.getTestMethod;
import static com.automation.framework.util.AssertWebElement.assertThat;


public class GoogleSearchTest extends BaseTestNGTest {

    @LazyAutowired
    private GoogleSearchPage googleSearchPage;

    @LazyAutowired
    private ScreenshotService screenshotService;

    @Test
    public void googleSearchTest() throws IOException {

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
