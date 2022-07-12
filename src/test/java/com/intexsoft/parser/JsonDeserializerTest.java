package test.java.com.intexsoft.parser;

import main.java.com.intexsoft.model.*;
import main.java.com.intexsoft.parser.JsonDeserializer;
import main.java.com.intexsoft.utils.exceptions.InvalidJsonException;
import org.junit.Assert;
import org.junit.Test;

public class JsonDeserializerTest {
    @Test
    public void parseStringTest() {
        JsonString jsonString = new JsonString();
        jsonString.setJsonString("some text");
        Assert.assertTrue(jsonString.equals(((JsonString) (JsonDeserializer.parse("\"some text\"")))));
    }

    @Test(expected = InvalidJsonException.class)
    public void parseWrongStringTest() {
        JsonDeserializer.parse("\"some text");
    }

    @Test
    public void parseEmptyStringTest() {
        JsonString jsonString = new JsonString();
        jsonString.setJsonString("");
        Assert.assertTrue(jsonString.equals(((JsonString) (JsonDeserializer.parse("\"\"")))));
    }

    @Test
    public void parseNumberTest() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonNumber.setJsonNumber(15);
        Assert.assertTrue(jsonNumber.equals(((JsonNumber) (JsonDeserializer.parse("15")))));
    }

    @Test
    public void parseDoubleNumberTest() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonNumber.setJsonNumber(15.123456);
        Assert.assertTrue(jsonNumber.equals(((JsonNumber) (JsonDeserializer.parse("15.123456")))));
    }

    @Test(expected = InvalidJsonException.class)
    public void parseWrongNumberTest() {
        JsonDeserializer.parse("15qwe");
    }

    @Test
    public void parseBooleanTest() {
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonBoolean.setJsonBoolean(true);
        Assert.assertTrue(jsonBoolean.equals(((JsonBoolean) (JsonDeserializer.parse("true")))));
    }

    @Test(expected = InvalidJsonException.class)
    public void parseWrongBooleanTest() {
        JsonDeserializer.parse("TRUE");
    }

    @Test
    public void parseArrayTest() {
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

    @Test(expected = InvalidJsonException.class)
    public void parseWrongArrayTest() {
        JsonDeserializer.parse("[123,123,123");
    }


    @Test
    public void parseObjectTest() {
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

    @Test(expected = InvalidJsonException.class)
    public void parseWrongObjectTest() {
        JsonDeserializer.parse("{\"ableToWork\":true, languages\":[\"Russian\", \"English\"], \"name\":\"Alexey\", \"age\":12}");
    }
}
