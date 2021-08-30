package com.automation.framework.core.pages.google;

import com.automation.framework.core.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchComponent extends BasePage {

    private final String SEARCH_INPUT_FIELD = "//input[@title='Search']";
    private final String GOOGLE_SEARCH_BTN = "btnk";

    @FindBy(xpath = SEARCH_INPUT_FIELD)
    private WebElement searchInputField;

    @FindBy(name = GOOGLE_SEARCH_BTN)
    private List<WebElement> googleSearchBtnList;

    //TODO  - Add wait
    public void searchInGoogle(String input) {
        this.searchInputField.click();
        this.searchInputField.sendKeys(input);

    }

    public void clickGoogleSearchButton() {
        this.googleSearchBtnList.stream()
                .filter(btn -> btn.isEnabled() && btn.isDisplayed())
                .findFirst().get().click();
    }


}
