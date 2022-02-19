package com.automation.framework.env.driver;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.automation.framework.core.annotation.ThreadScopeBean;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.net.URL;

import static com.automation.framework.data.Constants.RUNMODE;
import static io.appium.java_client.remote.MobileBrowserType.CHROME;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.Platform.ANDROID;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@Slf4j
@LazyConfiguration
public class MobileAppiumDriver {

    @Value("${appium.server.url}")
    protected URL appiumServerUrl;

    @ThreadScopeBean
    @ConditionalOnProperty(name = RUNMODE, havingValue = "mobile")
    public AppiumDriver<MobileElement> getAndroidDriver(){
        log.info("activated");
        return new AppiumDriver(appiumServerUrl, configureDroidCapabilitiesOptions());
    }

    public DesiredCapabilities configureDroidCapabilitiesOptions(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(PLATFORM_NAME, ANDROID);
        capabilities.setCapability(DEVICE_NAME,"Pixel3");
        capabilities.setCapability(AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability(UDID,"emulator-5554");
        capabilities.setCapability(CapabilityType.BROWSER_NAME,"emulator-5554");
        return capabilities;
    }


    public static AppiumDriver<MobileElement> createAndroidDriverForNativeApp(String device_name, String udid, int port, String emulator) {
        try {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capability.setCapability(MobileCapabilityType.DEVICE_NAME, device_name);
            capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2); // Specific to Android
            capability.setCapability(MobileCapabilityType.UDID, udid); // To uniquely identify device
            capability.setCapability(MobileCapabilityType.APP, "getAndroidApkPath()");
            //capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getConfig(ConfigJson.APP_PACKAGE));
            //capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getConfig(ConfigJson.APP_ACTIVITY));
            capability.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, port); // To set different port for each thread - This port is used to communicate with UiAutomator2
            if (emulator.equalsIgnoreCase("yes")) {
                capability.setCapability(AndroidMobileCapabilityType.AVD, device_name);
                //capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, Integer.parseInt(getConfig(ConfigJson.AVD_LAUNCH_TIMEOUT)));
            }
            return new AndroidDriver<>(new URL("getConfig(ConfigJson.APPIUM_URL)"), capability);
        } catch (Exception e) {
            throw new AppiumServerHasNotBeenStartedLocallyException("Failed to initialize driver. Please check the desired capabilities", e);
        }
    }

    public static AppiumDriver<MobileElement> createAndroidDriverForWeb(String device_name, String udid, int port, String emulator) {
        try {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capability.setCapability(MobileCapabilityType.DEVICE_NAME, device_name);
            capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            capability.setCapability(MobileCapabilityType.UDID, udid);
            capability.setCapability(CapabilityType.BROWSER_NAME, CHROME);
            capability.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_PORT, port); // For Web view/Chrome browser to launch the browser on different port
            if (emulator.equalsIgnoreCase("yes")) {
                capability.setCapability(AndroidMobileCapabilityType.AVD, device_name);
                //capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, Integer.parseInt(getConfig(ConfigJson.AVD_LAUNCH_TIMEOUT)));
            }

            return new AndroidDriver<>(new URL("getConfig(ConfigJson.APPIUM_URL)"), capability);
        } catch (Exception e) {
            throw new AppiumServerHasNotBeenStartedLocallyException("Failed to initialize driver. Please check the desired capabilities", e);
        }
    }

    public static AppiumDriver<MobileElement> createIOSDriverForNativeApp(String device_name, String udid, int port) {
        try {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.IOS);
            capability.setCapability(MobileCapabilityType.DEVICE_NAME, device_name);
            capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            capability.setCapability(MobileCapabilityType.UDID, udid);
            capability.setCapability(MobileCapabilityType.APP, "getIosAppPath()");
            //capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, getConfig(ConfigJson.BUNDLE_ID));
            //capability.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, port); // To set different port for each thread - This port is used to communicate with WebDriverAgent driver

            return new IOSDriver<>(new URL("getConfig(ConfigJson.APPIUM_URL)"), capability);
        } catch (Exception e) {
            throw new AppiumServerHasNotBeenStartedLocallyException("Failed to initialize driver. Please check the desired capabilities", e);
        }
    }

    public static AppiumDriver<MobileElement> createIOSDriverForWeb(String device_name, String udid, int port) {
        try {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.IOS);
            capability.setCapability(MobileCapabilityType.DEVICE_NAME, device_name);
            capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            capability.setCapability(MobileCapabilityType.UDID, udid);
            //capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, getConfig(ConfigJson.BUNDLE_ID));
            //capability.setCapability(CapabilityType.BROWSER_NAME, MobileBrowserName.SAFARI);
            capability.setCapability("webkitDebugProxyPort", port); // For web view/Safari browser testing on real device

            return new IOSDriver<>(new URL("getConfig(ConfigJson.APPIUM_URL)"), capability);
        } catch (Exception e) {
            throw new AppiumServerHasNotBeenStartedLocallyException("Failed to initialize driver. Please check the desired capabilities", e);
        }
    }


}
