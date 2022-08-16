package works.springfield.framework.core;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public Log logger;

    public Actions actions;
}
