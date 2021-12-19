package com.automation.framework.util.service;

import com.automation.framework.core.annotation.LazyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@LazyService
public class PropertyDecryptService {

    @Value("${app.opencart.username}")
    private String opencartUsername;

    @Value("${app.opencart.password}")
    private String opencartPassword;

    public String getOpencartUsername() {
        return opencartUsername;
    }

    public String getOpencartPassword() {
        return opencartPassword;
    }

}
