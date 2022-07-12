package com.intexsoft.test.resources.model;

import com.intexsoft.main.resourses.model.JsonNode;
import com.intexsoft.main.resourses.model.JsonNumber;
import com.intexsoft.main.resourses.model.JsonObject;
import com.intexsoft.main.resourses.model.JsonString;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonObjectTest {
    JsonObject jsonObject = new JsonObject();
    JsonString jsonString = new JsonString();
    JsonNumber jsonNumber = new JsonNumber();

    @Test
    public void get() {

        jsonString.setJsonString("Alexey");
        jsonObject.values.put("name", jsonString);
        jsonNumber.setJsonNumber(12);
        jsonObject.values.put("age", jsonNumber);
        Assert.assertSame(jsonObject.get("name"), jsonString);
        Assert.assertSame(jsonObject.get("age"), jsonNumber);
    }

    @Test
    public void add() {
        jsonString.setJsonString("Alexey");
        jsonObject.add("name", jsonString);
        jsonNumber.setJsonNumber(12);
        jsonObject.add("age", jsonNumber);
        Map<String, JsonNode> testMap = new HashMap<>();
        testMap.put("name", jsonString);
        testMap.put("age", jsonNumber);
        Assert.assertTrue(jsonObject.values.equals(testMap));
    }

    @Test
    public void size() {
        jsonObject.add("name", jsonString);
        jsonObject.add("age", jsonNumber);
        jsonObject.add("test", jsonString);
        jsonObject.add("string", jsonString);
        jsonObject.add("num", jsonNumber);
        Assert.assertSame(jsonObject.size(), 5);
    }

    @Test
    public void testToString() {
        jsonString.setJsonString("Alexey");
        jsonObject.add("name", jsonString);
        jsonNumber.setJsonNumber(12);
        jsonObject.add("age", jsonNumber);
        Assert.assertTrue(jsonObject.toString().equals("{\"name\":\"Alexey\", \"age\":12}"));
    }

    @Test
    public void getValues() {
        jsonString.setJsonString("Alexey");
        jsonObject.add("name", jsonString);
        jsonNumber.setJsonNumber(12);
        jsonObject.add("age", jsonNumber);
        Map<String, JsonNode> testMap = new HashMap<>();
        testMap.put("name", jsonString);
        testMap.put("age", jsonNumber);
        Assert.assertTrue(jsonObject.getValues().equals(testMap));
    }
}