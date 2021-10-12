package com.automation.framework.data.entity.app.ems;

import com.automation.framework.util.converter.StringToBoolean;
import com.automation.framework.util.converter.StringToInteger;
import com.creditdatamw.zerocell.annotation.Column;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Department {


    @Id
    @Column(index=0, name="Dept Id", converterClass = StringToInteger.class)
    private Integer deptId;

    @Column(index=1, name="Dept Name")
    private String deptName;

    @Column(index=2, name="Dept Code")
    private String deptCode;

    @Column(index=3, name="Size", converterClass = StringToInteger.class)
    private Integer size;

    @Column(index=4, name="Cost Center")
    private String costCenter;

    @Column(index=5, name="Night Shift", converterClass = StringToBoolean.class)
    private Boolean isNightShift;


}
