package com.springfield.framework.core.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WindowSwitchAspect {

    @Autowired
    private ApplicationContext applicationContext;

    //TODO
/*    @Autowired
    private WindowSwitchService switchService;

    @Before("@target(window) && within(com.automation.framework.core..*)")
    public void before(Window window) {
        this.switchService.switchByTitle(window.value());
    }

    @After("@target(window) && within(com.automation.framework.core..*)")
    public void after(Window window) {
        this.switchService.switchByIndex(0);
    }*/
}
