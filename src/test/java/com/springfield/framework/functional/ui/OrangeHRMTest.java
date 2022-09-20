package com.springfield.framework.functional.ui;

import com.springfield.framework.SpringfieldApplicationTests;
import com.springfield.framework.core.annotation.LazyAutowired;
import com.springfield.framework.page.site.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;


public class OrangeHRMTest extends SpringfieldApplicationTests {


    @LazyAutowired
    private LoginPage loginPage;

    @Test
    public void login(){

        loginPage.goTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
                .isPageLoaded()
                .enterText(loginPage.getUsernameField(),"Admin")
                .enterText(loginPage.getPasswordField(),"admin123")
                .click(loginPage.getLoginButton())
                .waitForPageToLoad();

    }

    @AfterAll
    public void tear(){
        loginPage.getDriver().close();
    }





}
