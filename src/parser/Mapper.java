package parser;


import model.*;
import utils.CollectionToArray;
import utils.exceptions.AccessException;
import utils.exceptions.NewInstanceException;
import utils.exceptions.NoFieldException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


public class Mapper {

    public <T> T map(JsonNode jsonNode, Class<T> classType) throws NoFieldException, AccessException, NewInstanceException {
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
        } else if (classType.isAssignableFrom(JsonObject.class) || classType.isAssignableFrom(classType)) {
            T some = null;
            System.out.println("trying to map jsonObject");
            try {
                some = classType.newInstance();
                System.out.println("after new instance" + some.getClass());
            } catch (InstantiationException e) {
                throw new NewInstanceException("Can't create a new instance of ", classType);
            } catch (IllegalAccessException e) {
                throw new AccessException("Illegal access");
            }

            Field[] fields = some.getClass().getDeclaredFields();
            Field field;
            Set<String> keys = ((JsonObject) jsonNode).values.keySet();
            System.out.println("keys of map " + keys);

            Iterator keysIterator = keys.iterator();
            for (int i = 0; i < fields.length; i++) {
                try {
                    String currKey = (String) keysIterator.next();
                    field = some.getClass().getDeclaredField(currKey);
                    field.setAccessible(true);
                    System.out.println("Текущее поле " + field);
                    System.out.println("Текущий ключ карты " + currKey);
                    System.out.println("Тип поля " + fields[i].getGenericType());
                    field.set(some, map(((JsonObject) jsonNode).get(currKey), (Class<T>) fields[i].getGenericType()));
                    field.setAccessible(false);
                } catch (NoSuchFieldException e) {
                    throw new NoFieldException("No such field ", fields[i]);
                } catch (IllegalAccessException e) {
                    throw new AccessException("Illegal access");
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
                        .map((p) -> {
                            try {
                                return map((JsonNode) p, p.getClass());
                            } catch (NoFieldException | AccessException |
                                     NewInstanceException e) {
                                throw new RuntimeException(e);
                            }
                        })
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
                        .map((p) -> {
                            try {
                                return map((JsonNode) p, p.getClass());
                            } catch (NoFieldException | AccessException |
                                     NewInstanceException e) {
                                throw new RuntimeException(e);
                            }
                        })
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
