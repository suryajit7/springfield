package com.automation.framework.data.entity.app.ems;

import com.automation.framework.page.app.hrm.admin.usermanagement.Status;
import com.automation.framework.page.app.hrm.admin.usermanagement.UserRole;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;

@Data
@Builder
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
