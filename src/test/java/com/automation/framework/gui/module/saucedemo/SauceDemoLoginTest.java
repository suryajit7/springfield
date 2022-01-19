package com.automation.framework.gui.module.saucedemo;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.app.saucedemo.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

@Epic("SauceLabs")
@Feature("Login")
public class SauceDemoLoginTest extends BaseTestNGTest {

    @LazyAutowired
    private LoginPage login;

    @Value("${app.saucedemo.username}")
    private String username;

    @AfterClass
    public void closeBrowser(){
        this.login.close();
    }

    @Test
    public void verifyLoginPage() {

        this.login.goToUrl()
                .enterCredentials(this.username, decryptService.getSauceDemoPassword())
                .clickLoginButton();
    }

}
