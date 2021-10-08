package com.automation.framework.data.entity.app.ems;

import javax.persistence.Id;

public class Department {


    @Id
    private Integer deptId;
    private String deptName;
    private String deptCode;
    private Integer size;


}
