package com.automation.framework.core.bean;

import com.automation.framework.core.annotation.LazyConfiguration;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;

@LazyConfiguration
public class FakerConfig {

    @Bean
    public Faker getFakerConfig(){
        return new Faker();
    }

}
