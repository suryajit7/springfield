package com.automation.framework.data.supplier;

import com.automation.framework.data.entity.app.ems.Company;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;

public class CompanyDataFactory {

    @Autowired
    private static Faker faker;


    @DataProvider(parallel = true)
    public static Object[] getCompanyData(){
        return new Object[]{getValidCompany(), getEmptyCompany()};
    }

    public static Company getEmptyCompany() {
        return Company.builder().build();
    }

    public static Company getValidCompany() {
        return Company.builder()
                .companyId(faker.company().hashCode()).companyName(faker.company().name()).companyType(faker.company().industry())
                .city(faker.address().cityName()).state(faker.address().state()).country(faker.address().country())
                .postalCode(faker.address().zipCode()).phoneNumber(Long.valueOf(faker.phoneNumber().phoneNumber()))
                .build();
  }

}
