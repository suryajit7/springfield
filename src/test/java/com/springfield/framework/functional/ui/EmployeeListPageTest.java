package com.springfield.framework.functional.ui;

import com.springfield.framework.SpringfieldApplicationTests;
import com.springfield.framework.core.annotation.LazyAutowired;
import com.springfield.framework.page.site.EmployeeListPage;
import com.springfield.framework.page.site.OrangeLoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


public class EmployeeListPageTest extends SpringfieldApplicationTests {


    @LazyAutowired
    private OrangeLoginPage orangeLoginPage;

    @LazyAutowired
    private EmployeeListPage employeeListPage;

    @BeforeAll
    public void beforeTestSetup() {
        orangeLoginPage.appLogin("Admin","admin123")
                .waitForPageToLoad();
    }

    @Test
    @Order(1)
    public void verifyEmployeeList(){

        employeeListPage.isPageLoaded();

    }

    @AfterAll
    public void tearDownTestSetup(){
        orangeLoginPage.getDriver().close();
    }





}
