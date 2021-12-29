package com.automation.framework.page.app.google;

import com.automation.framework.core.annotation.Page;
import com.automation.framework.core.annotation.Screenshot;
import com.automation.framework.page.BasePage;
import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Page
@Data
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

    @Step
    public void goToURL() {
        goTo(url);
    }

    @Step
    @Screenshot
    public void performSearch(String input) {
        this.searchComponent.searchInGoogle(input);
    }

    @Step
    @Screenshot
    public void changeLanguage(){
        this.hindiLanguage.click();
    }


}
