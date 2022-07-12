package com.intexsoft.test.java.parser;

import com.intexsoft.main.resourses.model.*;
import com.intexsoft.main.java.parser.JsonDeserializer;
import org.junit.Assert;
import org.junit.Test;

public class JsonDeserializerTest {
    @Test
    public void parseString() {
        JsonString jsonString = new JsonString();
        jsonString.setJsonString("some text");
        Assert.assertTrue(jsonString.equals(((JsonString) (JsonDeserializer.parse("\"some text\"")))));
    }

    @Test
    public void parseNumber() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonNumber.setJsonNumber(15);
        Assert.assertTrue(jsonNumber.equals(((JsonNumber) (JsonDeserializer.parse("15")))));
    }

    @Test
    public void parseBoolean() {
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonBoolean.setJsonBoolean(true);
        Assert.assertTrue(jsonBoolean.equals(((JsonBoolean) (JsonDeserializer.parse("true")))));
    }

    @Test
    public void parseArray() {
        JsonArray jsonArray = new JsonArray();
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonBoolean.setJsonBoolean(true);
        JsonNumber jsonNumber = new JsonNumber();
        jsonNumber.setJsonNumber(15);
        JsonString jsonString = new JsonString();
        jsonString.setJsonString("some text");
        jsonArray.add(0, jsonBoolean);
        jsonArray.add(1, jsonNumber);
        jsonArray.add(2, jsonString);
        Assert.assertTrue(jsonArray.equals(((JsonArray) (JsonDeserializer.parse("[true, 15, \"some text\"]")))));
    }

    @Test
    public void parseObject() {
        JsonObject jsonObject = new JsonObject();
        JsonString jsonString = new JsonString();
        JsonBoolean jsonBoolean = new JsonBoolean();
        JsonNumber jsonNumber = new JsonNumber();
        JsonArray jsonArray = new JsonArray();
        JsonString jsonString1 = new JsonString();
        JsonString jsonString2 = new JsonString();
        jsonBoolean.setJsonBoolean(true);
        jsonNumber.setJsonNumber(12);
        jsonString.setJsonString("Alexey");
        jsonString1.setJsonString("Russian");
        jsonString2.setJsonString("English");
        jsonArray.add(0, jsonString1);
        jsonArray.add(1, jsonString2);


        jsonObject.add("ableToWork", jsonBoolean);
        jsonObject.add("languages", jsonArray);
        jsonObject.add("name", jsonString);
        jsonObject.add("age", jsonNumber);
        Assert.assertTrue(jsonObject.equals(JsonDeserializer.parseObject("{\"ableToWork\":true, \"languages\":[\"Russian\", \"English\"], \"name\":\"Alexey\", \"age\":12}")));
    }
}