package parser;

import model.*;

import java.util.*;
import java.util.List;

public class Mapper {
    public static <T> Object map(JsonNode jsonNode, Class<T> classType) {
        if (classType.isAssignableFrom(Boolean.class)||classType.isAssignableFrom(boolean.class)) {
            return ((JsonBoolean) jsonNode).getJsonBoolean();
        } else if (classType.isAssignableFrom(String.class)) {
            return ((JsonString) jsonNode).getJsonString();
        } else if (Number.class.isAssignableFrom(classType)) {
            return ((JsonNumber) jsonNode).getJsonNumber();
        } else if (Collection.class.isAssignableFrom(classType)) {
            List<JsonNode> jsonNodes = new ArrayList<>(((JsonArray) jsonNode).getValues());
            return jsonNodes;
        } else if (classType.isAssignableFrom(Arrays.class)) {
                          Object[] array = ((JsonArray) jsonNode).getValues().toArray(new Object[0]);
                          for(int i=0;i<array.length;i++){
                              if (array[i].getClass().isAssignableFrom(JsonBoolean.class)) {
                                  array[i] = map((JsonNode) array[i],Boolean.class );
                              } else if (array[i].getClass().isAssignableFrom(JsonString.class)) {
                                  array[i] = map((JsonNode) array[i],String.class );
                              } else if
                              (array[i].getClass().isAssignableFrom(JsonNumber.class)) {
                                  array[i] = map((JsonNode) array[i],Number.class );
                              } else if (Collection.class.isAssignableFrom(array[i].getClass())) {
                                  array[i] = map((JsonNode) array[i],Collection.class);
                              }else if(array[i].getClass().isAssignableFrom(Arrays.class)) {
                                  array[i] = map((JsonNode) array[i],Arrays.class );
                              }else array[i]=null;
                          }
                          return array;
        }
        return null;
    }

    public static void main(String[] args) {
        JsonBoolean jsonNode1 = new JsonBoolean();
        jsonNode1.setJsonBoolean(true);
        JsonString jsonNode2 = new JsonString();
        jsonNode2.setJsonString("some text for test");

        JsonNumber jsonNode3 = new JsonNumber();
        jsonNode3.setJsonNumber((int)12.12);

        JsonArray jsonNode4 = new JsonArray();
        jsonNode4.add(0, jsonNode1);
        jsonNode4.add(1, jsonNode2);
        jsonNode4.add(2, jsonNode3);
        Object arr [] =  (Object[]) map(jsonNode4, Arrays.class);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println();
    }

}
