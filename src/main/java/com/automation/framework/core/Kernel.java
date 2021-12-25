package com.automation.framework.core;

import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.core.config.TokenManager;
import com.automation.framework.util.service.PropertyDecryptService;
import com.github.javafaker.Faker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static com.automation.framework.data.Constants.JASYPT_ENCRYPTOR_KEY;
import static com.automation.framework.data.Constants.JASYPT_ENCRYPTOR_VALUE;


/**
 * The WebDriver and Wait instances are managed by Spring.
 */
public class Kernel {

    protected Log logger;
    protected Actions actions;
    public PropertyDecryptService decryptService;

    @LazyAutowired
    protected WebDriver driver;

    @LazyAutowired
    protected WebDriverWait wait;

    @LazyAutowired
    protected ApplicationContext appCtx;

    @LazyAutowired
    protected Faker faker;

    @LazyAutowired
    public TokenManager tokenManager;

    @Value("${default.timeout: 50}")
    protected int timeout;

    @PostConstruct
    protected void init() {
        System.setProperty(JASYPT_ENCRYPTOR_KEY, JASYPT_ENCRYPTOR_VALUE);

        this.logger = LogFactory.getLog(getClass());
        this.decryptService = appCtx.getBean(PropertyDecryptService.class);

        if (!isJUnitTest()){
            PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, timeout), this);
            this.driver.manage().window().maximize();
            this.actions = new Actions(this.driver);
        }
    }

    public boolean isJUnitTest() {
        return Arrays.stream(Thread.currentThread().getStackTrace()).anyMatch(test-> test.getClassName().startsWith("org.junit."));
    }
}
