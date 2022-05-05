package com.automation.framework.env.driver;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.automation.framework.core.annotation.ThreadScopeBean;
import com.browserstack.local.Local;
import lombok.SneakyThrows;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.automation.framework.data.Constants.BROWSER;
import static com.automation.framework.data.Constants.RUNMODE;
import static org.openqa.selenium.remote.CapabilityType.*;


@LazyConfiguration
@ConditionalOnProperty(name = RUNMODE, havingValue = "remote")
public class RemoteWebDriverSetup {

    protected String host;
    private Local local;

    @Value("${selenium.grid.url}")
    private URL url;

    @SneakyThrows
    @ThreadScopeBean
    @ConditionalOnMissingBean
    public WebDriver remoteChromeDriver() {
        MutableCapabilities dc = new ChromeOptions();
        dc.merge(getChromeOptions());

        host = System.getenv("HUB_HOST") != null ? System.getenv("HUB_HOST") : "localhost";

       return new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), dc);
    }


    @ThreadScopeBean
    @ConditionalOnProperty(name = BROWSER, havingValue = "firefox")
    public WebDriver remoteFirefoxDriver() {
        return new RemoteWebDriver(this.url, DesiredCapabilities.firefox());
    }


    @ThreadScopeBean
    @ConditionalOnProperty(name = BROWSER, havingValue = "browserstack")
    @ConditionalOnMissingBean
    public WebDriver remoteBrowserstack() throws MalformedURLException {
        return new RemoteWebDriver(new URL("https://uchihasuryajit_rIUiWy:HXYBWT4pHu8sCrrXTQNz@hub.browserstack.com/wd/hub"), configureCap());
    }

/*    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    public WebDriver remoteChromeDriver() throws MalformedURLException {
        return new RemoteWebDriver(
                new URL("https://uchihasuryajit_rIUiWy:HXYBWT4pHu8sCrrXTQNz@hub.browserstack.com/wd/hub"), configureCap());
    }*/


/*
    @ThreadScopeBean
    @Primary
    //@BeforeSuite(alwaysRun = true)
    @Parameters(value = { "config", "environment" })
    @SuppressWarnings("unchecked")
    public WebDriver setUp(String config_file, String environment) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new PathFinder("src/test/resources/conf/" + config_file));
        JSONObject envs = (JSONObject) config.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (capabilities.getCapability(pair.getKey().toString()) == null) {
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = (String) config.get("user");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) config.get("key");
        }

        if (capabilities.getCapability("browserstack.local") != null
                && capabilities.getCapability("browserstack.local") == "true") {
            local = new Local();
            Map<String, String> options = new HashMap<String, String>();
            options.put("key", accessKey);
            local.start(options);
        }

        return new RemoteWebDriver(
                new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"), capabilities);
    }
*/

    private ChromeOptions getChromeOptions() {
        Proxy proxy = new Proxy();
        proxy.setAutodetect(true);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(ELEMENT_SCROLL_BEHAVIOR, true);
        chromeOptions.setCapability(SUPPORTS_ALERTS, true);
        chromeOptions.setCapability(SUPPORTS_JAVASCRIPT, true);
        chromeOptions.setCapability(SUPPORTS_APPLICATION_CACHE, true);
        chromeOptions.setCapability(ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(ELEMENT_SCROLL_BEHAVIOR, true);
        chromeOptions.setCapability(PROXY, proxy);
        chromeOptions.setCapability(BROWSER_NAME, "chrome");
        chromeOptions.setCapability("chrome.switches", Arrays.asList("--disable-extensions"));
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--allow-insecure-localhost");
        chromeOptions.addArguments("--no-default-browser-check");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        return chromeOptions;
    }

    public DesiredCapabilities configureCap() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "browserstack-build-1");
        capabilities.setCapability("name", "parallel_test");
        capabilities.setCapability("browserstack.debug", true);

        capabilities.setCapability(BROWSER_NAME, "Chrome");
        //capabilities.setCapability(BROWSER_NAME, "firefox");
        capabilities.setCapability(BROWSER_VERSION, "latest");

        //Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
        capabilities.setPlatform(Platform.WIN8);


        return capabilities;
    }

/*    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver remoteGeckoDriver() {
        return new RemoteWebDriver(this.url, DesiredCapabilities.firefox());
    }*/


}
