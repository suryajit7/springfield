package com.automation.framework.page.site.saucedemo;

import com.automation.framework.core.annotation.Page;
import com.automation.framework.page.BasePage;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Page
@Getter
@Setter
public class LoginPage extends BasePage {

    private final String LOGIN_LOGO = "//div[@class='login_logo']";
    private final String USERNAME_FIELD = "user-name";
    private final String PASSWORD_FIELD = "password";
    private final String LOGIN_BUTTON = "login-button";

    @Value("${app.saucedemo.url}")
    private String sauceDemoUrl;

    @FindBy(id = USERNAME_FIELD)
    private WebElement username;

    @FindBy(id = PASSWORD_FIELD)
    private WebElement password;

    @FindBy(id = LOGIN_BUTTON)
    private WebElement login;

    public LoginPage goToUrl() {
        goTo(this.sauceDemoUrl);
        return this;
    }


    public LoginPage enterUsername(String username) {
        enterText(By.id(USERNAME_FIELD), username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        enterText(By.id(PASSWORD_FIELD), password);
        return this;
    }

    public LoginPage clickLoginButton() {
        wait.until(visibilityOfElementLocated(By.id(LOGIN_BUTTON))).click();
        return this;
    }


    public LoginPage enterCredentials(String username, String password) {
        waitForPageToLoad();
        enterUsername(username);
        enterPassword(password);
        return this;
    }
}
