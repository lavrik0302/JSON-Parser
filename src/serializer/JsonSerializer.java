package serializer;

import model.*;

import java.lang.reflect.Array;
import java.util.List;

public class JsonSerializer {
public JsonNode javaObjectToJsonNode(Object javaObject){
    // System.out.println(javaObject.getClass());
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
    } else if (javaObject.getClass().isArray()){
        JsonArray jsonArray=new JsonArray();
        for (int i=0;i<Array.getLength(javaObject);i++){
            jsonArray.add(javaObjectToJsonNode(Array.get(javaObject,i)));
        }
        return jsonArray;
} else  {

    }
    return null;
}
    public String serialize(Object javaObject) {
        JsonNode jsonNode=javaObjectToJsonNode(javaObject);

            return jsonNode.toString();
    }
}
