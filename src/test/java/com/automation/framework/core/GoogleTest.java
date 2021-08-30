package com.automation.framework.core;

import com.automation.framework.core.pages.google.GoogleSearchPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTestNGTest {


    @Autowired
    private GoogleSearchPage googleSearchPage;

    @Test
    public void googleSearchTest() {
        this.googleSearchPage.goToURL();
        this.googleSearchPage.performSearch();
    }


}
