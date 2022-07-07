import model.*;
import parser.JsonDeserializer;
import parser.Mapper;
import parser.User;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Mapper mapper = new Mapper();
        JsonDeserializer test = new JsonDeserializer("");
        //  System.out.println(test.parse("false"));
        JsonBoolean jsonNode1 = new JsonBoolean();
        jsonNode1.setJsonBoolean(true);
        // System.out.println(jsonNode1);
        JsonString jsonNode2 = new JsonString();
        jsonNode2.setJsonString("Alexey");
        // System.out.println(jsonNode2);
        JsonNumber jsonNode3 = new JsonNumber();
        jsonNode3.setJsonNumber((int) 20.4);
        JsonNull jsonNode6 = new JsonNull();
        //System.out.println(jsonNode3);
        JsonArray jsonNode4 = new JsonArray();
        //jsonNode4.add(0, jsonNode6);
        // jsonNode4.add(1, jsonNode6);
        //jsonNode4.add(2, jsonNode6);
        // System.out.println(jsonNode4);
        JsonArray jsonNode5 = new JsonArray();
        jsonNode5.add(0, jsonNode1);
        jsonNode5.add(1, jsonNode2);
        jsonNode5.add(2, jsonNode3);
        jsonNode5.add(3, jsonNode4);
        jsonNode5.add(4, jsonNode6);

        System.out.println(jsonNode5);
        LinkedList arr = mapper.map(jsonNode5, LinkedList.class);
        // System.out.println(arr);
        //System.out.println(arr.getClass());
        // System.out.println(arr[0]);
        System.out.println(arr.get(1));
        System.out.println(arr.get(2));
        System.out.println(arr.get(3));
        System.out.println(arr.get(4));
        //  System.out.println(arr.get(0).getClass());
        //   System.out.println(arr.get(1).getClass());
        //    System.out.println(arr.get(2).getClass());
        //  System.out.println(arr.get(3).getClass());
        System.out.println("-----------");
        User user = new User();
        JsonString jsonStreet = new JsonString();
        jsonStreet.setJsonString("Lenina");
        JsonNumber jsonHouseNumber = new JsonNumber();
        jsonHouseNumber.setJsonNumber(12);
        JsonObject jsonAdress = new JsonObject();
        jsonAdress.values.put("street", jsonStreet);
        jsonAdress.values.put("houseNumber", jsonHouseNumber);

        JsonObject jsonNode7 = new JsonObject();
        jsonNode7.values.put("ableToWork", jsonNode1);
        jsonNode7.values.put("name", jsonNode2);
        jsonNode7.values.put("adress", jsonAdress);
        jsonNode7.values.put("age", jsonNode3);

        user = mapper.map(jsonNode7, User.class);
        System.out.println(user.getName());
        System.out.println(user.getAge());
        System.out.println(user.getAbleToWork());
        System.out.println(user.getAdress().getStreet());
        System.out.println(user.getAdress().getHouseNumber());
    }
}