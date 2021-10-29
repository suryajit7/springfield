package com.automation.framework.gui.module.crm;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.page.app.hrm.LoginPanelPage;
import com.automation.framework.page.app.hrm.dashboard.DashboardPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTestNGTest {

    @Autowired
    private LoginPanelPage login;

    @Autowired
    private DashboardPage dashboardPage;

    @Test
    public void verifyPieChartLabels() {

        this.login.goTo("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
        this.dashboardPage.getAllPieChartLabels();
    }

}
