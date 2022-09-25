package com.springfield.framework.env.driver;

import com.springfield.framework.core.annotation.ThreadScopeBean;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.springfield.framework.data.Constants.WEBDRIVER_RUNMODE;
import static com.springfield.framework.util.Await.getInitializedAwait;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.util.Collections.singletonList;

@Configuration
public class RemoteWebDriverSetup {

    protected String host;

    @ThreadScopeBean
    @ConditionalOnProperty(name = WEBDRIVER_RUNMODE, havingValue = "remote")
    public RemoteWebDriver getRemoteWebDriver() throws MalformedURLException {

        host = System.getenv("HUB_HOST") != null ? System.getenv("HUB_HOST") : "localhost";

        getInitializedAwait()
                .until(() -> getGridAvailability(host));
        return new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), getCapabilities());
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name = WEBDRIVER_RUNMODE, havingValue = "cloud")
    public RemoteWebDriver remoteChromeDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("https://uchihasuryajit_rIUiWy:HXYBWT4pHu8sCrrXTQNz@hub.browserstack.com/wd/hub"), getCapabilities());
    }

    private Capabilities getCapabilities() {

        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        //chromeOptions.setCapability("se:name", getTestResult().getTestClass().getRealClass().getSimpleName());
        chromeOptions.setExperimentalOption("excludeSwitches", singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }

    private Boolean getGridAvailability(String host){
        return given().contentType(JSON)
                .when().get(("http://").concat(host).concat(":4444/wd/hub/status"))
                .then().extract().response().path("value.message")
                .toString().equalsIgnoreCase("Selenium Grid ready.");
    }
}
