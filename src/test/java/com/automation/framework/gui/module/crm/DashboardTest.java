package com.automation.framework.gui.module.crm;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.page.app.hrm.LoginPanelPage;
import com.automation.framework.page.app.hrm.dashboard.DashboardPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTestNGTest {

    @Autowired
    private LoginPanelPage login;

    @Autowired
    private DashboardPage dashboardPage;

    @Value("${app.hrm.url}")
    private String hrmUrl;

    @Value("${app.hrm.username}")
    private String username;

    @Test
    public void verifyLoginPage() {

        this.login.goTo(this.hrmUrl);
        this.login.enterCredentials(this.username);
        this.login.clickLoginButton();
        this.dashboardPage.getAllPieChartLabels();
    }

}
