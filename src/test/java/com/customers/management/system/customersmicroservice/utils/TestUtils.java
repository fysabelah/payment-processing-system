package com.customers.management.system.customersmicroservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.customers.management.system.customersmicroservice.CustomersMicroserviceApplication;
import jakarta.annotation.Resource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CustomersMicroserviceApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TestUtils {
    private static final String SOURCE_ROOT = "src/test/resources/mocks/";

    @Resource
    protected ObjectMapper objectMapper;

    protected File getFile(String path) {
        return new File(SOURCE_ROOT + path);
    }

    protected String getMock(String filename, Class<?> type) {
        File file = this.getFile(filename);

        try {
            Object object = objectMapper.readValue(file, type);

            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Não foi possível converter o objeto!", e);
        }
    }
}
