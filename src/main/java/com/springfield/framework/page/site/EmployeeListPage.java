package com.springfield.framework.page.site;

import com.springfield.framework.core.annotation.PageObject;
import com.springfield.framework.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Getter
@PageObject
public class EmployeeListPage extends BasePage {

    private final String EMPLOYEE_INFO_HEADER = "//h5[normalize-space()='Employee Information']";
    private final String RESET_BUTTON = "//button[normalize-space()='Reset']";
    private final String SEARCH_BUTTON = "//button[normalize-space()='Search']";
    private final String ADD_BUTTON = "//button[normalize-space()='Add']";

    @FindBy(xpath = EMPLOYEE_INFO_HEADER)
    private WebElement employeeInfoHeader;

    @FindBy(xpath = RESET_BUTTON)
    private WebElement resetButton;

    @FindBy(xpath = SEARCH_BUTTON)
    private WebElement searchButton;

    @FindBy(xpath = ADD_BUTTON)
    private WebElement addButton;

    @Override
    public EmployeeListPage isPageLoaded() {
        waitForPageToLoad();
        wait.until(visibilityOf(employeeInfoHeader));
        return this;
    }
}
