package com.automation.framework.page.app.opencart;

import com.automation.framework.core.annotation.Page;
import com.automation.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Page
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


    public void enterUsername() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(USERNAME_ID)));
        this.usernameId.click();
        enterText(this.usernameId, decryptService.getOpencartUsername());
    }

    public void enterPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_ID)));
        this.passwordId.click();
        enterText(this.passwordId, decryptService.getOpencartPassword());
    }

    public void enterUsernameAndPassword() {
        enterUsername();
        enterPassword();
    }

    public void clickLoginButton() {
        wait.until(element -> this.loginButton.isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_BUTTON)));
        this.loginButton.click();
    }


}
