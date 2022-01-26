package com.automation.framework.env.driver;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.automation.framework.core.annotation.ThreadScopeBean;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;

@Slf4j
@LazyConfiguration
public class AppiumDriverFactory {

    @Value("${appium.server.url}")
    protected URL appiumServerUrl;

    @Value("${android.app.dir.path}")
    protected URL appiumApp;

    @ThreadScopeBean
    @ConditionalOnMissingBean
    public WebDriver getAndroidDriver(){
        log.info("activated");
        return new AppiumDriver(appiumServerUrl, configureDroidCapabilitiesOptions());
    }

    public DesiredCapabilities configureDroidCapabilitiesOptions(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(PLATFORM_NAME,"Android");
        capabilities.setCapability(DEVICE_NAME,"Pixel3");
        capabilities.setCapability(AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability(UDID,"emulator-5554");
        capabilities.setCapability(APP,"C:\\apk\\ApiDemos-debug.apk");
        return capabilities;
    }
}
