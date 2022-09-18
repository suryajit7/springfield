package com.springfield.framework.core.config;

import com.springfield.framework.core.annotation.LazyConfiguration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.context.annotation.Bean;

import static com.springfield.framework.data.Constants.SPOTIFY_API_ENDPOINT;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

@LazyConfiguration
public class SpecBuilder {

    @Bean
    public RequestSpecification getRequestSpec(){
        RequestSpecification requestSpec = null;
        return requestSpec = new RequestSpecBuilder()
                .setBaseUri(SPOTIFY_API_ENDPOINT)
                .setBasePath("/v1")
                .setContentType(JSON)
                .log(ALL).build();
    }

    @Bean
    public ResponseSpecification getResponseSpec(){
        ResponseSpecification responseSpec = null;
        return responseSpec = new ResponseSpecBuilder()
                .log(ALL).build();
    }
}
