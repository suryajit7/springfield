package com.springfield.framework.core;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class WaitAspect {

    public final static Map<Object, Object> CACHE = new HashMap<>();

    @Pointcut("execution(* com.springfield.framework.page.BasePage.*(..))")
    public void pageObjectWaitPointcut(){}


    @Before("pageObjectWaitPointcut()")
    public void elementIsDisplayed(JoinPoint joinPoint){

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] signParameters = methodSignature.getParameterNames();

        for (String signParameter : signParameters) {
            System.out.println(signParameter);
        }

        Arrays.stream(joinPoint.getArgs())
                .filter(this::isWebElement)
                .forEach(args -> {
                    System.out.println(args);
                    log.info("****************************Before****************************");
                    //getInitializedAwait().until(() -> ((WebElement) args).isDisplayed());
                });
    }


    @Around("pageObjectWaitPointcut()")
    public Object aroundObject(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        log.info(proceedingJoinPoint.getSignature().toString());

        Object object = proceedingJoinPoint.proceed();

        if (object instanceof WebElement){
            log.info("*******Around*******");
            //getInitializedAwait().until(() -> ((WebElement) object).isDisplayed());
        } return object;

    }


    @AfterReturning(value = "pageObjectWaitPointcut()", returning = "element")
    public void elementIsDisplayed(JoinPoint joinPoint, WebElement element){
        log.info("*****AfterReturning*********");
        log.info(element.getText());
    }



/*    @Before(value = "pageObjectWaitPointcut()")
    public void aroundAdvice(JoinPoint joinPoint, WebElement element){
        System.out.println(element);

    }*/

    private boolean isWebElement(Object args) {
        return args.toString().contains("WebDriver") || args.toString().contains("RemoteWebDriver");
    }


}
