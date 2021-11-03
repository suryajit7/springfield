package com.automation.framework;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

import static java.lang.Character.*;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static org.junit.jupiter.api.DisplayNameGenerator.parameterTypesAsString;

@Slf4j
class TestDisplayNameFormat extends ReplaceUnderscores {

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return formatTestName(super.generateDisplayNameForClass(testClass));
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return formatTestName(super.generateDisplayNameForNestedClass(nestedClass));
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return this.formatTestName(testMethod.getName()).concat(parameterTypesAsString(testMethod));
    }

    String formatTestName(String testName) {

        StringBuilder result = new StringBuilder();
        result.append(toUpperCase(testName.charAt(0)));

        for (int i=1; i<testName.length(); i++) {
            result = isTestName(testName, i) ? result.append(' ').append(testName.charAt(i)) : result.append(testName.charAt(i));
        }
        return result.toString();
    }

    private boolean isTestName(String name, int i) {
        return (isUpperCase(name.charAt(i)) && isLowerCase(name.charAt(i-1))) || (isUpperCase(name.charAt(i)) && isLowerCase(name.charAt(i+1)));
    }

}

