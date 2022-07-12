package test.java.com.intexsoft.model;

import main.java.com.intexsoft.model.JsonString;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class JsonStringTest {
    JsonString jsonString = new JsonString();

    @Test
    public void getJsonString() throws IllegalAccessException, NoSuchFieldException {
        Field field = jsonString.getClass().getDeclaredField("jsonString");
        field.setAccessible(true);
        field.set(jsonString, "text for test");
        Assert.assertSame(jsonString.getJsonString(), "text for test");
    }

    @Test
    public void setJsonString() throws IllegalAccessException, NoSuchFieldException {
        jsonString.setJsonString("test text");
        Field field = jsonString.getClass().getDeclaredField("jsonString");
        field.setAccessible(true);
        Assert.assertSame(field.get(jsonString), "test text");
    }
}