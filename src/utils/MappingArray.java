package utils;

import model.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static parser.Mapper.map;


public class MappingArray {

    public <T> T mappingArray(Class<T> classType, JsonNode jsonNode) {
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
}
