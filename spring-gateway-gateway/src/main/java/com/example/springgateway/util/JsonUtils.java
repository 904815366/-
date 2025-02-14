package com.example.springgateway.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    /**
     * 简单的对象转 json-string
     */
    public static <T> String toJSONString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * json-string 转简单的对象
     */
    public static <T> T parseObject(String JSONString, Class<T> clazz) {
        if (!JSONString.startsWith("{") && !JSONString.endsWith("}"))
            throw new IllegalArgumentException();

        try {
            return new ObjectMapper().readValue(JSONString, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * json-string（数组）转简单的对象的集合。
     */
    public static <T> List<T> parseObjectList(String JSONString, Class<T> elementClass) {
        if (!JSONString.startsWith("[") && !JSONString.endsWith("]"))
            throw new IllegalArgumentException();

        JavaType javaType = getCollectionType(ArrayList.class, elementClass);
        try {
            return (List<T>) new ObjectMapper().readValue(JSONString, javaType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return new ObjectMapper()
                .getTypeFactory()
                .constructParametricType(collectionClass, elementClasses);
    }

}