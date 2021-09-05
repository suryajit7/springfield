package com.automation.framework.core.pages.window;

import com.automation.framework.core.config.annotation.Page;
import com.automation.framework.core.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Page
public class MainPage extends BasePage {


    @FindBy(tagName = "a")
    private List<WebElement> links;

    public void goTo() {
        this.driver.get("https://vins-udemy.s3.amazonaws.com/ds/window/main.html");
    }

    public void launchAllWindows() {
        for (int i = 0; i < links.size(); i++) {
            links.get(i).click();
        }
    }

}
