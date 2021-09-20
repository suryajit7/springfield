package com.automation.framework.page;

import com.automation.framework.util.CommonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;

public class BasePage {

    protected Log logger;
    protected Actions actions;
    protected CommonUtil commonUtil;

    @Value("${default.timeout:50}")
    private int timeout;

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;


    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
        this.actions = new Actions(this.driver);
        this.logger = LogFactory.getLog(getClass());
        this.commonUtil = new CommonUtil();
    }


    public void goTo(String url) {
        this.driver.get(url);
    }


    public void switchToActiveElement() {
        this.driver.switchTo().activeElement();
    }

    public void enterText(WebElement webelement, String text) {
        webelement.clear();
        webelement.sendKeys(text);
        logger.info("Text entered:".concat(text));
    }


    public void refreshPage() {
        this.driver.navigate().refresh();
        logger.info("Page refreshed");
    }


    public void moveToElementAndClick(WebElement webelement) {
        this.actions.click(webelement);
    }


    public void dragAndDropWebElement(WebElement source, WebElement destination) {
        this.actions.moveToElement(source).dragAndDrop(source, destination);
    }


    public void selectByVisibleTextInDropdown(WebElement webelement, String visibleText) {
        new Select(webelement).selectByVisibleText(visibleText);
        logger.info("WebElement selected by visible text:".concat(visibleText));
    }


    public void selectByValueInDropdown(WebElement webelement, String value) {
        new Select(webelement).selectByValue(value);
        logger.info("WebElement selected by value:".concat(value));
    }


    public void selectManyByValueInDropdown(WebElement webelement, List<String> multiValues) {
        for (String valueToBeSelected : multiValues) {
            new Select(webelement).selectByValue(valueToBeSelected);
        }
        logger.info("All WebElements selected by value:".concat(multiValues.toString()));
    }


    public void selectManyByVisibleTextInDropdown(WebElement webelement, List<String> multiValues) {
        for (String valueToBeSelected : multiValues) {
            new Select(webelement).selectByVisibleText(valueToBeSelected);
        }
        logger.info("All WebElements selected by visible text:".concat(multiValues.toString()));
    }


    public void selectByIndexInDropdown(WebElement webelement, int index) {
        new Select(webelement).selectByIndex(index);
        logger.info("WebElement selected by index:".concat(String.valueOf(index)));
    }


    public WebElement getFirstSelectedOptionInDropdown(WebElement webelement) {
        return new Select(webelement).getFirstSelectedOption();
    }


    public List<WebElement> getAllSelectedOptionsInDropdown(WebElement webelement) {
        return new Select(webelement).getAllSelectedOptions();
    }


    public void deselectByVisibleTextInDropdown(WebElement webelement, String visibleText) {
        new Select(webelement).deselectByVisibleText(visibleText);
        logger.info("WebElement deselected by visible text:".concat(visibleText));
    }


    public void deselectByValueInDropdown(WebElement webelement, String value) {
        new Select(webelement).deselectByValue(value);
        logger.info("WebElement deselected by value:".concat(value));
    }


    public void deselectByIndexInDropdown(WebElement webelement, int index) {
        new Select(webelement).deselectByIndex(index);
        logger.info("WebElement deselected by index:".concat(String.valueOf(index)));
    }

    public void deselectAllInDropdown(WebElement webelement) {
        new Select(webelement).deselectAll();
        logger.info("All WebElements deselected.");
    }


    public List<WebElement> getAllOptionsInDropdown(WebElement webelement) {
        return new Select(webelement).getOptions();
    }


}
