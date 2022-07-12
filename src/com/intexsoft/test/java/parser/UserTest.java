package com.intexsoft.test.java.parser;

import com.intexsoft.main.java.parser.Adress;
import com.intexsoft.main.java.parser.User;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class UserTest {
    User user = new User();

    @Test
    public void getAbleToWork() throws NoSuchFieldException, IllegalAccessException {
        Field field = user.getClass().getDeclaredField("ableToWork");
        field.setAccessible(true);
        field.set(user, true);
        Assert.assertSame(user.getAbleToWork(), true);

    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Field field = user.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(user, "Alexey");
        Assert.assertSame(user.getName(), "Alexey");

    }

    @Test
    public void getAdress() throws NoSuchFieldException, IllegalAccessException {
        Field field = user.getClass().getDeclaredField("adress");
        field.setAccessible(true);
        Adress adress = new Adress();
        adress.setHouseNumber(12);
        adress.setCity(Adress.City.Grodno);
        adress.setStreet("Lenina");
        field.set(user, adress);
        Assert.assertSame(user.getAdress(), adress);
    }

    @Test
    public void getAge() throws NoSuchFieldException, IllegalAccessException {
        Field field = user.getClass().getDeclaredField("age");
        field.setAccessible(true);
        field.set(user, (int) 12.2);
        Assert.assertSame(user.getAge(), 12);
    }

    @Test
    public void setAbleToWork() throws NoSuchFieldException, IllegalAccessException {
        user.setAbleToWork(true);
        Field field = user.getClass().getDeclaredField("ableToWork");
        field.setAccessible(true);
        Assert.assertSame(field.get(user), true);
    }

    @Test
    public void setName() throws NoSuchFieldException, IllegalAccessException {
        user.setName("Alexey");
        Field field = user.getClass().getDeclaredField("name");
        field.setAccessible(true);
        Assert.assertSame(field.get(user), "Alexey");
    }

    @Test
    public void setAdress() throws NoSuchFieldException, IllegalAccessException {
        Adress adress = new Adress();
        adress.setStreet("Lenina");
        adress.setCity(Adress.City.Grodno);
        adress.setHouseNumber(12);
        user.setAdress(adress);
        Field field = user.getClass().getDeclaredField("adress");
        field.setAccessible(true);
        Assert.assertSame(field.get(user), adress);
    }

    @Test
    public void setAge() throws NoSuchFieldException, IllegalAccessException {
        user.setAge((int) 30.2);
        Field field = user.getClass().getDeclaredField("age");
        field.setAccessible(true);
        Assert.assertSame(field.get(user), 30);
    }
}