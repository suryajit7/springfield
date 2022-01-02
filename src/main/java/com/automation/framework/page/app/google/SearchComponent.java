package com.automation.framework.page.app.google;

import com.automation.framework.core.annotation.PageComponent;
import com.automation.framework.page.BasePage;
import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Data
@PageComponent
public class SearchComponent extends BasePage {

    private final String SEARCH_INPUT_FIELD = "//input[@title='Search']";
    private final String GOOGLE_SEARCH_BTN = "btnk";

    @FindBy(xpath = SEARCH_INPUT_FIELD)
    private WebElement searchInputField;

    @FindBy(name = GOOGLE_SEARCH_BTN)
    private List<WebElement> googleSearchBtnList;

    @Step
    public BasePage searchInGoogle(String input) {
        wait.until(visibilityOf(this.searchInputField));
        this.searchInputField.click();
        this.searchInputField.sendKeys(input);
        return this;
    }

    @Step
    public BasePage clickGoogleSearchButton() {
        this.googleSearchBtnList.stream()
                .filter(btn -> btn.isEnabled() && btn.isDisplayed())
                .findFirst().get().click();
        return this;
    }


}
