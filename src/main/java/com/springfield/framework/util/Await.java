package com.springfield.framework.util;

import com.springfield.framework.core.AwaitInterface;
import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.WebElement;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.awaitility.Awaitility.await;

public class Await {

    public static AwaitInterface elementIsDisplayed = (element) -> getInitializedAwait()
            .until(element::isDisplayed);

    public static AwaitInterface elementIsEnabled = (element) -> getInitializedAwait()
            .until(element::isEnabled);

    public static AwaitInterface elementIsSelected = (element) -> getInitializedAwait()
            .until(element::isSelected);

    public static AwaitInterface elementIsButton = (element) -> getInitializedAwait()
            .until(() -> element.getTagName().equalsIgnoreCase("button") || element.getAttribute("type").equalsIgnoreCase("button"));

    public static WebElement awaitUntil(AwaitInterface method, WebElement element) {
        method.syncUsingAwait(element);
        return element;
    }

    public static ConditionFactory getInitializedAwait() {
        return await()
                .ignoreExceptions()
                .pollDelay(ofMillis(10))
                .atLeast(ofMillis(10))
                .atMost(ofSeconds(180));
    }








}
