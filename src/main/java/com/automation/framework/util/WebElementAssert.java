package com.automation.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;


/**
 * Custom assertions suitable for WebElements.
 */
@Slf4j
public class WebElementAssert extends AbstractAssert<WebElementAssert, WebElement> {

    public WebElementAssert(WebElement webElement) {
        super(webElement, WebElementAssert.class);
    }

    public static WebElementAssert assertThat(WebElement element) {
        return new WebElementAssert(element);
    }

    public WebElementAssert isDisplayed() {
        isNotNull();
        if (!actual.isDisplayed()) {
            log.error("Expected element is NOT displayed.");
        }
        return this;
    }

    public WebElementAssert isEnabled() {
        isNotNull();
        if (!actual.isEnabled()) {
            log.error("Expected element is NOT enabled.");
        }
        return this;
    }

    public WebElementAssert isButton() {
        isNotNull();
        boolean isButton = actual.getTagName().equalsIgnoreCase("button") || actual.getAttribute("type").equalsIgnoreCase("button");

        if (!isButton) {
            log.error("Expected element is NOT button.");
        }
        return this;
    }

    public WebElementAssert hasAttributeValue(String attribute, String value) {
        isNotNull();
        if (!actual.getAttribute(attribute).equals(value)) {
            log.error("Expected element doesn't have attribute <%s> value as <%s>.", attribute, value);
        }
        return this;
    }

}