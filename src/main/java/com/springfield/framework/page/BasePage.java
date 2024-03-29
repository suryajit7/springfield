package com.springfield.framework.page;

import com.springfield.framework.core.Kernel;
import com.springfield.framework.core.annotation.LazyAutowired;
import com.springfield.framework.core.annotation.PageObject;
import com.springfield.framework.page.site.OrangeMenu;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.springfield.framework.data.Constants.ORANGE_HRM_URL;
import static com.springfield.framework.data.Constants.WEBDRIVER_EXCEPTION_LIST;
import static com.springfield.framework.util.Await.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@Getter
@PageObject
public abstract class BasePage extends Kernel {

    @LazyAutowired
    public RemoteWebDriver driver;

    @LazyAutowired
    public WebDriverWait wait;

    public Actions actions;

    public abstract BasePage isPageLoaded();

    @PostConstruct
    public void init() {

        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 30), this);
        this.actions = new Actions(this.driver);
        this.driver.manage().window().maximize();
    }


    public BasePage goTo(OrangeMenu menuOption) {
        this.driver.get(ORANGE_HRM_URL.concat(menuOption.getStrValue()));
        waitForPageToLoad();
        //logger.info("URL loaded: ".concat(url));
        return this;
    }


    public WebElement scrollToElement(WebElement element){

        waitForPageToLoad();

        awaitUntil(elementIsDisplayed, element);
        actions.scrollToElement(element).perform();

        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\",inline: \"center\",behavior: \"smooth\"});", element);

        getInitializedAwait()
                .until(() -> isElementVisibleInViewport(element));

        wait.until(visibilityOf(element));
        waitForPageToLoad();

        //logger.info(("Browser scrolled for element:").concat(element.getText()));
        return element;
    }


    public Boolean isElementVisibleInViewport(WebElement element) {
        return (Boolean)((JavascriptExecutor) this.driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , element);
    }


    public BasePage switchToActiveElement() {
        waitForPageToLoad();
        this.driver.switchTo().activeElement();
        //logger.info("Switched to active WebElement.");
        return this;
    }


    public BasePage click(WebElement element) {
        wait.until(visibilityOf(element)).click();
        //logger.info(("WebElement clicked: ").concat(element.getText()));
        return this;
    }

    public BasePage click(By locator) {
        wait.until(visibilityOfElementLocated(locator)).click();
        //logger.info(("WebElement clicked: ").concat(element.getText()));
        return this;
    }


    public BasePage enterText(WebElement element, String text) {
        wait.until(visibilityOf(element)).click();
        element.clear();
        element.sendKeys(text);
        //logger.info(("Text entered: ").concat(text.contains("pass") ? ("App Password") : (text)));
        return this;
    }


    public BasePage refreshPage() {
        this.driver.navigate().refresh();
        waitForPageToLoad();
        //logger.info("Page is refreshed.");
        return this;
    }


    public BasePage waitForPageToLoad() {
        //logger.info("Waiting for the whole Page to load.");
        wait.until((ExpectedCondition) webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString().equalsIgnoreCase("complete")
                || ((Boolean) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0")));
        return this;
    }


    public Boolean isElementLoaded(By locator){
        waitForPageToLoad();
        return !this.driver.findElements(locator).isEmpty();
    }

    public WebElement waitUntilStaleElementIsAttachedToDOM(By locator) {
        try {
            wait.ignoreAll(WEBDRIVER_EXCEPTION_LIST)
                    .withMessage("Elements is Not available.")
                    .until(refreshed(visibilityOfAllElements(this.driver.findElements(locator))));
        } catch (WebDriverException exception) {
            //logger.info(exception.getMessage());
        } return this.driver.findElement(locator);
    }



    public BasePage moveToElementAndClick(WebElement webelement) {
        this.actions.click(webelement);
        //logger.info("Moved to WebElement and clicked: ".concat(webelement.getText()));
        return this;
    }

    public BasePage moveToElementAndClick(List<WebElement> elementList) {
        for (WebElement element : elementList) {
            wait.until(visibilityOf(element));
            this.actions.click(element);
            //logger.info("Moved to WebElement and clicked: ".concat(element.getText()));
        } return this;
    }


}
