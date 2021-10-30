package com.automation.framework;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

@Slf4j
public class TestDisplayNameFormat extends DisplayNameGenerator.Standard {

    public TestDisplayNameFormat(){}

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return this.replaceCapitals(super.generateDisplayNameForClass(testClass));
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return this.replaceCapitals(super.generateDisplayNameForNestedClass(nestedClass));
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return this.replaceCapitals(testMethod.getName());
    }

    private String replaceCapitals(String name) {
        name = name.replaceAll("([A-Z])", " $1");
        name = name.replaceAll("([0-9]+)", " $1");
        return name;
    }
}

