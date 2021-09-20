package com.automation.framework.page.app.google;

import com.automation.framework.core.annotation.Page;
import com.automation.framework.page.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Page
public class GoogleSearchPage extends BasePage {

    @Autowired
    private SearchComponent searchComponent;

    @Autowired
    private SearchResultsComponent searchResultsComponent;

    @Value("${application.url}")
    private String url;

    public void goToURL() {
        goTo(url);
    }

    public void performSearch(String input) {
        this.searchComponent.searchInGoogle(input);
    }


}
