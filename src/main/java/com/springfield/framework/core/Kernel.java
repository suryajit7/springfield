package com.springfield.framework.core;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Getter
public class Kernel {

    @Autowired
    public WebDriver driver;

    @Autowired
    public WebDriverWait wait;


/*    @Autowired
    public Log logger;*/

    public Actions actions;

    @PostConstruct
    public void init() {

        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 30), this);
        this.actions = new Actions(this.driver);
        this.driver.manage().window().maximize();
    }
}
