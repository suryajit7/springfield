package com.automation.framework.gui.module.window;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.page.app.window.MainPage;
import com.automation.framework.page.app.window.PageA;
import com.automation.framework.page.app.window.PageB;
import com.automation.framework.page.app.window.PageC;
import org.springframework.beans.factory.annotation.Autowired;
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

    @BeforeClass
    public void setup() {
        this.mainPage.goTo();
        this.mainPage.launchAllWindows();
    }

    @Test
    public void switchTest() {
        this.pageA.addToArea(1 + "\n");
        this.pageB.addToArea((2 * 2) + "\n");
        this.pageC.addToArea((3 * 3) + "\n");
    }

}
