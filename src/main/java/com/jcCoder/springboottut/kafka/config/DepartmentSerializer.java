package com.jcCoder.springboottut.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcCoder.springboottut.entity.Department;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class DepartmentSerializer implements Serializer<Department>{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No configuration needed
    }

    @Override
    public byte[] serialize(String topic, Department data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing Department object to JSON", e);
        }
    }

    @Override
    public void close() {
        // No resources to close
    }
}
