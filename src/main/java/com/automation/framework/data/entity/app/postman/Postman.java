package com.automation.framework.data.entity.app.postman;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Postman {
    @Id
    private String id;

    @Transient
    private Workspace workspace;

}
