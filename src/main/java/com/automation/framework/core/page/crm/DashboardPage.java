package com.automation.framework.core.page.crm;

import com.automation.framework.core.config.annotation.Page;
import com.automation.framework.core.page.BasePage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Page
public class DashboardPage extends BasePage {

    @Autowired
    private EmployeeDistributionComponent employeeDistribution;

    //TODO: ***Suraj*** Ignore the boilerplate code here for now - will refactor it later
    public List<String> getAllPieChartLabels() {
        return this.employeeDistribution.getAllPieChartLabels();
    }


}
