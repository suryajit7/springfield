package com.automation.framework.core;

import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.util.PropertyDecryptService;
import com.github.javafaker.Faker;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;


/**
 * The WebDriver and Wait instances are managed by Spring.
 */
public class Kernel {


    public PropertyDecryptService decryptService;

/*    @LazyAutowired
    public AppiumDriver<MobileElement> appiumDriver;*/

    @LazyAutowired
    public ApplicationContext appCtx;

    @LazyAutowired
    public Faker faker;

    @LazyAutowired
    public Log logger;

    @LazyAutowired
    public SpecBuilder specBuilder;

    @Value("${wait.default.timeout:50}")
    protected int timeout;

    @Value("${wait.polling.interval:10}")
    protected int pollingInterval;

    @Value("${jasypt.encryptor.secret}")
    protected String jasyptSecretValue;


    private boolean initializePageFactory() {
        return Arrays.stream(Thread.currentThread().getStackTrace()).anyMatch(test-> test.getClassName().contains("junit"));
    }

}
