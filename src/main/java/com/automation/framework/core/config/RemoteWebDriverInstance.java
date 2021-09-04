package com.automation.framework.core.config;

import com.automation.framework.core.config.annotations.LazyConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.net.URL;

@Profile("remote")
@LazyConfiguration
public class RemoteWebDriverInstance {

    @Value("${selenium.grid.url}")
    private URL url;

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    public WebDriver remoteChromeDriver() {
        return new RemoteWebDriver(this.url, DesiredCapabilities.chrome());
    }

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver remoteGeckoDriver() {
        return new RemoteWebDriver(this.url, DesiredCapabilities.firefox());
    }


}
