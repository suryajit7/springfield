package com.springfield.framework.page.site;

import com.springfield.framework.core.annotation.PageObject;
import com.springfield.framework.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
@PageObject
public class AddEmployeePage extends BasePage {

    private final String ADD_EMPLOYEE_HEADER = "//h6[normalize-space()='Add Employee']";
    private final String ADD_EMPLOYEE_IMAGE = "//img[@class='employee-image']";
    private final String EMPLOYEE_FIRST_NAME_INPUT_FIELD = "//input[@placeholder='First Name']";
    private final String EMPLOYEE_MIDDLE_NAME_INPUT_FIELD = "//input[@placeholder='Middle Name']";
    private final String EMPLOYEE_LAST_NAME_INPUT_FIELD = "//input[@placeholder='Last Name']";
    private final String EMPLOYEE_ID_INPUT_FIELD = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']";
    private final String CANCEL_BUTTON = "//button[normalize-space()='Cancel']";
    private final String SAVE_BUTTON = "//button[normalize-space()='Save']";

    @FindBy(xpath = ADD_EMPLOYEE_HEADER)
    private WebElement addEmployeeHeader;

    @FindBy(xpath = ADD_EMPLOYEE_IMAGE)
    private WebElement addEmployeeImage;

    @FindBy(xpath = EMPLOYEE_FIRST_NAME_INPUT_FIELD)
    private WebElement employeeFirstNameInputField;

    @FindBy(xpath = EMPLOYEE_MIDDLE_NAME_INPUT_FIELD)
    private WebElement employeeMiddleNameInputField;

    @FindBy(xpath = EMPLOYEE_LAST_NAME_INPUT_FIELD)
    private WebElement employeeLastNameInputField;

    @FindBy(xpath = EMPLOYEE_ID_INPUT_FIELD)
    private WebElement employeeIDInputField;

    @FindBy(xpath = CANCEL_BUTTON)
    private WebElement cancelButton;

    @FindBy(xpath = SAVE_BUTTON)
    private WebElement saveButton;

    @Override
    public AddEmployeePage isPageLoaded() {
        waitForPageToLoad();
        wait.until(ExpectedConditions.visibilityOf(addEmployeeHeader));
        wait.until(ExpectedConditions.visibilityOf(addEmployeeImage));
        wait.until(ExpectedConditions.visibilityOf(employeeMiddleNameInputField));
        wait.until(ExpectedConditions.visibilityOf(saveButton));
        return this;
    }
}
