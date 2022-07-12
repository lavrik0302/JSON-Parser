package com.intexsoft.test.resources.model;

import com.intexsoft.main.resourses.model.JsonNumber;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class JsonNumberTest {
    JsonNumber jsonNumber = new JsonNumber();

    @Test
    public void getJsonNumber() throws NoSuchFieldException, IllegalAccessException {
        Field field = jsonNumber.getClass().getDeclaredField("jsonNumber");
        field.setAccessible(true);
        field.set(jsonNumber, 35);
        Assert.assertSame(jsonNumber.getJsonNumber(), 35);
    }

    @Test
    public void setJsonNumber() throws NoSuchFieldException, IllegalAccessException {
        jsonNumber.setJsonNumber(45);
        Field field = jsonNumber.getClass().getDeclaredField("jsonNumber");
        field.setAccessible(true);
        Assert.assertSame(field.get(jsonNumber), 45);
    }
}