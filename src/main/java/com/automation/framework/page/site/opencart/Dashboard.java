package com.automation.framework.page.site.opencart;

import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.core.annotation.Page;
import com.automation.framework.page.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
@Getter
@Setter
public class Dashboard extends BasePage {

    private final String MENU_DASHBOARD = "menu-dashboard";
    private final String USERNAME_ID = "input-username";
    private final String PASSWORD_ID = "input-password";
    private final String LOGIN_BUTTON = "//button[normalize-space()='Login']";
    private final String FORGOT_PASSWORD = "//a[normalize-space()='Forgotten Password']";


    @LazyAutowired
    private WorldMap worldMap;

    @FindBy(xpath = MENU_DASHBOARD)
    private WebElement opencartLogo;

    @FindBy(id = USERNAME_ID)
    private WebElement username;

    @FindBy(id = PASSWORD_ID)
    private WebElement password;

    @Step
    public void selectCountryByCode(String countryCode) {
        this.worldMap.selectCountry(countryCode);
    }





}
