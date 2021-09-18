package com.automation.framework.core.page.crm;

import com.automation.framework.core.config.annotation.PageComponent;
import com.automation.framework.core.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@PageComponent
public class EmployeeDistributionComponent extends BasePage {

    private static final String PIE_CHART = "//span[contains(@id,'pieLabel')]";

    @FindBy(xpath = PIE_CHART)
    private List<WebElement> piechartLabels;

    public List<String> getAllPieChartLabels() {
        return this.piechartLabels.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


}
