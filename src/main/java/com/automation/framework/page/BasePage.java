package com.automation.framework.page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class BasePage {

    protected Log logger;

    @Value("${default.timeout:50}")
    private int timeout;

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
        this.logger = LogFactory.getLog(getClass());
    }


    public void goTo(String url) {
        this.driver.get(url);
    }


}
