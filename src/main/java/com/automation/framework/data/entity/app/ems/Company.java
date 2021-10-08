package com.automation.framework.data.entity.app.ems;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;

@Data
@Builder
public class Company {

    @Id
    private Integer companyId;
    private String companyName;
    private String companyType;
    private String phoneNumber;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    private String timezone;


}
