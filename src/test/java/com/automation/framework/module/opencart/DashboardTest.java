package com.automation.framework.module.opencart;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.data.FileReader;
import com.automation.framework.data.entity.app.ems.Employee;
import com.automation.framework.page.app.hrm.MenuNavigationPage;
import com.automation.framework.page.app.opencart.Dashboard;
import com.automation.framework.page.app.opencart.Login;
import com.automation.framework.util.converter.CsvToJson;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.automation.framework.page.app.hrm.MenuOption.USERS;


public class DashboardTest extends BaseTestNGTest {

    @Value("${app.opencart.url}")
    private String url;

    @Value("${test-resources.dir.path}")
    private static Path testDir;

    @LazyAutowired
    private Login login;

    @LazyAutowired
    private Dashboard dashboard;

    @LazyAutowired
    private MenuNavigationPage menuNavigation;

    //@BeforeClass
    public void setup() {
        this.login.goTo(url);
        this.login.enterUsernameAndPassword();
        this.login.clickLoginButton();
    }

    @Test(priority = 0)
    public void verifyDashboard() throws IOException {
        String resourceName = "property-test.properties";
        FileReader reader = new FileReader();
        //this.dashboard.selectCountryByCode("in");
        Path path = reader.getFilePathForFile(resourceName);
        reader.readFile(resourceName);
        System.out.println(path);


        CsvToJson csvToJson = new CsvToJson();

        List<Employee> employeeList = csvToJson.csvToJson(reader.getFilePathForFile("test-data-file.csv"));
        System.out.println(employeeList);
    }

    @Test(priority = 1)
    public void verify() {
        menuNavigation.navigateToMenu(USERS);
    }

}