package com.springfield.framework.core.config;

import com.springfield.framework.core.annotation.LazyConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.List;

import static java.time.Duration.ofSeconds;

@Slf4j
@LazyConfiguration
public class WaitInstance {

    @Value("${default.timeout:30}")
    private int timeout;

    @Value("${wait.polling.interval:10}")
    protected int pollingInterval;

    private final List<Class<? extends WebDriverException>> exceptionList = List.of(NoSuchWindowException.class, NoSuchFrameException.class, NoAlertPresentException.class,
            InvalidSelectorException.class, TimeoutException.class, NoSuchSessionException.class, StaleElementReferenceException.class);

    @Bean
    @ConditionalOnMissingBean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebDriverWait getWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, ofSeconds(30));
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FluentWait<WebDriver> getFluentWaitInstance(WebDriver driver) {
        return new FluentWait<WebDriver>(driver)
                .withTimeout(ofSeconds(timeout))
                .pollingEvery(ofSeconds(pollingInterval))
                .ignoreAll(exceptionList);
    }


}
