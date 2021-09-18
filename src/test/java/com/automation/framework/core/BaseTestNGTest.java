package com.automation.framework.core;

import com.automation.framework.core.reporting.TestExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

@SpringBootTest
@Listeners(TestExecutionListener.class)
public class BaseTestNGTest extends AbstractTestNGSpringContextTests {

    public static final Logger logger = LoggerFactory.getLogger(BaseTestNGTest.class);

}
