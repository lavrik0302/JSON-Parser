import model.*;
import parser.Adress;
import parser.JsonDeserializer;
import parser.Mapper;
import parser.User;
import serializer.JsonSerializer;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Mapper mapper = new Mapper();
        JsonDeserializer test = new JsonDeserializer("");
        JsonSerializer jsonSerializer= new JsonSerializer();

        User user = new User();
        Adress adress=new Adress();
        adress.setHouseNumber(12);
        adress.setStreet("Lenina");
        adress.setCity(Adress.City.Minsk);
        user.setAge(20);
       user.setName("Alexey");
        user.setAbleToWork(true);
      user.setAdress(adress);
        String string= "some text for test";
        Number number=12;
       Boolean bool=true;
       Object nulll=null;
        Object arr[]={string, bool,number,nulll};
        System.out.println(jsonSerializer.serialize(string));
       System.out.println(jsonSerializer.serialize(number));
        System.out.println(jsonSerializer.serialize(bool));
        System.out.println(jsonSerializer.serialize(arr));
       System.out.println(jsonSerializer.serialize(nulll));
        System.out.println(jsonSerializer.serialize(user));
    }
}