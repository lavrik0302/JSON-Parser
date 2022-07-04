package parser;

import model.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.List;


public class Mapper {


    public static <T> T[] listToArray(Class<T> tClass, List list) {
        T[] elements=(T[]) java.lang.reflect.Array.newInstance(tClass,list.size());
        System.out.println(elements.getClass());
        for (int i = 0; i < list.size(); i++) {
            elements[i]=(T) list.get(i);
        }
        return elements;
    }

    public static <T> T map(JsonNode jsonNode, Class<T> classType) {
        if (classType.isAssignableFrom(Boolean.class) || classType.isAssignableFrom(boolean.class)) {
            return (T) (Object) (((JsonBoolean) jsonNode).getJsonBoolean());
        } else if (classType.isAssignableFrom(String.class)) {
            return (T) ((JsonString) jsonNode).getJsonString();
        } else if (Number.class.isAssignableFrom(classType)) {
            return (T) ((JsonNumber) jsonNode).getJsonNumber();
        } else if (Collection.class.isAssignableFrom(classType)) {
            List<JsonNode> jsonNodes = new ArrayList<>(((JsonArray) jsonNode).getValues());
            return (T) jsonNodes;
        } else if (classType.isArray()) {
            List list = ((JsonArray) jsonNode).values;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getClass().isAssignableFrom(JsonBoolean.class)) {
                    list.set(i, map((JsonNode) list.get(i), Boolean.class));
                } else if (list.get(i).getClass().isAssignableFrom(JsonString.class)) {
                    list.set(i, map((JsonNode) list.get(i), String.class));
                } else if
                (list.get(i).getClass().isAssignableFrom(JsonNumber.class)) {
                    list.set(i, map((JsonNode) list.get(i), Number.class));
                } else if (Collection.class.isAssignableFrom(list.get(i).getClass())) {
                    list.set(i, map((JsonNode) list.get(i), Collection.class));
                } else if (list.get(i).getClass().isAssignableFrom(Arrays.class)) {
                    list.set(i, map((JsonNode) list.get(i), Arrays.class));
                } else list.set(i, null);
            }
            T array= (T) listToArray(list.get(0).getClass(),list);

            System.out.println(array.getClass());

            return array;
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
        jsonNode5.add(0, jsonNode3);
        jsonNode5.add(0, jsonNode3);
        Integer[] arr = map(jsonNode5, Integer[].class);
        System.out.println(arr.getClass());
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
     //   System.out.println(arr[0].getClass());
     //   System.out.println(arr[1].getClass());
      //  System.out.println(arr[2].getClass());
        System.out.println();
    }

}
