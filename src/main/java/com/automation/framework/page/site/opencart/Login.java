package com.automation.framework.page.site.opencart;

import com.automation.framework.core.annotation.Page;
import com.automation.framework.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Page
@Getter
public class Login extends BasePage {

    private static final String OPENCART_LOGO = "//img[@title='OpenCart']";
    private static final String USERNAME_ID = "input-username";
    private static final String PASSWORD_ID = "input-password";
    private static final String LOGIN_BUTTON = "//button[normalize-space()='Login']";
    private static final String FORGOT_PASSWORD = "//a[normalize-space()='Forgotten Password']";


    @FindBy(xpath = OPENCART_LOGO)
    private WebElement opencartLogo;

    @FindBy(id = USERNAME_ID)
    private WebElement usernameId;

    @FindBy(id = PASSWORD_ID)
    private WebElement passwordId;

    @FindBy(xpath = LOGIN_BUTTON)
    private WebElement loginButton;

    @FindBy(xpath = FORGOT_PASSWORD)
    private WebElement forgotPassword;


    public Login enterUsername() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(USERNAME_ID))).click();
        enterText(By.id(USERNAME_ID), "");
        return this;
    }

    public Login enterPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_ID)));
        this.passwordId.click();
        enterText(By.id(USERNAME_ID), "");
        return this;
    }

    public Login enterUsernameAndPassword() {
        enterUsername();
        enterPassword();
        return this;
    }

    public Login clickLoginButton() {
        wait.until(element -> this.loginButton.isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_BUTTON)));
        this.loginButton.click();
        return this;
    }


}
