package com.automation.framework.gui.module.opencart;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.app.hrm.LoginPanelPage;
import com.automation.framework.page.app.hrm.MenuNavigationPage;
import com.automation.framework.page.app.hrm.admin.usermanagement.Status;
import com.automation.framework.page.app.hrm.admin.usermanagement.UserRole;
import com.automation.framework.page.app.hrm.admin.usermanagement.Users;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.automation.framework.page.app.hrm.MenuOption.USERS;

public class UsersTest extends BaseTestNGTest {


    @Value("${app.hrm.url}")
    private String url;

    @Value("${app.hrm.username:demo}")
    private String username;

    @Value("${app.hrm.password:demo}")
    private String password;

    @LazyAutowired
    private LoginPanelPage loginPanelPage;

    @LazyAutowired
    private Users users;

    @LazyAutowired
    private MenuNavigationPage menuNavigation;

    @BeforeClass
    public void setup() {
        this.loginPanelPage.goTo(url);
        this.loginPanelPage.enterUsernameAndPassword(username, password);
        this.loginPanelPage.clickLoginButton();
    }

    @Test(priority = 0)
    public void verify() {

        this.menuNavigation.navigateToMenu(USERS);

        this.users.enterUsername("David");
        this.users.selectUserType(UserRole.ADMIN);
        this.users.enterEmployeeName("David");
        this.users.selectUserStatus(Status.ENABLED);
        this.users.clickSearchButton();

    }


}
