package com.automation.framework.page.site.google;

import com.automation.framework.core.annotation.PageComponent;
import com.automation.framework.page.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@PageComponent
public class SearchResultsComponent extends BasePage {

    private final String SEARCH_RESULTS = "div.rc";

    @FindBy(css = SEARCH_RESULTS)
    private List<WebElement> searchResults;

    @Step
    public List<String> getSearchResults(){
        return this.searchResults.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }





}
