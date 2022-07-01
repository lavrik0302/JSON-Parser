package parser;

import model.*;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mapper {
    public static <T> Constable map(JsonNode jsonNode, Class<T> classType) {
        if (classType == Boolean.class) {
            return ((JsonBoolean) jsonNode).getJsonBoolean();
        } else if (classType == String.class) {
            return ((JsonString) jsonNode).getJsonString();
        } else if (classType == Number.class) {
            Number num = ((JsonNumber) jsonNode).getJsonNumber();
            return (Constable) num;
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

    }

}
