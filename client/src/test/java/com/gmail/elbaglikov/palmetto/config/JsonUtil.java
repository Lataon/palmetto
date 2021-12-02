package com.gmail.elbaglikov.palmetto.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gmail.elbaglikov.palmetto.model.Order;

import java.io.IOException;

public class JsonUtil {
    public static String toJson(Order order) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
        return mapper.writeValueAsString(order);
    }
}
