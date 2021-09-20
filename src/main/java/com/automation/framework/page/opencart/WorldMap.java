package com.automation.framework.page.opencart;

import com.automation.framework.core.annotation.PageComponent;
import com.automation.framework.page.BasePage;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
@Setter
@PageComponent
public class WorldMap extends BasePage {

    private final String WORLD_MAP_LABEL = "//h3[normalize-space()='World Map']";
    private final String ZOOM_IN = "//div[@class='jqvmap-zoomin']";
    private final String ZOOM_OUT = "//div[@class='jqvmap-zoomout']";
    private final String SELECT_COUNTRY = "jqvmap1_%s";
    private final String FORGOT_PASSWORD = "//a[normalize-space()='Forgotten Password']";

    @FindBy(xpath = ZOOM_IN)
    private WebElement zoomIn;

    @FindBy(xpath = ZOOM_OUT)
    private WebElement zoomOut;

    public void selectCountry(String countryCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(WORLD_MAP_LABEL)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(commonUtil.getDynamicLocator(SELECT_COUNTRY, countryCode))))
                .click();
        logger.info("Country selected");
    }

}
