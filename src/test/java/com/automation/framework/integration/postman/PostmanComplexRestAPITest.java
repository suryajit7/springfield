package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.data.entity.app.postman.Postman;
import com.automation.framework.util.FileReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.automation.framework.data.Constants.*;
import static io.restassured.RestAssured.*;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PostmanComplexRestAPITest extends AutomationSuiteApplicationTests {

    @Autowired
    private FileReader fileReader;

    private ResponseSpecification customResponseSpecification;
    private String workspaceId;
    private Postman payLoad;

    @BeforeAll
    public void beforeClassSetup() {

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(postmanMockServerUrl)
                .addHeader(X_API_KEY_HEADER, apiKey)
                .addHeader(X_MOCK_MATCH_REQUEST_BODY, "true")
                .setContentType(JSON)
                .log(ALL)
                //.setConfig(config.encoderConfig(encoderConfig().encodeContentTypeAs("Content-type:application/json", ContentType.JSON)))
                .build();


        customResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(SC_SUCCESS)
                .expectContentType(JSON)
                .log(ALL)
                .build();

        payLoad = fileReader.readJsonFile("postmanWorkspaceData.json")
                .getPostmen().get(0);
    }


    @Test
    @Order(1)
    public void validatePostRequestWithJsonArrayList() {

        HashMap<String, String> object5001 = new HashMap<String, String>();
        HashMap<String, String> object5002 = new HashMap<String, String>();

        object5001.put("id", "5001");
        object5001.put("type", "None");

        object5002.put("id", "5001");
        object5002.put("type", "Glazed");

        List<HashMap> jsonList = new ArrayList<HashMap>();
        jsonList.add(object5001);
        jsonList.add(object5002);

        given()
                .body(jsonList)
                .when()
                .post("/post")
                .then().spec(customResponseSpecification)
                .log().all()
                .assertThat()
                .body("msg", containsString("Pong"));
    }

    @Test
    @Order(2)
    public void validateComplexPayloadForPostRequest(){
        List<Integer> idArrayList = new ArrayList<Integer>();
        idArrayList.add(5);
        idArrayList.add(9);

        HashMap<String, Object> batterHashMap2 = new HashMap<String, Object>();
        batterHashMap2.put("id", idArrayList);
        batterHashMap2.put("type", "Chocolate");

        HashMap<String, Object> batterHashMap1 = new HashMap<String, Object>();
        batterHashMap1.put("id", "1001");
        batterHashMap1.put("type", "Regular");

        List<HashMap<String, Object>> batterArrayList = new ArrayList<HashMap<String, Object>>();
        batterArrayList.add(batterHashMap1);
        batterArrayList.add(batterHashMap2);

        HashMap<String, List<HashMap<String, Object>>> batterHashMap = new HashMap<String, List<HashMap<String, Object>>>();
        batterHashMap.put("batter", batterArrayList);

        List<String> typeArrayList = new ArrayList<String>();
        typeArrayList.add("test1");
        typeArrayList.add("test2");

        HashMap<String, Object> toppingHashMap2 = new HashMap<String, Object>();
        toppingHashMap2.put("id", "5002");
        toppingHashMap2.put("type", typeArrayList);

        HashMap<String, Object> toppingHashMap1 = new HashMap<String, Object>();
        toppingHashMap1.put("id", "5001");
        toppingHashMap1.put("type", "None");

        List<HashMap<String, Object>> toppingArrayList = new ArrayList<HashMap<String, Object>>();
        toppingArrayList.add(toppingHashMap1);
        toppingArrayList.add(toppingHashMap2);

        HashMap<String, Object> mainHashMap = new HashMap<String, Object>();
        mainHashMap.put("id", "0001");
        mainHashMap.put("type", "donut");
        mainHashMap.put("name", "Cake");
        mainHashMap.put("ppu", 0.55);
        mainHashMap.put("batters", batterHashMap);
        mainHashMap.put("topping", toppingArrayList);

        given()
                .body(mainHashMap)
                .when()
                .post("/ping")
                .then()
                .spec(customResponseSpecification)
                .assertThat()
                .body("msg", equalTo("Success"));

    }


    @Test
    @Order(3)
    public void verifyComplexPayloadWithPostRequest(){

        HashMap<String, Object> codeHashMap1 = new HashMap<String, Object>();
        List<Integer> rgbaList1 = List.of(255, 255, 255, 1);
        codeHashMap1.put("rgba", rgbaList1);
        codeHashMap1.put("hex", "#000");

        HashMap<String, Object> colorDetailsHashMap1 = new HashMap<String, Object>();

        colorDetailsHashMap1.put("color", "black");
        colorDetailsHashMap1.put("category", "hue");
        colorDetailsHashMap1.put("type", "primary");
        colorDetailsHashMap1.put("code", codeHashMap1);


        HashMap<String, Object> codeHashMap2 = new HashMap<String, Object>();
        List<Integer> rgbaList2 = List.of(0, 0, 0, 1);
        codeHashMap2.put("rgba", rgbaList2);
        codeHashMap2.put("hex", "#FFF");

        HashMap<String, Object> colorDetailsHashMap2 = new HashMap<String, Object>();

        colorDetailsHashMap2.put("color", "white");
        colorDetailsHashMap2.put("category", "value");
        colorDetailsHashMap2.put("code", codeHashMap2);

        List<HashMap<String, Object>> mainColorsList = new ArrayList<HashMap<String, Object>>();
        mainColorsList.add(colorDetailsHashMap1);
        mainColorsList.add(colorDetailsHashMap2);

        HashMap<String, List<HashMap<String, Object>>> mainColorsMap = new HashMap<String, List<HashMap<String, Object>>>();
        mainColorsMap.put("colors", mainColorsList);

        given()
                .body(mainColorsMap)
                .when()
                .post("/colors")
                .then()
                .spec(customResponseSpecification)
                .assertThat()
                .body("msg", equalTo("Colors"));

    }

}
