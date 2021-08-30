package com.automation.framework.core.pages.google;

import com.automation.framework.core.pages.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleSearchPage extends BasePage {

    @Autowired
    private SearchComponent searchComponent;

    @Autowired
    private SearchResultsComponent searchResultsComponent;


}
