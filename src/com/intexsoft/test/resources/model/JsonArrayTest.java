package com.intexsoft.test.resources.model;

import com.intexsoft.main.resourses.model.JsonArray;
import com.intexsoft.main.resourses.model.JsonBoolean;
import com.intexsoft.main.resourses.model.JsonNumber;
import com.intexsoft.main.resourses.model.JsonString;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class JsonArrayTest {
    JsonArray jsonArray = new JsonArray();

    @Test
    public void get() {
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonArray.add(0, jsonBoolean);
        Assert.assertSame(jsonArray.get(0), jsonBoolean);
    }

    @Test
    public void add() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonArray.add(jsonNumber);
        Assert.assertSame(jsonArray.get(0), jsonNumber);
    }

    @Test
    public void testAdd() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonArray.add(0, jsonNumber);
        Assert.assertSame(jsonArray.get(0), jsonNumber);
    }

    @Test
    public void getValues() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonArray.add(0, jsonNumber);
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonArray.add(0, jsonBoolean);
        Assert.assertSame(jsonArray.getValues(), jsonArray.values);
    }

    @Test
    public void size() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonArray.add(0, jsonNumber);
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonArray.add(0, jsonBoolean);
        JsonNumber jsonNumber1 = new JsonNumber();
        jsonArray.add(0, jsonNumber);
        JsonBoolean jsonBoolean1 = new JsonBoolean();
        jsonArray.add(0, jsonBoolean);
        Assert.assertSame(jsonArray.size(), 4);
    }

    @Test
    public void toStringTest() {
        JsonNumber jsonNumber = new JsonNumber();
        jsonNumber.setJsonNumber(12);
        jsonArray.add(0, jsonNumber);
        JsonBoolean jsonBoolean = new JsonBoolean();
        jsonBoolean.setJsonBoolean(true);
        jsonArray.add(1, jsonBoolean);
        JsonString jsonString = new JsonString();
        jsonString.setJsonString("test");
        jsonArray.add(2, jsonString);
        Assert.assertTrue(jsonArray.toString().equals("[12, true, \"test\"]"));
    }
}