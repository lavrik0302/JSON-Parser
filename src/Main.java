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
        JsonBoolean jsonNode1 = new JsonBoolean();
        jsonNode1.setJsonBoolean(true);
        JsonString jsonNode2 = new JsonString();
        jsonNode2.setJsonString("Alexey");
        JsonNumber jsonNode3 = new JsonNumber();
        jsonNode3.setJsonNumber((int) 20.4);
        User user = new User();
        JsonString jsonStreet = new JsonString();
        jsonStreet.setJsonString("Lenina");
        JsonNumber jsonHouseNumber = new JsonNumber();
        jsonHouseNumber.setJsonNumber(12);
        JsonObject jsonAdress = new JsonObject();
        JsonString jsonCity=new JsonString();
        jsonCity.setJsonString("Grodno");

        jsonAdress.values.put("city",jsonCity);
        jsonAdress.values.put("street", jsonStreet);
        jsonAdress.values.put("houseNumber", jsonHouseNumber);


        JsonObject jsonNode7 = new JsonObject();
        jsonNode7.values.put("ableToWork", jsonNode1);
        jsonNode7.values.put("name", jsonNode2);
        jsonNode7.values.put("adress", jsonAdress);
        jsonNode7.values.put("age", jsonNode3);
        System.out.println(jsonNode7.values);
        user=mapper.map(jsonNode7, User.class);
        System.out.println(user.getName());
        System.out.println(user.getAge());
        System.out.println(user.getAbleToWork());
        System.out.println(user.getAdress().getCity());
        System.out.println(user.getAdress().getStreet());
        System.out.println(user.getAdress().getHouseNumber());

      //  User user = new User();
      //  Adress adress=new Adress();
       // adress.setHouseNumber(12);
       // adress.setStreet("Lenina");
       // adress.setCity(Adress.City.Grodno);
      //  System.out.println(adress.getCity());
       // user.setAge(15);
      //  user.setName("Alexey");
       // user.setAbleToWork(true);
       // user.setAdress(adress);
       // String string= "some text for test";
       // Number number=12;
       // Boolean bool=true;
       // Object nulll=null;
       // Object arr[]={string, bool,number,nulll};
       // System.out.println(jsonSerializer.serialize(string));
      //  System.out.println(jsonSerializer.serialize(number));
       // System.out.println(jsonSerializer.serialize(bool));
       // System.out.println(jsonSerializer.serialize(arr));
       // System.out.println(jsonSerializer.serialize(nulll));
       // System.out.println(jsonSerializer.serialize(user));
    }
}