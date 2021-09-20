package com.automation.framework.module.opencart;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.app.opencart.Dashboard;
import com.automation.framework.page.app.opencart.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Profile("opencart")
public class DashboardTest extends BaseTestNGTest {

    @Value("${app.username:demo}")
    private String username;

    @Value("${app.password:demo}")
    private String password;

    @LazyAutowired
    private Login login;

    @LazyAutowired
    private Dashboard dashboard;

    @BeforeClass
    public void setup() {
        this.login.goTo();
        this.login.enterUsernameAndPassword(username, password);
        this.login.clickLoginButton();
    }

    @Test
    public void verifyDashboard() {
        this.dashboard.selectCountry("in");
    }

}