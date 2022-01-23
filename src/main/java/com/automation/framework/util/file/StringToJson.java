package com.automation.framework.util.file;

import com.automation.framework.core.annotation.LazyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.List;

@LazyService
public class StringToJson {

    @SneakyThrows
    public static <T> T toJson(String jsonString, Class<T> type) {
        return type.cast(new ObjectMapper().readValue(jsonString, type));
    }

    @SneakyThrows
    public static <T> List<T> convertJsonStringToObjectList(String jsonString, Class<T> valueType) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().
                constructCollectionType(List.class, valueType);
        return mapper.readValue(jsonString, type);
    }

    public static String getPrettyJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(obj);
    }

    public static String getJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }


}
