package com.automation.framework.page.crm;

import com.automation.framework.core.config.annotation.Page;
import com.automation.framework.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
public class LoginPage extends BasePage {

    private static final String USERNAME_FIELD = "txtUsername";
    private static final String PASSWORD_FIELD = "txtPassword";
    private static final String LOGIN_BUTTON = "btnLogin";

    @FindBy(id = USERNAME_FIELD)
    private WebElement username;

    @FindBy(id = PASSWORD_FIELD)
    private WebElement password;

    @FindBy(id = LOGIN_BUTTON)
    private WebElement login;

    public void enterUsername(String username) {
        this.username.click();
        this.username.clear();
        this.username.sendKeys(username);
    }

    public void enterPassword(String password) {
        this.password.click();
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void clickLoginButton() {
        this.login.click();
    }


}
