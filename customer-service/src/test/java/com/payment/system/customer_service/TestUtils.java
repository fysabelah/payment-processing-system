package com.payment.system.customer_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.File;

public class TestUtils {

    protected final ObjectMapper objectMapper;

    public TestUtils() {
        this.objectMapper = new ObjectMapper();
    }

    protected File getMock(String path) {
        return new File(path);
    }

    protected void assertJsonEquals(String expected, String actual) throws JSONException {
        JSONAssert.assertEquals(
                expected,
                actual,
                false);
    }
}
