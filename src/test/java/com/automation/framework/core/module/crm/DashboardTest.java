package com.automation.framework.core.module.crm;

import com.automation.framework.core.BaseTestNGTest;
import com.automation.framework.core.page.crm.DashboardPage;
import com.automation.framework.core.page.crm.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTestNGTest {

    @Autowired
    private LoginPage loginPage;

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

        this.loginPage.goTo("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
        this.dashboardPage.getAllPieChartLabels();
    }

}
