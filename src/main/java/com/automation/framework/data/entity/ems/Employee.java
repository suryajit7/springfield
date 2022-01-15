package com.automation.framework.data.entity.ems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class Employee {

    @Id
    private Integer id;
    private Integer employeeId;
    private Integer deptId;
    private String username;
    private String employeeName;
    private Date doj;

}
