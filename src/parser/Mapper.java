package parser;

import model.*;

import java.util.*;
import java.util.List;

public class Mapper {
    public static <T> Object map(JsonNode jsonNode, Class<T> classType) {
        if (classType.isAssignableFrom(Boolean.class)) {
            return ((JsonBoolean) jsonNode).getJsonBoolean();
        } else if (classType.isAssignableFrom(String.class)) {
            return ((JsonString) jsonNode).getJsonString();
        } else if (classType.isAssignableFrom(Number.class)) {
            return ((JsonNumber) jsonNode).getJsonNumber();
        } else if (Collection.class.isAssignableFrom(classType)||classType.isAssignableFrom(Arrays.class)) {
            List<JsonNode> jsonNodes = new ArrayList<>(((JsonArray) jsonNode).getValues());
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
        List<JsonNode> list = (List<JsonNode>) map(jsonNode4, List.class);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println();
    }

}
