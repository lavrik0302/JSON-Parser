package com.intexsoft.test.resources.model;

import com.intexsoft.main.resourses.model.JsonNull;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class JsonNullTest {
    JsonNull jsonNull = new JsonNull();

    @Test
    public void getJsonNull() throws NoSuchFieldException, IllegalAccessException {
        Field field = jsonNull.getClass().getDeclaredField("jsonNull");
        field.setAccessible(true);
        field.set(jsonNull, null);
        Assert.assertSame(jsonNull.getJsonNull(), null);
    }

    @Test
    public void setJsonNull() throws NoSuchFieldException, IllegalAccessException {
        jsonNull.setJsonNull(null);
        Field field = jsonNull.getClass().getDeclaredField("jsonNull");
        field.setAccessible(true);
        Assert.assertSame(field.get(jsonNull), null);
    }
}