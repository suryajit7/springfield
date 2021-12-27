package com.automation.framework.page.app.hrm;

import com.automation.framework.core.annotation.Page;
import com.automation.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Page
public class LoginPanelPage extends BasePage {

    private final String USERNAME_FIELD = "txtUsername";
    private final String PASSWORD_FIELD = "txtPassword";
    private final String LOGIN_BUTTON = "btnLogin";

    @FindBy(id = USERNAME_FIELD)
    private WebElement username;

    @FindBy(id = PASSWORD_FIELD)
    private WebElement password;

    @FindBy(id = LOGIN_BUTTON)
    private WebElement login;

    public void enterUsername(String username) {
        enterText(By.id(USERNAME_FIELD), username);
    }

    public void enterPassword(String password) {
        enterText(By.id(PASSWORD_FIELD), password);
    }

    public void clickLoginButton() {
        wait.until(visibilityOfElementLocated(By.id(LOGIN_BUTTON))).click();
    }


    public void enterCredentials(String username) {
        enterUsername(username);
        enterPassword(decryptService.getOrangeHrmPassword());
    }
}
