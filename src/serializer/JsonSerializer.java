package serializer;

import model.*;
import parser.User;
import utils.exceptions.JavaObjectToJsonNodeException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;

public class JsonSerializer {
    public JsonNode javaObjectToJsonNode(Object javaObject) {
        if (javaObject == null) {
            JsonNull jsonNull = new JsonNull();
            return jsonNull;
        } else if (javaObject.getClass().isAssignableFrom(String.class)) {
            JsonString jsonString = new JsonString();
            jsonString.setJsonString((String) javaObject);
            return jsonString;
        } else if (Number.class.isAssignableFrom(javaObject.getClass())) {
            JsonNumber jsonNumber = new JsonNumber();
            jsonNumber.setJsonNumber((Number) javaObject);
            return jsonNumber;
        } else if (javaObject.getClass().isAssignableFrom(Boolean.class)) {
            JsonBoolean jsonBoolean = new JsonBoolean();
            jsonBoolean.setJsonBoolean((Boolean) javaObject);
            return jsonBoolean;
        } else if (javaObject.getClass().isArray()) {
            JsonArray jsonArray = new JsonArray();
            for (int i = 0; i < Array.getLength(javaObject); i++) {
                jsonArray.add(javaObjectToJsonNode(Array.get(javaObject, i)));
            }
            return jsonArray;
        } else if (javaObject.getClass().isEnum()) {
            JsonString jsonString = new JsonString();
            jsonString.setJsonString(javaObject.toString());
            return jsonString;
        } else if (javaObject.getClass().isAssignableFrom(javaObject.getClass())) {
            JsonObject jsonObject = new JsonObject();
            Field[] fields = javaObject.getClass().getDeclaredFields();
            for (Field curr : fields) {
                curr.setAccessible(true);
                try {
                    jsonObject.add(curr.getName(), javaObjectToJsonNode(curr.get(javaObject)));
                } catch (IllegalAccessException e) {
                    throw new JavaObjectToJsonNodeException(e, "Problems with serializing custom Class object\n Object Class:  ", javaObject.getClass());
                }
                curr.setAccessible(false);
            }
            return jsonObject;
        }
        return null;
    }

    public String serialize(Object javaObject) {

        JsonNode jsonNode = javaObjectToJsonNode(javaObject);
        if (jsonNode.getClass().isAssignableFrom(JsonNull.class)) {
            return ((JsonNull) jsonNode).toString();
        } else if (jsonNode.getClass().isAssignableFrom(JsonBoolean.class)) {
            return ((JsonBoolean) jsonNode).toString();
        } else if (jsonNode.getClass().isAssignableFrom(JsonString.class)) {
            return ((JsonString) jsonNode).toString();
        } else if (jsonNode.getClass().isAssignableFrom(JsonNumber.class)) {
            return ((JsonNumber) jsonNode).toString();
        } else if (jsonNode.getClass().isAssignableFrom(JsonArray.class)) {
            return ((JsonArray) jsonNode).toString();
        } else if (jsonNode.getClass().isAssignableFrom(JsonObject.class)) {
            return ((JsonObject) jsonNode).toString();
        }
        return null;
    }
}
