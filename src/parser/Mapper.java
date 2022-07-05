package parser;


import model.*;
import utils.CollectionToArray;

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
            return mappingCollection(classType, jsonNode);
        } else if (classType.isArray()) {

            return mappingArray(classType, jsonNode);
        }
        return null;
    }

    private static <T> T mappingCollection(Class<T> classType, JsonNode jsonNode) {
        List<JsonNode> list = new ArrayList<>(((JsonArray) jsonNode).getValues());
        System.out.println(classType);
        java.util.List listOfJavaObjects = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Class nodeClass = list.get(i).getClass();
            JsonNode tempNode = list.get(i);
            if (nodeClass.isAssignableFrom(JsonBoolean.class)) {
                listOfJavaObjects.add(i, map(tempNode, Boolean.class));
            } else if (nodeClass.isAssignableFrom(JsonString.class)) {
                listOfJavaObjects.add(i, map(tempNode, String.class));
            } else if
            (nodeClass.isAssignableFrom(JsonNumber.class)) {
                listOfJavaObjects.add(i, map(tempNode, Number.class));
            } else if (Collection.class.isAssignableFrom(nodeClass)) {
                listOfJavaObjects.add(i, map(tempNode, Collection.class));
            } else if (nodeClass.isArray()) {
                listOfJavaObjects.add(i, map(tempNode, nodeClass));
            } else listOfJavaObjects.add(i, null);
        }
        System.out.println(listOfJavaObjects);
        Collection collection;
        if (classType.isAssignableFrom(LinkedList.class)) {
            collection = new LinkedList(listOfJavaObjects);
        } else if (classType.isAssignableFrom(ArrayList.class)) {
            collection = new ArrayList(listOfJavaObjects);
        } else if (classType.isAssignableFrom(Vector.class)) {
            collection = new Vector<>(listOfJavaObjects);
        } else if (classType.isAssignableFrom(Stack.class)) {
            collection = new Stack<>();
            collection.addAll(listOfJavaObjects);
        } else if (classType.isAssignableFrom(ArrayDeque.class)) {
            collection = new ArrayDeque(listOfJavaObjects);
        } else if (classType.isAssignableFrom(HashSet.class)) {
            collection = new HashSet<>(listOfJavaObjects);
        } else if (classType.isAssignableFrom(LinkedHashSet.class)) {
            collection = new LinkedHashSet<>(listOfJavaObjects);
        } else {
            collection = listOfJavaObjects;
        }
        return (T) collection;
    }


    private static <T> T mappingArray(Class<T> classType, JsonNode jsonNode) {
        List list = ((JsonArray) jsonNode).getValues();
        List listOfJavaObjects = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            Class nodeClass = list.get(i).getClass();
            JsonNode tempNode = (JsonNode) list.get(i);
            if (nodeClass.isAssignableFrom(JsonBoolean.class)) {
                listOfJavaObjects.add(i, map(tempNode, Boolean.class));
            } else if (nodeClass.isAssignableFrom(JsonString.class)) {
                listOfJavaObjects.add(i, map(tempNode, String.class));
            } else if
            (nodeClass.isAssignableFrom(JsonNumber.class)) {
                listOfJavaObjects.add(i, map(tempNode, Number.class));
            } else if (Collection.class.isAssignableFrom(nodeClass)) {
                listOfJavaObjects.add(i, map(tempNode, Collection.class));
            } else if (nodeClass.isArray()) {
                listOfJavaObjects.add(i, map(tempNode, nodeClass));
            } else listOfJavaObjects.add(i, null);
        }
        T array = (T) CollectionToArray.collectionToArray(classType.getComponentType(), listOfJavaObjects);
        return array;
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
        System.out.println(arr[0]);
        //  System.out.println(arr.get(1));
        //   System.out.println(arr.get(2));
        // System.out.println(arr.get(0).getClass());
        //  System.out.println(arr.get(1).getClass());
        //  System.out.println(arr.get(2).getClass());
        System.out.println();
    }

}
