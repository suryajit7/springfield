package com.automation.framework.gui.module.google;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.SpringfieldApplication;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.site.google.GoogleSearchPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static com.automation.framework.util.TestExecutionListener.getTestMethod;
import static com.automation.framework.util.testhelper.AssertWebElement.assertThat;

@ContextConfiguration(loader= AnnotationConfigContextLoader.class, classes = SpringfieldApplication.class)
public class GoogleSearchTest extends BaseTestNGTest {

    @LazyAutowired
    private GoogleSearchPage googleSearchPage;


    @AfterClass
    public void closeBrowser(){
        this.googleSearchPage.close();
    }

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
