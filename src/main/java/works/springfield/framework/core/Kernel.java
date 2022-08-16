package works.springfield.framework.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import works.springfield.framework.core.annotation.LazyAutowired;

/**
 *
 * The WebDriver and Wait instances are managed by Spring.
 */
public class Kernel {

    @LazyAutowired
    public WebDriver driver;

    @LazyAutowired
    public WebDriverWait wait;

    public Actions actions;
}
