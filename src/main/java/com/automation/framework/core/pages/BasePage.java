package com.automation.framework.core.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class BasePage {

    @Value("${default.timeout:50}")
    private int timeout;

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

    @Autowired
    protected Actions actions;

    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
    }

    //public Boolean isLoaded();



}
