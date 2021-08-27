package com.automation.framework.core.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class BasePage {

    @Autowired
    public WebDriver driver;

    @Autowired
    public WebDriverWait wait;

    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
    }

    public abstract Boolean isLoaded();



}
