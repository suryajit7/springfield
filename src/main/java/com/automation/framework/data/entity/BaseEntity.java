package com.automation.framework.data.entity;


import com.automation.framework.data.entity.ems.Company;
import com.automation.framework.data.entity.ems.Department;
import com.automation.framework.data.entity.ems.Employee;
import com.automation.framework.data.entity.ems.User;
import com.automation.framework.data.entity.fnb.BreakfastMenu;
import com.automation.framework.data.entity.library.Catalog;
import com.automation.framework.data.entity.postman.Postman;
import com.automation.framework.data.entity.postman.Workspace;
import com.automation.framework.data.entity.spotify.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.qameta.allure.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@JsonInclude(NON_NULL)
@Document
@JacksonXmlRootElement(localName = "BaseEntity")
@Root(name = "BaseEntity")
public class BaseEntity {


    List<Employee> employees;
    List<Company> companies;
    List<Department> departments;
    List<User> users;
    @Transient
    List<Postman> postmen;
    @Transient
    List<Workspace> workspaces;
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

    //Allure
    List<TestResult> testResults;
    List<StepResult> stepResults;
    List<TestResultContainer> testResultContainers;
    List<Attachment> attachments;
    List<Parameter> parameters;

}
