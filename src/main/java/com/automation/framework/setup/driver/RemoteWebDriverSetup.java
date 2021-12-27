package com.automation.framework.setup.driver;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.automation.framework.core.annotation.ThreadScopeBean;
import com.browserstack.local.Local;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_VERSION;


@LazyConfiguration
@ConditionalOnProperty(name = "runmode", havingValue = "remote")
public class RemoteWebDriverSetup {


    private Local local;

    @Value("${selenium.grid.url}")
    private URL url;

    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver remoteFirefoxDriver() {
        return new RemoteWebDriver(this.url, DesiredCapabilities.firefox());
    }

 /*   @ThreadScopeBean
    @ConditionalOnMissingBean
    public WebDriver remoteChromeDriver() {
        return new RemoteWebDriver(this.url, DesiredCapabilities.chrome());
    }*/

    @ThreadScopeBean
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
