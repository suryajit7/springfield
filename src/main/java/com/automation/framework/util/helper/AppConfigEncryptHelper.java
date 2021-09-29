package com.automation.framework.util.helper;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;

@LazyConfiguration
@EncryptablePropertySource("application.properties")
public class AppConfigEncryptHelper {

}
