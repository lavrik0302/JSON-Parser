package com.intexsoft.test.java.serializer;

import com.intexsoft.main.java.parser.Adress;
import com.intexsoft.main.java.serializer.JsonSerializer;
import com.intexsoft.main.resourses.model.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonSerializerTest {
    JsonSerializer jsonSerializer = new JsonSerializer();
    JsonBoolean jsonBoolean = new JsonBoolean();
    JsonString jsonString = new JsonString();
    JsonNull jsonNull = new JsonNull();
    JsonNumber jsonNumber = new JsonNumber();

    @Test
    public void javaBooleanToJsonNode() {
        jsonBoolean.setJsonBoolean(true);
        JsonBoolean expected = (JsonBoolean) jsonSerializer.javaObjectToJsonNode(true);
        Assert.assertTrue(expected.equals(jsonBoolean));
    }

    @Test
    public void javaNumberToJsonNode() {
        jsonNumber.setJsonNumber(12);
        JsonNumber expected = (JsonNumber) jsonSerializer.javaObjectToJsonNode(12);
        Assert.assertTrue(expected.equals(jsonNumber));
    }

    @Test
    public void javaStringToJsonNode() {
        jsonString.setJsonString("text for test");
        JsonString expected = (JsonString) jsonSerializer.javaObjectToJsonNode("text for test");
        Assert.assertTrue(expected.equals(jsonString));
    }

    @Test
    public void javaNullToJsonNode() {
        jsonNull.setJsonNull(null);
        JsonNull expected = (JsonNull) jsonSerializer.javaObjectToJsonNode(null);
        Assert.assertTrue(expected.equals(jsonNull));
    }

    @Test
    public void javaArrayToJsonNode() {
        JsonArray jsonArray = new JsonArray();
        jsonBoolean.setJsonBoolean(true);
        jsonNumber.setJsonNumber(12);
        jsonString.setJsonString("text for test");
        jsonNull.setJsonNull(null);
        jsonArray.add(jsonBoolean);
        jsonArray.add(jsonNumber);
        jsonArray.add(jsonString);
        jsonArray.add(jsonNull);
        Object[] actual = {true, 12, "text for test", null};
        JsonArray expected = (JsonArray) jsonSerializer.javaObjectToJsonNode(actual);
        Assert.assertTrue(expected.equals(jsonArray));
    }

    @Test
    public void serializeNull() {
        Assert.assertTrue(jsonSerializer.serialize(null).equals("null"));
    }

    @Test
    public void serializeString() {
        Assert.assertTrue(jsonSerializer.serialize("text for test").equals("\"text for test\""));
    }

    @Test
    public void serializeBoolean() {
        Assert.assertTrue(jsonSerializer.serialize(true).equals("true"));
    }

    @Test
    public void serializeNumber() {
        Assert.assertTrue(jsonSerializer.serialize(123).equals("123"));
    }

    @Test
    public void serializeArray() {
        Object array[] = {true, "text", 3, null};
        Assert.assertTrue(jsonSerializer.serialize(array).equals("[true, \"text\", 3, null]"));
    }

    @Test
    public void serializeObject() {
        Adress adress = new Adress();
        adress.setStreet("Lenina");
        adress.setCity(Adress.City.Minsk);
        adress.setHouseNumber(12);
        Assert.assertTrue(jsonSerializer.serialize(adress).equals("{\"city\":\"Minsk\", \"street\":\"Lenina\", \"houseNumber\":12}"));
    }
}