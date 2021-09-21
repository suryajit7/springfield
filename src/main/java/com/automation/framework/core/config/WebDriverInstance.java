package com.automation.framework.core.config;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.automation.framework.core.annotation.ThreadScopeBean;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@LazyConfiguration
@ConditionalOnProperty(name = "runmode", havingValue = "local")
public class WebDriverInstance {


    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    WebDriver getChromeInstance() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    WebDriver getGeckoInstance(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    WebDriver getEdgeInstance(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }



    private ChromeOptions configureChromeOptions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
        chromeOptions.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
        chromeOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        chromeOptions.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
        //chromeOptions.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        chromeOptions.setCapability("chrome.switches", Arrays.asList("--disable-extensions"));
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--allow-insecure-localhost");
        chromeOptions.addArguments("disable-gpu");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        chromeOptions.setExperimentalOption("prefs", prefs);
        return chromeOptions;
    }
}
