package main.java.com.intexsoft.serializer;

import main.java.com.intexsoft.model.*;
import main.java.com.intexsoft.utils.exceptions.IOFileException;
import main.java.com.intexsoft.utils.exceptions.JavaObjectToJsonNodeException;
import main.java.com.intexsoft.utils.exceptions.NoSuchFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;


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

    public void serialize(Object javaObject, File file) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

            JsonNode jsonNode = javaObjectToJsonNode(javaObject);
            if (jsonNode.getClass().isAssignableFrom(JsonNull.class)) {
                fileWriter.write(((JsonNull) jsonNode).toString());
            } else if (jsonNode.getClass().isAssignableFrom(JsonBoolean.class)) {
                fileWriter.write(((JsonBoolean) jsonNode).toString());
            } else if (jsonNode.getClass().isAssignableFrom(JsonString.class)) {
                fileWriter.write(((JsonString) jsonNode).toString());
            } else if (jsonNode.getClass().isAssignableFrom(JsonNumber.class)) {
                fileWriter.write(((JsonNumber) jsonNode).toString());
            } else if (jsonNode.getClass().isAssignableFrom(JsonArray.class)) {
                fileWriter.write(((JsonArray) jsonNode).toString());
            } else if (jsonNode.getClass().isAssignableFrom(JsonObject.class)) {
                fileWriter.write(((JsonObject) jsonNode).toString());
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new IOFileException(e,"Problem with writing to file ",file);
        }
    }
}
