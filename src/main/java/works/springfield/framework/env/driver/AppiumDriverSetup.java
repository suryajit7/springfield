package works.springfield.framework.env.driver;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import works.springfield.framework.core.annotation.LazyConfiguration;

import static works.springfield.framework.data.Constants.WEBDRIVER_RUNMODE;

@LazyConfiguration
@ConditionalOnProperty(name = WEBDRIVER_RUNMODE, havingValue = "mobile")
public class AppiumDriverSetup {

}
