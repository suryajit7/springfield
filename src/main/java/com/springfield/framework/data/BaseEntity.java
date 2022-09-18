package com.springfield.framework.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class BaseEntity {

    private String message;
    private ResponseData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(NON_NULL)
    public class ResponseData {

        //Login Response Data
        private String user_id;
        private String email;
        private String first_name;
        private String last_name;
        private String org_name;
        private String is_admin;
        private String access_token;
        private String refresh_token;

    }
}
