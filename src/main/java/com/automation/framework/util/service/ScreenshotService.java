package com.automation.framework.util.service;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.automation.framework.page.BasePage;
import com.automation.framework.util.PathFinder;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.testng.ITestNGMethod;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Lazy
@Service
@Slf4j
public class ScreenshotService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PathFinder pathFinder;

    @Value("${screenshot.dir.path}")
    private String screenshotDirectory;

    public void takeWebPageScreenshot(ITestNGMethod testMethod, String identifier) {
        String currentTestMethodName = testMethod.getMethodName().concat(identifier);

        PageSnapshot snapshot = Shutterbug.shootPage(this.applicationContext.getBean(WebDriver.class), Capture.FULL_SCROLL, 50, true);
        snapshot.withName(currentTestMethodName.concat(identifier))
                .save(screenshotDirectory.concat(testMethod.getRealClass().getSimpleName()));
    }

    public void takeWebElementScreenshot(ITestNGMethod currentTestMethod, String identifier) {
        String currentTestMethodName = currentTestMethod.getMethodName().concat("_").concat(identifier);
        PageSnapshot snapshot = Shutterbug.shootPage(this.applicationContext.getBean(WebDriver.class), Capture.FULL_SCROLL, 50, true);
        snapshot.withName(currentTestMethodName.concat(identifier)).save(screenshotDirectory.concat(currentTestMethod.getRealClass().getSimpleName()));

    }

    public void assertWebPageScreenshots(ITestNGMethod testMethod, String identifier) {
        String currentTestMethodName = testMethod.getMethodName().concat(identifier);

        PageSnapshot snapshot = Shutterbug.shootPage(this.applicationContext.getBean(WebDriver.class), Capture.FULL_SCROLL, 50, true);
        snapshot.withName(currentTestMethodName.concat(identifier.concat("Actual")))
                .save(screenshotDirectory.concat(testMethod.getRealClass().getSimpleName()));
        try {
            BufferedImage expectedImage = ImageIO.read(pathFinder.getFilePathForFile(currentTestMethodName.concat(".png")).toFile());

            snapshot.equalsWithDiff(expectedImage, screenshotDirectory.concat(testMethod.getRealClass().getSimpleName().concat("//").concat(currentTestMethodName).concat("Difference")), 0.0000000000000);
        } catch(IOException e) {
            log.error(e.getMessage());
        }
    }
}
