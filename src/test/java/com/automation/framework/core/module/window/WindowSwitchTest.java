package com.automation.framework.core.module.window;

import com.automation.framework.core.BaseTestNGTest;
import com.automation.framework.core.pages.window.MainPage;
import com.automation.framework.core.pages.window.PageA;
import com.automation.framework.core.pages.window.PageB;
import com.automation.framework.core.pages.window.PageC;
import com.automation.framework.core.util.WindowSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class WindowSwitchTest extends BaseTestNGTest {

    @Autowired
    private MainPage mainPage;

    @Autowired
    private PageA pageA;

    @Autowired
    private PageB pageB;

    @Autowired
    private PageC pageC;

    @Autowired
    private WindowSwitchService switchService;

    @BeforeClass
    public void setup() {
        this.mainPage.goTo();
        this.mainPage.launchAllWindows();
    }

    @Test
    public void switchServiceTest() {
        this.switchService.switchByTitle("Page A");
        this.pageA.addToArea("Page a");

        this.switchService.switchByIndex(2);
        this.pageB.addToArea("Page B");
    }
}
