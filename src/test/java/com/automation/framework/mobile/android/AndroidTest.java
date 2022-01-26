package com.automation.framework.mobile.android;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.page.app.demo.DemoAPK;
import org.testng.annotations.Test;

public class AndroidTest extends BaseTestNGTest {

    @LazyAutowired
    private DemoAPK demoAPK;

    @Test
    public void someTest(){
        //demoAPK.someAction();
    }



}
