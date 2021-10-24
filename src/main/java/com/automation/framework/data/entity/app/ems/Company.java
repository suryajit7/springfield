package com.automation.framework.data.entity.app.ems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    private Integer companyId;
    private String companyName;
    private String companyType;
    private Long phoneNumber;
    private String city;
    private String state;
    private String country;
    private String postalCode;


}
