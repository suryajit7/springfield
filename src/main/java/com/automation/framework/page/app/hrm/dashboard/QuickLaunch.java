package com.automation.framework.page.app.hrm.dashboard;

import com.automation.framework.core.annotation.PageComponent;
import com.automation.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@PageComponent
public class QuickLaunch extends BasePage {

    private final String QUICK_LAUNCH_MENU = "//div[@class='quickLaunge']/child::a/child::span";

    @FindBy(xpath = QUICK_LAUNCH_MENU)
    private List<WebElement> quickLaunchMenu;

    public void selectQuickLaunchMenu(QuickLaunchOption option) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(QUICK_LAUNCH_MENU)));
        quickLaunchMenu.stream()
                .filter(menu -> menu.isDisplayed() && menu.getText().equalsIgnoreCase(option.name()))
                .findFirst().get().click();
    }


}