package com.automation.framework.module.crm;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.page.app.crm.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

public class OrangeCRMLoginTest extends BaseTestNGTest {

    @Autowired
    private LoginPage loginPage;

    @Value("${app.crm.url}")
    private String crmURL;

    @Value("${app.crm.username}")
    private String username;

    @Value("${app.crm.pwd}")
    private String pwd;

    @Test
    public void verifyLoginPage() {
        this.loginPage.goTo(this.crmURL);
        this.loginPage.enterUsername(this.username);
        this.loginPage.enterPassword(this.pwd);
        this.loginPage.clickLoginButton();
    }


}
