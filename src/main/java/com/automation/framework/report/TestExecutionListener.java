package com.automation.framework.report;


import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class TestExecutionListener implements ITestListener, IInvokedMethodListener {

    @Autowired
    private WebDriver driver;

    private static final ThreadLocal<ITestNGMethod> currentMethods = new ThreadLocal<>();
    private static final ThreadLocal<ITestResult> currentResults = new ThreadLocal<>();

    private static final String WEBDRIVER = "WebDriver";

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        currentMethods.set(method.getTestMethod());
        currentResults.set(testResult);
        //getEnvironment();
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        currentMethods.remove();
        currentResults.remove();
    }

    public static ITestNGMethod getTestMethod() {
        return checkNotNull(currentMethods.get(),
                "Did you forget to register the %s listener?", TestExecutionListener.class.getName());
    }

    /**
     * Parameters passed from a data provider are accessible in the test result.
     */
    public static ITestResult getTestResult() {
        return checkNotNull(currentResults.get(),
                "Did you forget to register the %s listener?", TestExecutionListener.class.getName());
    }


    @Override
    public void onTestStart(ITestResult result) {
        ITestContext context = result.getTestContext();
        driver = (WebDriver) context.getAttribute(WEBDRIVER);

/*        Shutterbug.shootPage(this.driver, Capture.FULL_SCROLL, 50,true)
                .withName("BeforeTest_"+result.getMethod().getMethodName()).save(BEFORE_SCREENSHOTS_DIR_PATH + localDate.format(formatter));

        logger.info("************************");
        logger.info(result.getMethod().getMethodName());
        logger.info("Test Priority:"+result.getMethod().getPriority());*/

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestContext context = result.getTestContext();
        driver = (WebDriver) context.getAttribute(WEBDRIVER);

  /*      Shutterbug.shootPage(this.driver, Capture.FULL_SCROLL, SCROLL_TIMEOUT, true).withName("PassedTest_" + result.getMethod().getMethodName()).save(AFTER_SCREENSHOTS_DIR_PATH + localDate.format(formatter));

        logger.info("Test Status: Passed");
        logger.info("************************");*/
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ITestContext context = result.getTestContext();
        driver = (WebDriver) context.getAttribute(WEBDRIVER);

        //saveFailureScreenShot(driver);



  /*      Shutterbug.shootPage(this.driver, Capture.FULL_SCROLL, SCROLL_TIMEOUT,true)
                .withName("Failed_" + result.getMethod().getMethodName()).save(FAILED_SCREENSHOTS_DIR_PATH + localDate.format(formatter));

        logger.info("Test Status: Failed");
        logger.error(result.getThrowable().getMessage());
        logger.info("************************");*/
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ITestContext context = result.getTestContext();
        driver = (WebDriver) context.getAttribute(WEBDRIVER);

/*        Shutterbug.shootPage(this.driver, Capture.FULL_SCROLL, SCROLL_TIMEOUT,true)
                .withName("Skipped_" + result.getMethod().getMethodName()).save(SKIPPED_SCREENSHOTS_DIR_PATH + localDate.format(formatter));


        logger.info("Test Status: Skipped");
        logger.trace(result.getSkipCausedBy());
        logger.error(result.getThrowable().getMessage());
        logger.info("************************");*/
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        /* logger.info("************************");*/
    }

    @Override
    public void onStart(ITestContext context) {

        driver = (WebDriver) context.getAttribute(WEBDRIVER);

      /*  logger.info("************************");
        logger.info("Test Suite Started...");
        logger.info(context.getSuite().getName());
        logger.info(context.getSuite().getXmlSuite());
        logger.info("************************");*/
    }

    @Override
    public void onFinish(ITestContext context) {
        //logger.info("************************");
    }


    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
