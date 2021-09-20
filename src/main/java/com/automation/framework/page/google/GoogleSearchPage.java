package com.automation.framework.page.google;

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
        this.driver.get(url);

        logger.info("Page url passed");

    }

    public void performSearch(String input) {
        this.searchComponent.searchInGoogle(input);
    }


}
