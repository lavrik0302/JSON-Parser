package com.intexsoft.test.java.parser;

import com.intexsoft.main.java.parser.Adress;
import com.intexsoft.main.java.parser.Mapper;
import com.intexsoft.main.resourses.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class MapperTest {
    Mapper mapper = new Mapper();
    JsonBoolean jsonBoolean = new JsonBoolean();
    JsonNumber jsonNumber = new JsonNumber();
    JsonString jsonString = new JsonString();
    JsonNull jsonNull = new JsonNull();

    @Test
    public void mappingBoolean() {
        jsonBoolean.setJsonBoolean(true);
        Assert.assertSame(mapper.map(jsonBoolean, Boolean.class), true);
    }

    @Test
    public void mappingNumber() {
        jsonNumber.setJsonNumber(12);
        Assert.assertSame(mapper.map(jsonNumber, Number.class), 12);
    }

    @Test
    public void mappingString() {
        jsonString.setJsonString("test text");
        Assert.assertSame(mapper.map(jsonString, String.class), "test text");
    }

    @Test
    public void mappingNull() {
        jsonNull.setJsonNull(null);
        Assert.assertSame(mapper.map(jsonNull, JsonNull.class), null);
    }

    @Test
    public void mappingArray() {
        JsonArray jsonArray = new JsonArray();
        jsonBoolean.setJsonBoolean(true);
        jsonString.setJsonString("test text");
        jsonNumber.setJsonNumber(12);
        jsonNull.setJsonNull(null);
        jsonArray.add(jsonBoolean);
        jsonArray.add(jsonString);
        jsonArray.add(jsonNumber);
        jsonArray.add(jsonNull);
        Object[] actual = {jsonBoolean.isJsonBoolean(), jsonString.getJsonString(), jsonNumber.getJsonNumber(), jsonNull.getJsonNull()};
        Object expected = mapper.map(jsonArray, JsonArray.class);
        Assert.assertSame(Array.get(expected, 0), actual[0]);
        Assert.assertSame(Array.get(expected, 1), actual[1]);
        Assert.assertSame(Array.get(expected, 2), actual[2]);
        Assert.assertSame(Array.get(expected, 3), actual[3]);
    }

    @Test
    public void mappingCollection() {
        JsonArray jsonArray = new JsonArray();
        jsonBoolean.setJsonBoolean(true);
        jsonString.setJsonString("test text");
        jsonNumber.setJsonNumber(12);
        jsonNull.setJsonNull(null);
        jsonArray.add(jsonBoolean);
        jsonArray.add(jsonString);
        jsonArray.add(jsonNumber);
        jsonArray.add(jsonNull);
        Object[] actual = {jsonBoolean.isJsonBoolean(), jsonString.getJsonString(), jsonNumber.getJsonNumber(), jsonNull.getJsonNull()};
        LinkedList<Object> expected = mapper.map(jsonArray, LinkedList.class);
        Assert.assertSame(expected.get(0), actual[0]);
        Assert.assertSame(expected.get(1), actual[1]);
        Assert.assertSame(expected.get(2), actual[2]);
        Assert.assertSame(expected.get(3), actual[3]);
    }

    @Test
    public void mappingObject() {
        JsonObject jsonObject = new JsonObject();
        jsonString.setJsonString("Grodno");
        jsonNumber.setJsonNumber(12);
        JsonString jsonString1 = new JsonString();
        jsonString1.setJsonString("Lenina");
        jsonObject.add("houseNumber", jsonNumber);
        jsonObject.add("street", jsonString1);
        jsonObject.add("city", jsonString);
        Adress actual = new Adress();
        actual.setCity(Adress.City.Grodno);
        actual.setHouseNumber(12);
        actual.setStreet("Lenina");
        Adress expected = mapper.map(jsonObject, Adress.class);
        Assert.assertTrue(expected.equals(actual));
    }

}