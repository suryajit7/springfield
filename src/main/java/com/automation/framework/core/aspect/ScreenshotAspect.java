package com.automation.framework.core.aspect;

import com.automation.framework.core.annotation.Screenshot;
import com.automation.framework.util.ScreenshotService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.automation.framework.util.TestExecutionListener.getTestMethod;

@Aspect
@Component
public class ScreenshotAspect {

    @Autowired
    private ScreenshotService screenshotService;

    @After("@annotation(screenshot)")
    public void after(Screenshot screenshot){
        this.screenshotService.takeWebPageScreenshot(getTestMethod(),"pass method name");
    }
}
