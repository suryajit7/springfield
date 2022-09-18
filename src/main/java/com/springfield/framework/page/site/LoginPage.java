package com.springfield.framework.page.site;

import com.springfield.framework.core.annotation.Page;
import com.springfield.framework.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.springfield.framework.util.Await.awaitUntil;
import static com.springfield.framework.util.Await.elementIsDisplayed;

@Page
@Getter
public class LoginPage extends BasePage {

    private final String ORANGE_HRM_LOGO = "//img[@alt='company-branding']";
    private final String USERNAME_FIELD = "//input[@placeholder='Username']";
    private final String PASSWORD_FIELD = "//input[@placeholder='Password']";
    private final String LOGIN_BUTTON = "//button[normalize-space()='Login']";
    private final String FORGOT_PASSWORD = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']";

    @FindBy(xpath = ORANGE_HRM_LOGO)
    private WebElement orangeHRMLogo;

    @FindBy(xpath = USERNAME_FIELD)
    private WebElement usernameField;

    @FindBy(xpath = PASSWORD_FIELD)
    private WebElement passwordField;

    @FindBy(xpath = LOGIN_BUTTON)
    private WebElement loginButton;

    @FindBy(xpath = FORGOT_PASSWORD)
    private WebElement forgotPasswordLink;


    @Override
    public LoginPage isPageLoaded() {
        waitForPageToLoad();
        awaitUntil(elementIsDisplayed, orangeHRMLogo);
        awaitUntil(elementIsDisplayed, usernameField);
        awaitUntil(elementIsDisplayed, passwordField);
        return this;
    }
}
