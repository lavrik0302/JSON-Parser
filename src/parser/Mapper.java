package parser;


import model.*;
import utils.MappingArray;
import utils.MappingCollection;

import java.util.*;


public class Mapper {


    public static <T> T map(JsonNode jsonNode, Class<T> classType) {
        if (classType.isAssignableFrom(Boolean.class) || classType.isAssignableFrom(boolean.class)) {
            return (T) (Object) (((JsonBoolean) jsonNode).getJsonBoolean());
        } else if (classType.isAssignableFrom(String.class)) {
            return (T) ((JsonString) jsonNode).getJsonString();
        } else if (Number.class.isAssignableFrom(classType)) {
            return (T) ((JsonNumber) jsonNode).getJsonNumber();
        } else if (Collection.class.isAssignableFrom(classType)) {
            MappingCollection mappingCollection = new MappingCollection();
            return mappingCollection.mappingCollection(classType, jsonNode);
        } else if (classType.isArray()) {
            MappingArray mappingArray = new MappingArray();
            return mappingArray.mappingArray(classType, jsonNode);
        }
        return null;
    }

    public static void main(String[] args) {
        JsonBoolean jsonNode1 = new JsonBoolean();
        jsonNode1.setJsonBoolean(true);
        System.out.println(jsonNode1);
        JsonString jsonNode2 = new JsonString();
        jsonNode2.setJsonString("some text for test");
        System.out.println(jsonNode2);
        JsonNumber jsonNode3 = new JsonNumber();
        jsonNode3.setJsonNumber((int) 12.12);
        System.out.println(jsonNode3);
        JsonArray jsonNode4 = new JsonArray();
        jsonNode4.add(0, jsonNode3);
        jsonNode4.add(1, jsonNode3);
        jsonNode4.add(2, jsonNode3);
        System.out.println(jsonNode4.values);
        JsonArray jsonNode5 = new JsonArray();
        jsonNode5.add(0, jsonNode3);
        jsonNode5.add(1, jsonNode3);
        jsonNode5.add(2, jsonNode3);
        Integer[] arr = map(jsonNode5, Integer[].class);
        System.out.println(arr);
        System.out.println(arr.getClass());
        System.out.println(arr);
        //  System.out.println(arr.get(1));
        //   System.out.println(arr.get(2));
        // System.out.println(arr.get(0).getClass());
        //  System.out.println(arr.get(1).getClass());
        //  System.out.println(arr.get(2).getClass());
        System.out.println();
    }

}
