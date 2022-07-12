package com.intexsoft.test.java.parser;

import com.intexsoft.main.java.parser.Adress;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class AdressTest {
    Adress adress = new Adress();

    @Test
    public void getHouseNumber() throws NoSuchFieldException, IllegalAccessException {
        Field field = adress.getClass().getDeclaredField("houseNumber");
        field.setAccessible(true);
        field.set(adress, 35);
        Assert.assertSame(adress.getHouseNumber(), 35);
    }

    @Test
    public void getStreet() throws NoSuchFieldException, IllegalAccessException {
        Field field = adress.getClass().getDeclaredField("street");
        field.setAccessible(true);
        field.set(adress, "Groove");
        Assert.assertSame(adress.getStreet(), "Groove");
    }

    @Test
    public void getCity() throws IllegalAccessException, NoSuchFieldException {
        Field field = adress.getClass().getDeclaredField("city");
        field.setAccessible(true);
        field.set(adress, Adress.City.Gomel);
        Assert.assertSame(adress.getCity(), Adress.City.Gomel);
    }

    @Test
    public void setHouseNumber() throws NoSuchFieldException, IllegalAccessException {
        adress.setHouseNumber(45);
        Field field = adress.getClass().getDeclaredField("houseNumber");
        field.setAccessible(true);
        Assert.assertSame(field.get(adress), 45);
    }

    @Test
    public void setStreet() throws NoSuchFieldException, IllegalAccessException {
        adress.setStreet("Groove");
        Field field = adress.getClass().getDeclaredField("street");
        field.setAccessible(true);
        Assert.assertSame(field.get(adress), "Groove");
    }

    @Test
    public void setCity() throws IllegalAccessException, NoSuchFieldException {
        adress.setCity(Adress.City.Minsk);
        Field field = adress.getClass().getDeclaredField("city");
        field.setAccessible(true);
        Assert.assertSame(field.get(adress), Adress.City.Minsk);
    }
}