package com.automation.framework.core.config;

import org.springframework.stereotype.Component;

@Component
public class ConfigurableBean {

    private Boolean expiredAccessToken;

    public void setExpiredAccessToken(Boolean expiredAccessToken){
        this.expiredAccessToken = expiredAccessToken; }


    public Boolean getExpiredAccessToken(){
        return this.expiredAccessToken; }

}
