package com.automation.framework.data.entity.app.ems;

import com.automation.framework.page.app.hrm.admin.usermanagement.Status;
import com.automation.framework.page.app.hrm.admin.usermanagement.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Employee {

    @Id
    private Integer id;
    private Integer employeeId;
    private Integer deptId;
    private String username;
    private String employeeName;
    private UserRole userRole;
    private Status status;
}
