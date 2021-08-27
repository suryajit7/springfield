package com.automation.framework.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {


    @Bean
    WebDriver getDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @Bean
    public WebDriverWait webDriverWait(WebDriver driver){

        return new WebDriverWait(driver, 10);
    }


}
