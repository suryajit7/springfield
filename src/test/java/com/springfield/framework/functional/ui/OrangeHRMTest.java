package com.springfield.framework.functional.ui;

import com.springfield.framework.SpringfieldApplicationTests;
import com.springfield.framework.page.site.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class OrangeHRMTest extends SpringfieldApplicationTests {


    @Autowired
    private LoginPage loginPage;

    @BeforeAll
    public void beforeTestExecution(){

        loginPage.goTo("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
                .isPageLoaded()
                .enterText(loginPage.getUsernameField(),"Admin")
                .enterText(loginPage.getPasswordField(),"admin123")
                .click(loginPage.getLoginButton())
                .waitForPageToLoad();
    }

    @Test
    public void login(){

        System.out.println("test");

    }

    @AfterAll
    public void tear(){
        loginPage.getDriver().close();
    }





}
