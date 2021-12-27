package com.automation.framework.util;

import com.automation.framework.core.annotation.LazyService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Slf4j
@LazyService
public class WindowSwitchService {

    @Autowired
    private ApplicationContext applicationContext;

    public void switchByTitle(final String title) {
        WebDriver driver = this.applicationContext.getBean(WebDriver.class);
        driver.getWindowHandles().stream()
                .map(handle -> driver.switchTo().window(handle).getTitle())
                .filter(t -> t.startsWith(title))
                .findFirst()
                .orElseThrow(() -> {
                    throw new RuntimeException("No such window");
                });
        log.info("Window switched to: ".concat(title));
    }

    public void switchByIndex(final int index) {
        WebDriver driver = this.applicationContext.getBean(WebDriver.class);
        String[] handles = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(handles[index]);
        log.info("Window switched to tab at index: ".concat(String.valueOf(index)));
    }
}