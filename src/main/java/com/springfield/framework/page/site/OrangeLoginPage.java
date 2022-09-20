package com.springfield.framework.page.site;

import com.springfield.framework.core.annotation.PageObject;
import com.springfield.framework.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.springfield.framework.page.site.OrangeMenu.LOGIN;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Getter
@PageObject
public class OrangeLoginPage extends BasePage {

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

    public OrangeLoginPage appLogin(String username, String password){
        goTo(LOGIN);
        if (driver.getCurrentUrl().contains(LOGIN.getStrValue())) {
            enterText(usernameField, username);
            enterText(passwordField, password);
            click(loginButton);
        } return this;
    }


    @Override
    public OrangeLoginPage isPageLoaded() {
        waitForPageToLoad();
        wait.until(visibilityOf(orangeHRMLogo));
        wait.until(visibilityOf(usernameField));
        wait.until(visibilityOf(passwordField));
        wait.until(visibilityOf(loginButton));
        return this;
    }
}
