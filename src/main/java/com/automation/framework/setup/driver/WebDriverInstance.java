package com.automation.framework.setup.driver;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.automation.framework.core.annotation.ThreadScopeBean;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.automation.framework.data.Constants.BROWSER;
import static com.automation.framework.data.Constants.RUNMODE;
import static org.openqa.selenium.remote.CapabilityType.*;


@LazyConfiguration
@ConditionalOnProperty(name = RUNMODE, havingValue = "local")
public class WebDriverInstance {


    @ThreadScopeBean
    @ConditionalOnProperty(name = BROWSER, havingValue = "chrome")
    WebDriver getChromeInstance() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(configureChromeOptions());
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name = BROWSER, havingValue = "firefox")
    WebDriver getGeckoInstance(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name = BROWSER, havingValue = "edge")
    WebDriver getEdgeInstance(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }


    private ChromeOptions configureChromeOptions() {

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setCapability(ELEMENT_SCROLL_BEHAVIOR, true);
        chromeOptions.setCapability(SUPPORTS_ALERTS, true);
        chromeOptions.setCapability(SUPPORTS_JAVASCRIPT, true);
        chromeOptions.setCapability(SUPPORTS_APPLICATION_CACHE, true);
        chromeOptions.setCapability(ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(ELEMENT_SCROLL_BEHAVIOR, true);
        chromeOptions.setCapability("chrome.switches", Arrays.asList("--disable-extensions"));
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--allow-insecure-localhost");
        chromeOptions.addArguments("disable-gpu");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);

        chromeOptions.setExperimentalOption("prefs", prefs);

        System.setProperty("java.awt.headless", "false");

        return chromeOptions;
    }
}
