package com.automation.framework.data.entity.app.spotify;

import com.automation.framework.data.entity.app.Error;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class Playlist {

    private Boolean collaborative;
    private String description;
    private ExternalUrl external_urls;
    private Follower followers;
    private String href;
    private String id;
    private List<Object> images;
    private String name;
    private Owner owner;
    private String primary_color;

    @JsonProperty("public")
    private Boolean isPublic;
    private String snapshot_id;
    private Track tracks;
    private String type;

    private String uri;

    private Error error;

}
