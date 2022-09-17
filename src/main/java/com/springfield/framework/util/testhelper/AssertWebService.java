package com.springfield.framework.util.testhelper;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.SneakyThrows;
import org.assertj.core.api.AbstractAssert;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;

import static com.springfield.framework.util.file.FileReader.getJsonStringFromJsonFile;
import static com.springfield.framework.util.file.PathFinder.getFilePathForFile;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.settings;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.lessThan;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT_ORDER;

public class AssertWebService extends AbstractAssert<AssertWebService, Response> {

    public AssertWebService(Response response) {
        super(response, AssertWebService.class);
    }

    public static AssertWebService assertThat(Response response) {
        return new AssertWebService(response);
    }


    public AssertWebService hasValidStatusCode(int expectedHttpStatus) {
        isNotNull();
        actual.then().assertThat().statusCode(expectedHttpStatus);
        return this;
    }


    public AssertWebService hasValidJsonSchema(String schemaFilename) {
        isNotNull();
        actual.then()
                .body(matchesJsonSchema(getFilePathForFile(schemaFilename).toFile())
                        .using(settings));
        return this;
    }


    @SneakyThrows
    public AssertWebService hasValidJsonData(String jsonFilename) {
        isNotNull();
        String jsonString = getJsonStringFromJsonFile(jsonFilename);
        JSONAssert.assertEquals(jsonString, actual.then().extract().asPrettyString(), STRICT_ORDER);
        return this;
    }


    @SneakyThrows
    public AssertWebService hasValidJsonData(JSONObject jsonObject) {
        isNotNull();
        JSONAssert.assertEquals(jsonObject.toString(), actual.then().extract().asPrettyString(), STRICT_ORDER);
        return this;
    }


    public AssertWebService hasResponseTimeWithin(Long timeout) {
        isNotNull();
        ValidatableResponse validatableResponse = actual.then();
        validatableResponse.time(lessThan(timeout), SECONDS);
        return this;
    }


}

