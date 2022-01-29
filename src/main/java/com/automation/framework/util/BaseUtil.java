package com.automation.framework.util;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.automation.framework.util.file.PathFinder;
import com.github.javafaker.Faker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;

@LazyConfiguration
public class BaseUtil {

    @Bean
    public Faker getFaker(){
        return new Faker(); }

    @Bean
    public Log getLogger(){
        return LogFactory.getLog(getClass()); }


    @Bean
    public PathFinder getPathFinder(){
        return new PathFinder(); }

}
