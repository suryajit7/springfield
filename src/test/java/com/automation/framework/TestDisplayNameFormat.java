package com.automation.framework;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

import static java.lang.Character.isUpperCase;
import static java.lang.Character.toUpperCase;

//TODO:: Modify the logic of generating the test method names
@Slf4j
class TestDisplayNameFormat extends DisplayNameGenerator.ReplaceUnderscores {


    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return replaceCamelCase(super.generateDisplayNameForClass(testClass));
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return replaceCamelCase(super.generateDisplayNameForNestedClass(nestedClass));
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return this.replaceCamelCase(testMethod.getName()) +
                DisplayNameGenerator.parameterTypesAsString(testMethod);
    }

    String replaceCamelCase(String camelCase) {
        StringBuilder result = new StringBuilder();
        result.append(toUpperCase(camelCase.charAt(0)));
        for (int i=1; i<camelCase.length(); i++) {

            result = isUpperCase(camelCase.charAt(i)) ? result.append(' ').append(camelCase.charAt(i)) : result.append(camelCase.charAt(i));
        }
        return result.toString();
    }
}

