package com.automation.framework.core.module.visa;

import com.automation.framework.core.BaseTestNGTest;
import com.automation.framework.core.data.entity.User;
import com.automation.framework.core.data.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

public class VisaRegistrationTest extends BaseTestNGTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void visaTest() {

        List<User> users = this.userRepository.findByFirstNameStartingWith("Bran");
        System.out.println(users);
        logger.info("some info");
    }
}
