package com.automation.framework.core.config.aop;

import com.automation.framework.core.config.annotation.Screenshot;
import com.automation.framework.util.ScreenshotService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.automation.framework.reporting.TestExecutionListener.getTestMethod;

@Aspect
@Component
public class ScreenshotHelper {

    @Autowired
    private ScreenshotService screenshotService;

    @After("@annotation(screenshot)")
    public void after(Screenshot screenshot) throws IOException {
        //TODO: add some kind of identifier later as method name etc. so screenshots have good name
        this.screenshotService.takeScreenshot(getTestMethod(), "pass method name");
    }
}
