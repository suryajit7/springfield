package com.automation.framework.gui.module.google;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.app.google.GoogleSearchPage;
import com.automation.framework.util.ScreenshotService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static com.automation.framework.report.TestExecutionListener.getTestMethod;

@Epic("Google")
@Feature("GoogleSearch")
public class GoogleSearchTest2 extends BaseTestNGTest {


    @LazyAutowired
    private GoogleSearchPage googleSearchPage;

    @LazyAutowired
    private ScreenshotService screenshotService;

    @Test
    public void googleSearchTest() {
        this.googleSearchPage.goToURL();
        this.googleSearchPage.performSearch("test2");
        this.screenshotService.takeWebPageScreenshot(getTestMethod(), "test2");

    }


}
