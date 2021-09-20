package com.automation.framework.page.window;

import com.automation.framework.core.config.annotation.Screenshot;
import com.automation.framework.core.config.annotation.Window;
import com.automation.framework.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Window
public class MainPage extends BasePage {


    @FindBy(tagName = "a")
    private List<WebElement> links;

    public void goTo() {
        this.driver.get("https://vins-udemy.s3.amazonaws.com/ds/window/main.html");
    }

    @Screenshot
    public void launchAllWindows() {
        for (int i = 0; i < links.size(); i++) {
            links.get(i).click();
        }
    }

}
