package com.intexsoft.parser;

import com.intexsoft.model.*;
import com.intexsoft.utils.exceptions.MappingObjectException;
import com.intexsoft.utils.exceptions.NoSuchEnumValue;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class MapperTest {

    Mapper mapper = new Mapper();

    @Test
    public void mappingBooleanTest() {
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonBoolean.setJsonBoolean(true);
        Assert.assertSame(mapper.map(jsonBoolean, Boolean.class), true);
    }

    @Test
    public void mappingBooleanFailTest() {
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonBoolean.setJsonBoolean(false);
        Assert.assertNotSame(mapper.map(jsonBoolean, Boolean.class), true);
    }

    @Test
    public void mappingNumberTest() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonNumber.setJsonNumber(12);
        Assert.assertSame(mapper.map(jsonNumber, Number.class), 12);
    }

    @Test
    public void mappingNumberFailTest() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonNumber.setJsonNumber(12.34);
        Assert.assertNotSame(mapper.map(jsonNumber, Number.class), 12);
    }

    @Test
    public void mappingNullNumberTest() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonNumber.setJsonNumber(null);
        Assert.assertSame(mapper.map(jsonNumber, Number.class), null);
    }

    @Test
    public void mappingStringTest() {
        JsonString jsonString = new JsonString();
        jsonString.setJsonString("test text");
        Assert.assertSame(mapper.map(jsonString, String.class), "test text");
    }

    @Test
    public void mappingStringFailTest() {
        JsonString jsonString = new JsonString();
        jsonString.setJsonString("test text");
        Assert.assertNotSame(mapper.map(jsonString, String.class), "test     text");
    }

    @Test
    public void mappingNullStringTest() {
        JsonString jsonString = new JsonString();
        jsonString.setJsonString(null);
        Assert.assertSame(mapper.map(jsonString, String.class), null);
    }

    @Test
    public void mappingNullTest() {
        JsonNull jsonNull = new JsonNull();
        jsonNull.setJsonNull(null);
        Assert.assertSame(mapper.map(jsonNull, JsonNull.class), null);
    }

    @Test
    public void mappingArrayTest() {
        JsonNull jsonNull = new JsonNull();
        JsonString jsonString = new JsonString();
        JsonNumber jsonNumber = new JsonNumber();
        JsonArray jsonArray = new JsonArray();
        JsonBoolean jsonBoolean = new JsonBoolean();
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
    public void mappingArrayFailTest() {
        JsonString jsonString = new JsonString();
        JsonNumber jsonNumber = new JsonNumber();
        JsonArray jsonArray = new JsonArray();
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonBoolean.setJsonBoolean(false);
        jsonString.setJsonString("test       text");
        jsonNumber.setJsonNumber(12.34);
        jsonArray.add(jsonBoolean);
        jsonArray.add(jsonString);
        jsonArray.add(jsonNumber);
        Object[] actual = {true, "test text", 12};
        Object expected = mapper.map(jsonArray, JsonArray.class);
        Assert.assertNotSame(Array.get(expected, 0), actual[0]);
        Assert.assertNotSame(Array.get(expected, 1), actual[1]);
        Assert.assertNotSame(Array.get(expected, 2), actual[2]);
    }

    @Test
    public void mappingNullArrayTest() {
        JsonArray jsonArray = new JsonArray();
        Object array = mapper.map(jsonArray, JsonArray.class);
        Assert.assertSame(Array.getLength(array), 0);
    }

    @Test
    public void mappingCollectionTest() {
        JsonNull jsonNull = new JsonNull();
        JsonString jsonString = new JsonString();
        JsonNumber jsonNumber = new JsonNumber();
        JsonArray jsonArray = new JsonArray();
        JsonBoolean jsonBoolean = new JsonBoolean();
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
    public void mappingCollectionFailTest() {
        JsonString jsonString = new JsonString();
        JsonNumber jsonNumber = new JsonNumber();
        JsonArray jsonArray = new JsonArray();
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonBoolean.setJsonBoolean(true);
        jsonString.setJsonString("test text");
        jsonNumber.setJsonNumber(12);
        jsonArray.add(jsonBoolean);
        jsonArray.add(jsonString);
        jsonArray.add(jsonNumber);
        Object[] actual = {false, "test     text", 12.21};
        LinkedList<Object> expected = mapper.map(jsonArray, LinkedList.class);
        Assert.assertNotSame(expected.get(0), actual[0]);
        Assert.assertNotSame(expected.get(1), actual[1]);
        Assert.assertNotSame(expected.get(2), actual[2]);
    }

    @Test
    public void mappingObjectTest() {
        JsonString jsonString = new JsonString();
        JsonNumber jsonNumber = new JsonNumber();
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
    @Test(expected = MappingObjectException.class)
    public void wrongClassMappingObjectTest() {
        JsonString jsonString = new JsonString();
        JsonNumber jsonNumber = new JsonNumber();
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
        User expected = mapper.map(jsonObject, User.class);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test(expected = MappingObjectException.class)
    public void wrongFieldInMappingObjectTest() {
        JsonString jsonString = new JsonString();
        JsonNumber jsonNumber = new JsonNumber();
        JsonObject jsonObject = new JsonObject();
        jsonString.setJsonString("Grodno");
        jsonNumber.setJsonNumber(12);
        JsonString jsonString1 = new JsonString();
        jsonString1.setJsonString("Lenina");
        jsonObject.add("houseNumber", jsonNumber);
        jsonObject.add("streeeeet", jsonString1);
        jsonObject.add("city", jsonString);
        Adress actual = new Adress();
        actual.setCity(Adress.City.Grodno);
        actual.setHouseNumber(12);
        actual.setStreet("Lenina");
        Adress expected = mapper.map(jsonObject, Adress.class);
        Assert.assertTrue(expected.equals(actual));
    }

    @Test(expected = NoSuchEnumValue.class)
    public void wrongEnumInMappingObjectTest() {
        JsonString jsonString = new JsonString();
        JsonNumber jsonNumber = new JsonNumber();
        JsonObject jsonObject = new JsonObject();
        jsonString.setJsonString("Grodddno");
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

    @Test(expected = MappingObjectException.class)
    public void mappingNullObjectTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject = null;
        Adress expected = mapper.map(jsonObject, Adress.class);
    }

    @Test(expected = MappingObjectException.class)
    public void illegalAccessInMappingObjectTest() {
        @Data
        class Human {
            private static final String name = "Diana";
        }
        JsonObject jsonObject = new JsonObject();
        JsonString jsonString = new JsonString();
        jsonString.setJsonString("Alexey");
        jsonObject.add("name", jsonString);
        Human human = mapper.map(jsonObject, Human.class);
    }

    @Test(expected = MappingObjectException.class)
    public void wrongArgumentInMappingObjectTest() {
        JsonString jsonString = new JsonString();
        JsonNumber jsonNumber = new JsonNumber();
        JsonObject jsonObject = new JsonObject();
        jsonString.setJsonString("Grodno");
        jsonNumber.setJsonNumber(12);
        jsonObject.add("houseNumber", jsonNumber);
        jsonObject.add("street", jsonNumber);
        jsonObject.add("city", jsonString);
        Adress actual = new Adress();
        actual.setCity(Adress.City.Grodno);
        actual.setHouseNumber(12);
        actual.setStreet("Lenina");
        Adress expected = mapper.map(jsonObject, Adress.class);
        Assert.assertTrue(expected.equals(actual));
    }
}