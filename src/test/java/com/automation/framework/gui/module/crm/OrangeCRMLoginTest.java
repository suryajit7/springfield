package com.automation.framework.gui.module.crm;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.page.app.hrm.LoginPanelPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

public class OrangeCRMLoginTest extends BaseTestNGTest {

    @Autowired
    private LoginPanelPage login;

    @Value("${app.crm.url}")
    private String crmURL;

    @Value("${app.crm.username}")
    private String username;

    @Value("${app.crm.pwd}")
    private String pwd;

    @Test
    public void verifyLoginPage() {
        this.login.goTo(this.crmURL);
        this.login.enterUsername(this.username);
        this.login.enterPassword(this.pwd);
        this.login.clickLoginButton();
    }


}
