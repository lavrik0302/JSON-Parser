package com.intexsoft.main.java;

import com.intexsoft.main.java.parser.Adress;
import com.intexsoft.main.java.parser.JsonDeserializer;
import com.intexsoft.main.java.parser.Mapper;
import com.intexsoft.main.java.parser.User;
import com.intexsoft.main.java.serializer.JsonSerializer;

public class Main {

    public static void main(String[] args) {
        Mapper mapper = new Mapper();
        var test = new JsonDeserializer("");
        var jsonSerializer = new JsonSerializer();
        User user = new User();
        Adress adress = new Adress();
        adress.setHouseNumber(12);
        adress.setCity(Adress.City.Grodno);
        adress.setStreet("Lenina");
        user.setAge(25);
        user.setName("Alexey");
        user.setAbleToWork(true);
        user.setAdress(adress);
        String string = "some text for test";
        Number number = 12;
        Boolean bool = true;
        Object nulll = null;
        Object[] arr = {string, bool, number, nulll};
        System.out.println(jsonSerializer.serialize(string));
        System.out.println(jsonSerializer.serialize(number));
        System.out.println(jsonSerializer.serialize(bool));
        System.out.println(jsonSerializer.serialize(arr));
        System.out.println(jsonSerializer.serialize(nulll));
        System.out.println(jsonSerializer.serialize(user));
        User userrr = mapper.map(JsonDeserializer.parse(jsonSerializer.serialize(user)), User.class);
        String json = jsonSerializer.serialize(userrr);
        System.out.println(json);
        User te = mapper.map(JsonDeserializer.parse(json), User.class);
        System.out.println(te.getAge());
        System.out.println(te.getAbleToWork());
        System.out.println(te.getName());
        System.out.println(te.getAdress().getCity());
        System.out.println(te.getAdress().getHouseNumber());
        System.out.println(te.getAdress().getStreet());

        System.out.println();
    }
}