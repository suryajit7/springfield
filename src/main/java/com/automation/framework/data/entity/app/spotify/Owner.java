package com.automation.framework.data.entity.app.spotify;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    private String display_name;
    private ExternalUrl external_urls;
    private String href;
    private String id;
    private String type;
    private String uri;
}
