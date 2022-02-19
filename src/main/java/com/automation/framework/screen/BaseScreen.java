package com.automation.framework.screen;

import com.automation.framework.core.Kernel;
import com.automation.framework.core.annotation.Screen;
import lombok.Getter;

@Screen
@Getter
public class BaseScreen extends Kernel {

/*    protected void getServerStatus() {
        appiumDriver.getStatus();
    }

    protected void setOrientation(ScreenOrientation screenOrientationType) {
        switch (screenOrientationType) {
            case LANDSCAPE:
                appiumDriver.rotate(LANDSCAPE);
                logger.info("Device Orientation is set to Landscape");
                break;
            case PORTRAIT:
                appiumDriver.rotate(PORTRAIT);
                logger.info("Device Orientation is set to Portrait");
                break;
            default:
                throw new IllegalStateException("Unexpected value in Screen Orientation: " + screenOrientationType);
        }
    }

    protected void backgroundApp() {
        appiumDriver.runAppInBackground(ofSeconds(10));
    }*/



}
