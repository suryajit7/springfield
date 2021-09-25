package com.automation.framework.page.app.hrm.dashboard;

import com.automation.framework.core.annotation.PageComponent;
import com.automation.framework.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@PageComponent
public class EmployeeDistributionComponent extends BasePage {

    private static final String PIE_CHART = "//span[contains(@id,'pieLabel')]";

    @FindBy(xpath = PIE_CHART)
    private List<WebElement> pieChartLabels;

    public List<String> getAllPieChartLabels() {
        return this.pieChartLabels.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


}
