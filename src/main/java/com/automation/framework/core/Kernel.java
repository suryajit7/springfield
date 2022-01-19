package com.automation.framework.core;

import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.util.SpecBuilder;
import com.automation.framework.util.TokenService;
import com.github.javafaker.Faker;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static com.automation.framework.data.Constants.JASYPT_ENCRYPTOR_KEY;


/**
 * The WebDriver and Wait instances are managed by Spring.
 */
@Component
@DirtiesContext
public class Kernel {

    public Actions actions;

    @LazyAutowired
    public WebDriver driver;

    @LazyAutowired
    public WebDriverWait wait;

    @LazyAutowired
    public FluentWait<WebDriver> fluentWait;

    @LazyAutowired
    public ApplicationContext appCtx;

    @LazyAutowired
    public Faker faker;

    @LazyAutowired
    public Log logger;

    @LazyAutowired
    public TokenService tokenService;

    @LazyAutowired
    public SpecBuilder specBuilder;

    @Value("${wait.default.timeout:50}")
    protected int timeout;

    @Value("${wait.polling.interval:10}")
    protected int pollingInterval;

    @Value("${jasypt.encryptor.secret}")
    private String jasyptSecretValue;

    @PostConstruct
    public void init() {
        System.setProperty(JASYPT_ENCRYPTOR_KEY, jasyptSecretValue);

        if (!isJUnitTest()){
            PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, timeout), this);
            this.actions = new Actions(this.driver);
            this.driver.manage().window().maximize();
        }
    }

    private boolean isJUnitTest() {
        return Arrays.stream(Thread.currentThread().getStackTrace()).anyMatch(test-> test.getClassName().startsWith("org.junit."));
    }

}
