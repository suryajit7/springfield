package com.springfield.framework.functional.ui;

import com.springfield.framework.SpringfieldApplicationTests;
import com.springfield.framework.core.annotation.LazyAutowired;
import com.springfield.framework.page.site.AddEmployeePage;
import com.springfield.framework.page.site.OrangeLoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static com.springfield.framework.page.site.OrangeMenu.ADD_EMPLOYEE;

public class AddEmployeePageTest extends SpringfieldApplicationTests {

    @LazyAutowired
    private OrangeLoginPage orangeLoginPage;

    @LazyAutowired
    private AddEmployeePage addEmployeePage;

    @BeforeAll
    public void beforeTestSetup() {

        orangeLoginPage.appLogin("Admin","admin123")
                .waitForPageToLoad()
                .goTo(ADD_EMPLOYEE)
                .waitForPageToLoad();

        addEmployeePage.isPageLoaded();
    }

    @Test
    @Order(1)
    public void verifyAddEmployeeDetails(){

        addEmployeePage.isPageLoaded()
                .enterText(addEmployeePage.getEmployeeFirstNameInputField(), faker.name().firstName())
                .enterText(addEmployeePage.getEmployeeMiddleNameInputField(), faker.funnyName().name())
                .enterText(addEmployeePage.getEmployeeLastNameInputField(), faker.name().lastName())
                .click(addEmployeePage.getCancelButton());
    }

    @AfterAll
    public void tearDownTestSetup(){
        //orangeLoginPage.getDriver().close();
    }
}
