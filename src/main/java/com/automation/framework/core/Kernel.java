package com.automation.framework.core;

import com.automation.framework.util.CommonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;


/**
 * The WebDriver and Wait instances are managed by Spring.
 */
public class Kernel {

    protected Log logger;
    protected Actions actions;
    protected CommonUtil commonUtil;
    @Autowired
    protected WebDriver driver;
    @Autowired
    protected WebDriverWait wait;
    @Value("${default.timeout: 50}")
    private int timeout;

    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
        this.actions = new Actions(this.driver);
        this.logger = LogFactory.getLog(getClass());
        this.commonUtil = new CommonUtil();
    }
}