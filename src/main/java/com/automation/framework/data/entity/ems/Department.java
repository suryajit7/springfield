package com.automation.framework.data.entity.ems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class Department {


    @Id
    private Integer deptId;

    private String deptName;

    private String deptCode;

    private Integer size;

    private String costCenter;

    private Boolean isNightShift;


}
