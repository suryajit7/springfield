package com.automation.framework.data.entity.spotify;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class Owner {

    private String display_name;
    private ExternalUrl external_urls;
    private String href;
    private String id;
    private String type;
    private String uri;
}
