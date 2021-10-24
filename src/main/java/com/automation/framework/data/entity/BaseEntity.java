package com.automation.framework.data.entity;


import com.automation.framework.data.entity.app.User;
import com.automation.framework.data.entity.app.ems.Company;
import com.automation.framework.data.entity.app.ems.Department;
import com.automation.framework.data.entity.app.ems.Employee;
import com.automation.framework.data.entity.app.fnb.BreakfastMenu;
import com.automation.framework.data.entity.app.library.Catalog;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "BaseEntity")
@Root( name = "BaseEntity" )
public class BaseEntity {

    List<Employee> employees;
    List<Company> companies;
    List<Department> departments;
    List<User> users;

    @ElementList(name="breakfast_menu")
    List<BreakfastMenu> breakfast_menu;

    @ElementList(inline = true, required = false)
    List<Catalog> catalog;

}
