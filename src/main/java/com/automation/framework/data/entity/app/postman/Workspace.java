package com.automation.framework.data.entity.app.postman;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workspace {

    @Id
    private String id;
    private String name;
    private String type;
    private String description;

}
