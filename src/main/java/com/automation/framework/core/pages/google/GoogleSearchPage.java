package com.automation.framework.core.pages.google;

import com.automation.framework.core.pages.BasePage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleSearchPage extends BasePage {

    @Autowired
    private SearchComponent searchComponent;

    @Autowired
    private SearchResultsComponent searchResultsComponent;

    @Value("${application.url}")
    private String url;

    public void goToURL() {
        this.driver.get(url);
    }

    public void performSearch() {
        this.searchComponent.searchInGoogle("sas");
    }


}
