package com.automation.framework.data.entity.app.postman;

import com.automation.framework.data.entity.app.Error;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@Jacksonized
public class Workspace {

    @Id
    private String id;
    private String name;
    private String type;
    private String description;
    @Transient
    private Error error;

}
