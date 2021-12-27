package com.automation.framework.page.app.hrm.admin.usermanagement;

import com.automation.framework.core.annotation.Page;
import com.automation.framework.page.BasePage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Page
@Getter @Setter @NoArgsConstructor
public class Users extends BasePage {

    private final String USERNAME_FIELD = "searchSystemUser_userName";
    private final String USER_TYPE = "searchSystemUser_userType";
    private final String EMPLOYEE_NAME = "searchSystemUser_employeeName_empName";
    private final String USER_STATUS = "searchSystemUser_status";
    private final String SEARCH_BUTTON = "searchBtn";
    private final String RESET_BUTTON = "resetBtn";

    @FindBy(id = USERNAME_FIELD)
    private WebElement username;

    @FindBy(id = USER_TYPE)
    private WebElement userType;

    @FindBy(id = EMPLOYEE_NAME)
    private WebElement employeeName;

    @FindBy(id = USER_STATUS)
    private WebElement userStatus;

    @FindBy(id = SEARCH_BUTTON)
    private WebElement searchButton;

    @FindBy(id = RESET_BUTTON)
    private WebElement resetButton;


    public void enterUsername(String username) {
        wait.until(visibilityOfElementLocated(By.id(USERNAME_FIELD)));
        enterText(By.id(USERNAME_FIELD), username);
    }

    public void selectUserType(UserRole userRole) {
        wait.until(visibilityOfElementLocated(By.id(USER_TYPE)));
        this.userType.click();
        selectByVisibleTextInDropdown(this.userType, userRole.getStringValue());
    }

    public void enterEmployeeName(String employeeName) {
        wait.until(visibilityOfElementLocated(By.id(EMPLOYEE_NAME)));
        enterText(By.id(EMPLOYEE_NAME), employeeName);
    }

    public void selectUserStatus(Status status) {
        wait.until(visibilityOfElementLocated(By.id(USER_STATUS)));
        this.userStatus.click();
        selectByVisibleTextInDropdown(this.userStatus, status.getStringValue());
    }

    public void clickSearchButton() {
        wait.until(visibilityOfElementLocated(By.id(SEARCH_BUTTON)));
        this.searchButton.click();
    }

    public void clickResetButton() {
        wait.until(visibilityOfElementLocated(By.id(RESET_BUTTON)));
        this.resetButton.click();
    }


}
