package com.intexsoft.test.resources.model;

import com.intexsoft.main.resourses.model.JsonBoolean;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class JsonBooleanTest {
    JsonBoolean jsonBoolean = new JsonBoolean();

    @Test
    public void isJsonBoolean() throws NoSuchFieldException, IllegalAccessException {
        Field field = jsonBoolean.getClass().getDeclaredField("jsonBoolean");
        field.setAccessible(true);
        field.set(jsonBoolean, true);
        Assert.assertSame(jsonBoolean.isJsonBoolean(), true);
    }

    @Test
    public void setJsonBoolean() throws NoSuchFieldException, IllegalAccessException {
        jsonBoolean.setJsonBoolean(false);
        Field field = jsonBoolean.getClass().getDeclaredField("jsonBoolean");
        field.setAccessible(true);
        Assert.assertSame(field.get(jsonBoolean), false);
    }
}