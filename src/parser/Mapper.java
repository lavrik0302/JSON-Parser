package parser;

import model.*;

import java.lang.constant.Constable;
import java.util.*;
import java.util.List;

public class Mapper {
    public static <T> Object map(JsonNode jsonNode, Class<T> classType) {
        if (classType == Boolean.class) {
            return ((JsonBoolean) jsonNode).getJsonBoolean();
        } else if (classType == String.class) {
            return ((JsonString) jsonNode).getJsonString();
        } else if (classType == Number.class) {
            Number num = ((JsonNumber) jsonNode).getJsonNumber();
            return (Constable) num;
        } else if (classType == List.class) {
            List<JsonNode> jsonNodes = new ArrayList<>();
            jsonNodes.addAll(((JsonArray) jsonNode).getValues());
            return jsonNodes;
        }

        return null;
    }

    public static void main(String[] args) {
        JsonBoolean jsonNode1 = new JsonBoolean();
        jsonNode1.setJsonBoolean(true);
        System.out.println(map(jsonNode1, Boolean.class));

        JsonString jsonNode2 = new JsonString();
        jsonNode2.setJsonString("some text for test");
        System.out.println(map(jsonNode2, String.class));

        JsonNumber jsonNode3 = new JsonNumber();
        jsonNode3.setJsonNumber(1232);
        System.out.println(map(jsonNode3, Number.class));

        JsonArray jsonNode4 = new JsonArray();
        jsonNode4.add(0, jsonNode1);
        jsonNode4.add(1, jsonNode2);
        jsonNode4.add(2, jsonNode3);
        List<JsonNode> list = (List) map(jsonNode4, List.class);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }

}
