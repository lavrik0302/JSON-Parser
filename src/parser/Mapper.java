package parser;


import model.*;
import utils.CollectionToArray;

import java.util.*;
import java.util.stream.Collectors;


public class Mapper {
    public <T> T map(JsonNode jsonNode, Class<T> classType) {
        if (classType.isAssignableFrom(Boolean.class) || classType.isAssignableFrom(boolean.class) || classType.isAssignableFrom(JsonBoolean.class)) {
            return (T) (Object) (((JsonBoolean) jsonNode).getJsonBoolean());
        } else if (classType.isAssignableFrom(String.class) || classType.isAssignableFrom(JsonString.class)) {
            return (T) ((JsonString) jsonNode).getJsonString();
        } else if (Number.class.isAssignableFrom(classType) || classType.isAssignableFrom(JsonNumber.class)) {
            return (T) ((JsonNumber) jsonNode).getJsonNumber();
        } else if (Collection.class.isAssignableFrom(classType)) {
            return mappingCollection(jsonNode, classType);
        } else if (classType.isArray() || classType.isAssignableFrom(JsonArray.class)) {
            return mappingArray(jsonNode, classType);
        }
        return null;
    }

    private <T> T mappingCollection(JsonNode jsonNode, Class<T> classType) {
        List<Object> list = new ArrayList<>(((JsonArray) jsonNode).getValues());
        List listOfJavaObjects =
                list.stream()
                        .map((p) -> (map((JsonNode) p, p.getClass())))
                        .collect(Collectors.toList());
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

    private <T> T mappingArray(JsonNode jsonNode, Class<T> classType) {
        List list = ((JsonArray) jsonNode).getValues();
        List listOfJavaObjects = new ArrayList();
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
        T array;
        try {
            array = (T) CollectionToArray.collectionToArray(listOfJavaObjects.get(0).getClass(), listOfJavaObjects);
        } catch (ArrayStoreException e) {
            System.out.println("Array of Objects");
            array = (T) CollectionToArray.collectionToArray(Object.class, listOfJavaObjects);
        }

        return array;
    }

}
