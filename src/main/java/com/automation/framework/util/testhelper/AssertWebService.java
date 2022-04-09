package com.automation.framework.util.testhelper;

import com.automation.framework.util.file.PathFinder;
import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.settings;

public class AssertWebService extends AbstractAssert<AssertWebService, Response> {

    public AssertWebService(Response response) {
        super(response, AssertWebService.class);
    }

    public static AssertWebService assertThat(Response response) {
        return new AssertWebService(response);
    }


    public AssertWebService hasValidSchema(String schemaFilename) {
        isNotNull();
        actual.then().body(matchesJsonSchema(new PathFinder().getFilePathForFile(schemaFilename).toFile())
                .using(settings));
        return this;
    }


    public AssertWebService hasValidStatusCode(int expectedHttpSatus) {
        isNotNull();
        actual.then().assertThat().statusCode(expectedHttpSatus)
                .log().all();
        return this;
    }


}
