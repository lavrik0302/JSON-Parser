package utils;

import model.*;

import java.util.*;

import static parser.Mapper.map;

public class MappingCollection {
    public <T> T mappingCollection(Class<T> classType, JsonNode jsonNode) {
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
}
