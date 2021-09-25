package com.automation.framework.module.crm;

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

    @Value("${app.crm.url}")
    private String crmURL;

    @Value("${app.crm.username}")
    private String username;

    @Value("${app.crm.pwd}")
    private String pwd;

    @Test
    public void verifyPieChartLabels() {

        this.login.goTo("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
        this.dashboardPage.getAllPieChartLabels();
    }

}
