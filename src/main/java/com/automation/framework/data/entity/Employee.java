package com.automation.framework.data.entity;

import com.automation.framework.page.app.hrm.admin.usermanagement.Status;
import com.automation.framework.page.app.hrm.admin.usermanagement.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
public class Employee {

    @Id
    private Integer id;

    private String username;
    private String employeeName;
    private UserRole userRole;
    private Status status;
}
