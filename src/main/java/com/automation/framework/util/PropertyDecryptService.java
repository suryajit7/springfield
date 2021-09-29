package com.automation.framework.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
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
