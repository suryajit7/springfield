package com.automation.framework.util.helper;

import com.automation.framework.core.annotation.Window;
import com.automation.framework.util.service.WindowSwitchService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WindowSwitchHelper {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WindowSwitchService switchService;

    @Before("@target(window) && within(com.automation.framework.core..*)")
    public void before(Window window) {
        this.switchService.switchByTitle(window.value());
    }

    @After("@target(window) && within(com.automation.framework.core..*)")
    public void after(Window window) {
        this.switchService.switchByIndex(0);
    }
}
