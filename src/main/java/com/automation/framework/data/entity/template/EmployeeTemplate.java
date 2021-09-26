package com.automation.framework.data.entity.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.automation.framework.data.entity.Employee;
import com.automation.framework.page.app.hrm.admin.usermanagement.Status;
import com.automation.framework.page.app.hrm.admin.usermanagement.UserRole;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * The overridden load() implementation will load the data templates in memory
 * which can be supplied to test as test datasets [@DataProvider].
 */

public class EmployeeTemplate implements TemplateLoader {

    @Autowired
    private Faker faker;

    private Object[] getEmployeeUsernames(){
        List<String> usernameList = new ArrayList<>();
        for (int i=0; i<10; i++){
            usernameList.add(faker.name().username());
        }
        return usernameList.toArray();
    }


    @Override
    public void load() {

        Fixture.of(Employee.class).addTemplate("validEmployee", new Rule() {{
            add("username", random("David", "Luv", "Kakashi", "Briju", "Venki", "Pranav", "Rohan"));
            add("employeeName", random("David Cooper", "Madhukar Trivedi", "Uchiha Kakashi", "Brajesh Pandey", "Venkat Raj", "Pranav Thakar", "Rohan Patel"));
            add("userRole", random(UserRole.class));
            add("status", random(Status.class));
        }});

        Fixture.of(Employee.class).addTemplate("invalidEmployee", new Rule() {{
            add("username", random("David@rockstar", "Luv", "Kakashi", "Briju", "Venki", "Pranav", "Rohan"));
            add("employeeName", random("David/Cooper", "Madhukar$Trivedi", "Uchiha?Kakashi", "Brajesh.Pandey789", "Venky123", "PranavThakar", "Rohan Patel"));
            add("userRole", random(UserRole.class));
            add("status", random(Status.class));
        }});

        Fixture.of(Employee.class).addTemplate("invalidEmployeeName").inherits("validEmployee", new Rule() {{
            add("employeeName", random("", "123", "$ur@j"));
                }});

        Fixture.of(Employee.class).addTemplate("validFakerGeneratedEmployees").inherits("validEmployee", new Rule() {{
            add("username", random(getEmployeeUsernames()));
        }});
    }
}