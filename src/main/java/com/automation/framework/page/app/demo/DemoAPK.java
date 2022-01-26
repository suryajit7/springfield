package com.automation.framework.page.app.demo;

import com.automation.framework.core.annotation.Page;
import com.automation.framework.page.BasePage;
import lombok.Getter;
import lombok.Setter;

@Page
@Getter
@Setter
public class DemoAPK extends BasePage{

    String sas = "drag_layer";

    public void someAction(){
      getSome(sas);
    }
}
