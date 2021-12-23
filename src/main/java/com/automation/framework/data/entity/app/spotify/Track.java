package com.automation.framework.data.entity.app.spotify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Track {

    private String href;
    private List<Object> items;
    private Integer limit;
    private String next;
    private Integer offset;
    private String previous;
    private Integer total;
}
