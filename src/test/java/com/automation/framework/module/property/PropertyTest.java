package com.automation.framework.module.property;

import com.automation.framework.BaseTestNGTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class PropertyTest extends BaseTestNGTest {

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void testPropertiesFile() throws IOException {

        Properties property = PropertiesLoaderUtils.loadProperties(resourceLoader.getResource("property-test.properties"));
        System.out.printf(String.valueOf(property));


    }


}
