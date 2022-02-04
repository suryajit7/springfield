package com.automation.framework.core.config;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppContextProvider implements ApplicationContextAware  {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBeanOfType(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    public static <T> T getFirstBeanOfType(Class<T> beanClass) {
        Map<String, T> beans = applicationContext.getBeansOfType(beanClass);
        if (beans.isEmpty()) {
            throw new NoSuchBeanDefinitionException("No bean found for class " + beanClass);
        }
        return beans.values().iterator().next();
    }
}
