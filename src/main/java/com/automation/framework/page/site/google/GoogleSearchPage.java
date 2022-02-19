package com.automation.framework.page.site.google;

import com.automation.framework.core.annotation.Page;
import com.automation.framework.core.annotation.Screenshot;
import com.automation.framework.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Page
@Getter
public class GoogleSearchPage extends BasePage {

    private static final String HINDI_LANGUAGE = "//a[contains(text(),'हिन्दी')]";

    @Autowired
    private SearchComponent searchComponent;

    @Autowired
    private SearchResultsComponent searchResultsComponent;

    @FindBy(xpath = HINDI_LANGUAGE)
    private WebElement hindiLanguage;

    @Value("${application.url}")
    private String url;


    public GoogleSearchPage goToURL() {
        goTo(url);
        return this;
    }


    @Screenshot
    public GoogleSearchPage performSearch(String input) {
        this.searchComponent.searchInGoogle(input);
        return this;
    }


    @Screenshot
    public GoogleSearchPage changeLanguage(){
        this.hindiLanguage.click();
        return this;
    }


}
