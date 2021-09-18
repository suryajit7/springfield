package com.automation.framework.core.config.annotation;


import java.lang.annotation.*;

@Page
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Window {
    String value() default "";
}
