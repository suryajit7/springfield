package com.automation.framework.core.pages.google;

import com.automation.framework.core.config.annotations.PageComponent;
import com.automation.framework.core.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@PageComponent
public class SearchResultsComponent extends BasePage {

    private final String SEARCH_RESULTS = "div.rc";

    @FindBy(css = SEARCH_RESULTS)
    private List<WebElement> searchResults;

    public List<String> getSearchResults(){
        return this.searchResults.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }





}