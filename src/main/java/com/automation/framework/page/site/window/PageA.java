package com.automation.framework.page.site.window;

import com.automation.framework.core.annotation.Window;
import com.automation.framework.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Window("Page A")
public class PageA extends BasePage {

    @FindBy(id = "area")
    private WebElement textArea;

    public void addToArea(final String msg) {
        this.textArea.sendKeys(msg);
    }


}
