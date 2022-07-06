package parser;


import model.*;
import utils.CollectionToArray;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


public class Mapper {
    private JsonObject javaObject;
    private JsonObject javaObject1;
    private JsonObject javaObject11;

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
        } else if (classType.isAssignableFrom(JsonNull.class)) {
            return null;
        } else if (classType.isAssignableFrom(JsonObject.class) || classType.isAssignableFrom(Object.class) || classType.isAssignableFrom(classType)) {
            T some = null;
            try {
                some = classType.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            Field[] fields = some.getClass().getDeclaredFields();
            Field field;
            Set<String> keys = ((JsonObject) jsonNode).values.keySet();
            Iterator keysIterator = keys.iterator();
            for (int i = 0; i < fields.length; i++) {
                try {
                    String currKey = (String) keysIterator.next();
                    field = some.getClass().getDeclaredField(currKey);
                    field.set(some, map(((JsonObject) jsonNode).get(currKey), ((JsonObject) jsonNode).get(currKey).getClass()));

                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            return some;
        }
        return null;
    }

    private <T> T mappingCollection(JsonNode jsonNode, Class<T> classType) {
        List<Object> list = new ArrayList<>(((JsonArray) jsonNode).getValues());
        List listOfJavaObjects =
                list.stream()
                        .map((p) -> map((JsonNode) p, p.getClass()))
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
        List listOfJavaObjects =
                (List) list.stream()
                        .map((p) -> map((JsonNode) p, p.getClass()))
                        .collect(Collectors.toList());

        T array;
        try {
            array = (T) CollectionToArray.collectionToArray(listOfJavaObjects.get(0).getClass(), listOfJavaObjects);
        } catch (ArrayStoreException | NullPointerException | IndexOutOfBoundsException e) {
            array = (T) CollectionToArray.collectionToArray(Object.class, listOfJavaObjects);
        }
        return array;
    }

}
