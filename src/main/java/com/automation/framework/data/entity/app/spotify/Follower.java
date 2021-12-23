package com.automation.framework.data.entity.app.spotify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Follower {

    private String href;
    private Integer total;

}
