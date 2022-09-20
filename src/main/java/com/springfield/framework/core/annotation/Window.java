package com.springfield.framework.core.annotation;

import java.lang.annotation.*;

@PageObject
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Window {
}
