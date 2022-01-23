package com.automation.framework.core.config;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;

@LazyConfiguration
@EncryptablePropertySource("encrypted.properties")
public class AppConfigEncryptHelper {

}
