package model;

public class JsonNull extends JsonNode {
    private Object jsonNull;

    public Object getJsonNull() {
        return jsonNull;
    }

    @Override
    public String toString() {
        return "null";
    }
}
