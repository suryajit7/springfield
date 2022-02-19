package com.automation.framework.page;

import com.automation.framework.core.Kernel;
import com.automation.framework.core.annotation.Page;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.automation.framework.data.Constants.BLANK;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@Page
@Getter
public class BasePage extends Kernel {

    protected final List<Class<? extends WebDriverException>> exceptionList = List.of(NoSuchWindowException.class, NoSuchFrameException.class, NoAlertPresentException.class, InvalidSelectorException.class, ElementNotVisibleException.class, ElementNotSelectableException.class, TimeoutException.class, NoSuchSessionException.class, StaleElementReferenceException.class);

    public BasePage goTo(String url) {
        this.driver.get(url);
        waitForPageToLoad();
        logger.info("URL loaded: ".concat(url));
        return this;
    }


    public BasePage close() {
        waitForPageToLoad();
        this.driver.close();
        logger.info("Quiting browser instance.");
        return this;
    }


    public WebElement scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("arguments[0].scrollIntoView({block: \"center\",inline: \"center\",behavior: \"smooth\"});",element);
        waitForElementToAppear(element);
        logger.info(("Browser scrolled for element:").concat(element.getText()));
        return element;
    }

    public BasePage switchToActiveElement() {
        this.driver.switchTo().activeElement();
        logger.info("Switched to active WebElement.");
        return this;
    }

    public BasePage enterText(By locator, String text) {
        wait.until(visibilityOfElementLocated(locator)).click();
        this.driver.findElement(locator).clear();
        this.driver.findElement(locator).sendKeys(text);
        logger.info("Text entered: ".concat(text));
        return this;
    }


    public BasePage refreshPage() {
        this.driver.navigate().refresh();
        logger.info("Page refreshed");
        return this;
    }

    public BasePage waitForPageToLoad() {
        wait.until((ExpectedCondition) webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString().equalsIgnoreCase("complete")
                || ((Boolean) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0")));
        return this;
    }

    public WebElement waitForElementToClickable(WebElement element) {
        return wait.until(elementToBeClickable(element));
    }

    public WebElement waitForElementToClickable(By by) {
        return wait.until(elementToBeClickable(by));
    }

    public WebElement waitForElementToAppear(WebElement element) {
        return wait.until(visibilityOf(element));
    }

    public WebElement waitForElementToAppear(By by) {
        return wait.until(visibilityOfElementLocated(by));
    }


    public List<WebElement> waitForElementsToAppear(List<WebElement> elements) {
        return wait.until(visibilityOfAllElements(elements));
    }

    public List<WebElement> waitForElementsToAppear(By by) {
        return wait.until(visibilityOfAllElementsLocatedBy(by));
    }


    public WebElement reactivateWebElement(By by, WebElement element){
        try {
            wait.ignoreAll(exceptionList)
                    .until(refreshed(visibilityOf(element)));
            logger.info(("Element is available.").concat(BLANK).concat(element.toString()));

        } catch (WebDriverException exception) {
            logger.warn(exception.getMessage());
        } return this.driver.findElement(by);
    }


    public List<WebElement> reactivateWebElements(By by, List<WebElement> elements){
        try {
            wait.ignoreAll(exceptionList)
                    .until(refreshed(visibilityOfAllElements(elements)));
            logger.info("Elements is available.");
        } catch (WebDriverException exception) {
            logger.warn(exception.getMessage());
        } return this.driver.findElements(by);
    }


    public Boolean isElementLoaded(By locator){
        return !getWebElements(this.driver.findElements(locator), locator).isEmpty();
    }

    private List<WebElement> getWebElements(List<WebElement> webElementList, By locator) {
        waitForPageToLoad();
       /* try {

            await()
                    .dontCatchUncaughtExceptions()
                    .ignoreExceptions()
                    .atMost(timeout, SECONDS)
                    .pollInterval(pollingInterval, SECONDS)
                    .until(() -> this.wait.until(visibilityOfAllElements(webElementList)));
        } catch(StaleElementReferenceException exception) {

        }*/
        return this.driver.findElements(locator);
    }

  /*  private Callable<ExpectedCondition> getVisibilityStatusOfElements(List<WebElement> elements) {
        return () -> refreshed(visibilityOfAllElements(elements)).apply(elements).andThen(elements.isEmpty());
    }*/


    public BasePage moveToElementAndClick(WebElement webelement) {
        this.actions.click(webelement);
        logger.info("Moved to WebElement and clicked: ".concat(webelement.getText()));
        return this;
    }

    public BasePage moveToElementAndClick(List<WebElement> webelements) {
        for (WebElement element : webelements) {
            wait.until(visibilityOf(element));
            this.actions.click(element);
            logger.info("Moved to WebElement and clicked: ".concat(element.getText()));
        }
        return this;
    }

    public String getDynamicLocator(String locator, String newString) {
        return String.format(locator, newString);
    }

    public WebElement getDynamicWebElement(String locator, String newString) {
        return this.driver.findElement(By.xpath(String.format(locator, newString)));
    }


    public BasePage dragAndDropWebElement(WebElement source, WebElement destination) {
        this.actions.moveToElement(source).dragAndDrop(source, destination);
        logger.info("WebElement dragged and dropped to ".concat(destination.getText()));
        return this;
    }


    public BasePage selectByVisibleTextInDropdown(WebElement webelement, String visibleText) {
        new Select(webelement).selectByVisibleText(visibleText);
        logger.info("WebElement selected by visible text: ".concat(visibleText));
        return this;
    }


    public BasePage selectByValueInDropdown(WebElement webelement, String value) {
        new Select(webelement).selectByValue(value);
        logger.info("WebElement selected by value: ".concat(value));
        return this;
    }


    public BasePage selectManyByValueInDropdown(WebElement webelement, List<String> multiValues) {
        for (String valueToBeSelected : multiValues) {
            new Select(webelement).selectByValue(valueToBeSelected);
        }
        logger.info("All WebElements selected by value: ".concat(multiValues.toString()));
        return this;
    }


    public BasePage selectManyByVisibleTextInDropdown(WebElement webelement, List<String> multiValues) {
        for (String valueToBeSelected : multiValues) {
            new Select(webelement).selectByVisibleText(valueToBeSelected);
        }
        logger.info("All WebElements selected by visible text: ".concat(multiValues.toString()));
        return this;
    }


    public BasePage selectByIndexInDropdown(WebElement webelement, int index) {
        new Select(webelement).selectByIndex(index);
        logger.info("WebElement selected by index: ".concat(String.valueOf(index)));
        return this;
    }


    public WebElement getFirstSelectedOptionInDropdown(WebElement webelement) {
        return new Select(webelement).getFirstSelectedOption();
    }


    public List<WebElement> getAllSelectedOptionsInDropdown(WebElement webelement) {
        return new Select(webelement).getAllSelectedOptions();
    }


    public BasePage deselectByVisibleTextInDropdown(WebElement webelement, String visibleText) {
        new Select(webelement).deselectByVisibleText(visibleText);
        logger.info("WebElement deselected by visible text: ".concat(visibleText));
        return this;
    }


    public BasePage deselectByValueInDropdown(WebElement webelement, String value) {
        new Select(webelement).deselectByValue(value);
        logger.info("WebElement deselected by value: ".concat(value));
        return this;
    }


    public BasePage deselectByIndexInDropdown(WebElement webelement, int index) {
        new Select(webelement).deselectByIndex(index);
        logger.info("WebElement deselected by index: ".concat(String.valueOf(index)));
        return this;
    }

    public BasePage deselectAllInDropdown(WebElement webelement) {
        new Select(webelement).deselectAll();
        logger.info("All WebElements deselected.");
        return this;
    }


    public List<WebElement> getAllOptionsInDropdown(WebElement webelement) {
        return new Select(webelement).getOptions();
    }


}
