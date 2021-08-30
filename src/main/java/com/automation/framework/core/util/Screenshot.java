package com.automation.framework.core.util;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.testng.ITestNGMethod;

@Component
public class Screenshot {

    @Autowired
    private WebDriver driver;

    @Value("${screenshot.dir.path}")
    private String screenshotDirectory;

    public void takeScreenshot(ITestNGMethod currentTestMethod, String identifier) {
        String currentTestMethodName = currentTestMethod.getMethodName().concat("_").concat(identifier);
        PageSnapshot snapshot = Shutterbug.shootPage(this.driver, Capture.FULL_SCROLL, 50, true);
        snapshot.withName(currentTestMethodName.concat(identifier)).save(screenshotDirectory.concat(currentTestMethod.getRealClass().getSimpleName()));
    }
}
