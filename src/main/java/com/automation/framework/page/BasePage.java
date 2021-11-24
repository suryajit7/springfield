package com.automation.framework.page;

import com.automation.framework.core.Kernel;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage extends Kernel {

    public void goTo(String url) {
        this.driver.get(url);
        logger.info("URL loaded: ".concat(url));
    }


    public void switchToActiveElement() {
        this.driver.switchTo().activeElement();
        logger.info("Switched to active WebElement.");
    }

    public void enterText(WebElement webelement, String text) {
        wait.until(visibilityOf(webelement)).click();
        webelement.clear();
        webelement.sendKeys(text);
        logger.info("Text entered: ".concat(text));
    }


    public void refreshPage() {
        this.driver.navigate().refresh();
        logger.info("Page refreshed");
    }

    public void waitForPageToLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) this.driver).executeScript("return document.readyState").toString().equals("complete"));
    }


    public void moveToElementAndClick(WebElement webelement) {
        this.actions.click(webelement);
        logger.info("Moved to WebElement and clicked: ".concat(webelement.getText()));
    }

    public void moveToElementAndClick(List<WebElement> webelements) {
        for (WebElement element : webelements) {
            wait.until(visibilityOf(element));
            this.actions.click(element);
            logger.info("Moved to WebElement and clicked: ".concat(element.getText()));
        }

    }

    public String getDynamicLocator(String locator, String newString) {
        return String.format(locator, newString);
    }

    public WebElement getDynamicWebElement(String locator, String newString) {
        return this.driver.findElement(By.xpath(String.format(locator, newString)));
    }


    public void dragAndDropWebElement(WebElement source, WebElement destination) {
        this.actions.moveToElement(source).dragAndDrop(source, destination);
        logger.info("WebElement dragged and dropped to ".concat(destination.getText()));
    }


    public void selectByVisibleTextInDropdown(WebElement webelement, String visibleText) {
        new Select(webelement).selectByVisibleText(visibleText);
        logger.info("WebElement selected by visible text: ".concat(visibleText));
    }


    public void selectByValueInDropdown(WebElement webelement, String value) {
        new Select(webelement).selectByValue(value);
        logger.info("WebElement selected by value: ".concat(value));
    }


    public void selectManyByValueInDropdown(WebElement webelement, List<String> multiValues) {
        for (String valueToBeSelected : multiValues) {
            new Select(webelement).selectByValue(valueToBeSelected);
        }
        logger.info("All WebElements selected by value: ".concat(multiValues.toString()));
    }


    public void selectManyByVisibleTextInDropdown(WebElement webelement, List<String> multiValues) {
        for (String valueToBeSelected : multiValues) {
            new Select(webelement).selectByVisibleText(valueToBeSelected);
        }
        logger.info("All WebElements selected by visible text: ".concat(multiValues.toString()));
    }


    public void selectByIndexInDropdown(WebElement webelement, int index) {
        new Select(webelement).selectByIndex(index);
        logger.info("WebElement selected by index: ".concat(String.valueOf(index)));
    }


    public WebElement getFirstSelectedOptionInDropdown(WebElement webelement) {
        return new Select(webelement).getFirstSelectedOption();
    }


    public List<WebElement> getAllSelectedOptionsInDropdown(WebElement webelement) {
        return new Select(webelement).getAllSelectedOptions();
    }


    public void deselectByVisibleTextInDropdown(WebElement webelement, String visibleText) {
        new Select(webelement).deselectByVisibleText(visibleText);
        logger.info("WebElement deselected by visible text: ".concat(visibleText));
    }


    public void deselectByValueInDropdown(WebElement webelement, String value) {
        new Select(webelement).deselectByValue(value);
        logger.info("WebElement deselected by value: ".concat(value));
    }


    public void deselectByIndexInDropdown(WebElement webelement, int index) {
        new Select(webelement).deselectByIndex(index);
        logger.info("WebElement deselected by index: ".concat(String.valueOf(index)));
    }

    public void deselectAllInDropdown(WebElement webelement) {
        new Select(webelement).deselectAll();
        logger.info("All WebElements deselected.");
    }


    public List<WebElement> getAllOptionsInDropdown(WebElement webelement) {
        return new Select(webelement).getOptions();
    }


}
