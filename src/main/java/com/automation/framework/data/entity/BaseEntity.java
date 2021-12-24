package com.automation.framework.data.entity;


import com.automation.framework.data.entity.app.ems.Company;
import com.automation.framework.data.entity.app.ems.Department;
import com.automation.framework.data.entity.app.ems.Employee;
import com.automation.framework.data.entity.app.ems.User;
import com.automation.framework.data.entity.app.fnb.BreakfastMenu;
import com.automation.framework.data.entity.app.library.Catalog;
import com.automation.framework.data.entity.app.postman.Postman;
import com.automation.framework.data.entity.app.spotify.*;
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
@Root(name = "BaseEntity")
public class BaseEntity {

    List<Employee> employees;
    List<Company> companies;
    List<Department> departments;
    List<User> users;
    List<Postman> postmen;
    List<ExternalUrl> externalUrls;
    List<Follower> followers;
    List<Owner> owners;
    List<Playlist> playlists;
    List<Track> tracks;

    @ElementList(name="breakfast_menu")
    List<BreakfastMenu> breakfast_menu;

    @ElementList(inline = true, required = false)
    List<Catalog> catalog;

    List<Error> errors;

}
