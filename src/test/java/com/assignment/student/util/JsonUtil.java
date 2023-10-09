package com.assignment.student.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }
}