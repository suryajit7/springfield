package com.automation.framework.core.config;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.automation.framework.core.annotation.ThreadScopeBean;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.net.MalformedURLException;
import java.net.URL;


@LazyConfiguration
@ConditionalOnProperty(name = "runmode", havingValue = "remote")
public class RemoteWebDriverConfig {

    @Value("${selenium.grid.url}")
    private URL url;

    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    public WebDriver remoteChromeDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("https://suryajitbedage_JSZfG6:4pzbxpfYr34sYBzwEe1L@hub.browserstack.com/wd/hub"), configureCap());
    }

    public DesiredCapabilities configureCap() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
        capabilities.setPlatform(Platform.WIN10);
        capabilities.setBrowserName("firefox");
        //capabilities.setVersion("38");
        capabilities.setCapability("browserstack.debug", "true");

        return capabilities;
    }

/*    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver remoteGeckoDriver() {
        return new RemoteWebDriver(this.url, DesiredCapabilities.firefox());
    }*/


}
