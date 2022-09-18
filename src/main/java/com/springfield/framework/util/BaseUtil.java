package com.springfield.framework.util;

import com.github.javafaker.Faker;
import com.springfield.framework.core.annotation.LazyConfiguration;
import org.springframework.context.annotation.Bean;

@LazyConfiguration
public class BaseUtil {

    @Bean
    public Faker getFaker(){
        return new Faker();
    }


/*    @SneakyThrows
    public static JSONObject getSpecificJSONObjectFromGivenJSONArray(String jsonFilename, String searchID) {

        JSONArray jsonArray = getJSONArrayFromJsonFile(jsonFilename);
        JSONObject jsonObject = null;

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.get("patient_id").toString().equalsIgnoreCase(searchID)){
             break;
            }
        } return jsonObject;
    }*/
}
