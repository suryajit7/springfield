package com.automation.framework.page.site.saucedemo;

import com.automation.framework.core.annotation.Page;
import com.automation.framework.page.BasePage;
import lombok.Getter;
import lombok.Setter;

@Page
@Getter
@Setter
public class HomePage extends BasePage {

    private final String APP_LOGO = "//div[@class='app_logo']";
    private final String INVENTORY_LIST = "//div[@id='inventory_container']/div[@class='inventory_list']";
    private final String PRODUCT_NAME = "//div[normalize-space()='']";



}
