package com.intexsoft.parser;

import com.intexsoft.model.*;
import com.intexsoft.utils.CollectionToArray;
import com.intexsoft.utils.exceptions.MappingObjectException;
import com.intexsoft.utils.exceptions.NoSuchEnumValue;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
public class Mapper {

    public <T> T map(JsonNode jsonNode, Class<T> classType) {
        if (classType.isAssignableFrom(Boolean.class) || classType.isAssignableFrom(boolean.class) || classType.isAssignableFrom(JsonBoolean.class)) {
            return (T) (Object) (((JsonBoolean) jsonNode).isJsonBoolean());
        } else if (classType.isAssignableFrom(String.class) || classType.isAssignableFrom(JsonString.class) || Enum.class.isAssignableFrom(classType)) {
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
            System.out.println("Mapping Object");
            T some = null;
            try {
                some = classType.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new MappingObjectException(e, " Mapping Object Exception ", classType);
            }
            try {
                Field[] fields = some.getClass().getDeclaredFields();
                Field field;
                Set<String> keys = ((JsonObject) jsonNode).values.keySet();
                Iterator keysIterator = keys.iterator();

                for (Field field1 : fields) {
                    try {
                        String currKey = (String) keysIterator.next();
                        field = some.getClass().getDeclaredField(currKey);
                        field.setAccessible(true);
                        try {
                            field.set(some, map(((JsonObject) jsonNode).get(currKey), (Class<T>) field.getGenericType()));

                        } catch (IllegalArgumentException | ClassCastException e) {

                            Object[] arr = field.getType().getEnumConstants();
                            String enumValue = String.valueOf(((JsonObject) jsonNode).values.get(field.getType().getSimpleName().toLowerCase()));
                            boolean foundEnumValue = false;
                            for (Object temp : arr) {
                                String tempEnumValue = "\"" + temp + "\"";
                                if (tempEnumValue.equals(enumValue)) {
                                    field.set(some, temp);
                                    foundEnumValue = true;
                                    break;

                                }

                            }
                            if (!foundEnumValue) {

                                throw new NoSuchEnumValue(field, "--------\nNo such Enum value\nList of possible enum values");
                            }
                        }
                        field.setAccessible(false);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new MappingObjectException(e, " Mapping object exception ", classType);
                    }
                }
            } catch (NullPointerException e) {
                throw new MappingObjectException(e, "Mapping object exception", classType);
            }
            return (T) some;
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
                            } catch (MappingObjectException e) {
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
                            } catch (MappingObjectException e) {
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
