package com.automation.framework.core.pages.window;

import com.automation.framework.core.config.annotation.Page;
import com.automation.framework.core.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
public class PageA extends BasePage {

    @FindBy(id = "area")
    private WebElement textArea;

    public void addToArea(final String msg) {
        this.textArea.sendKeys(msg);
    }


}
