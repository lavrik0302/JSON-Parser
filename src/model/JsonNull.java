package model;

public class JsonNull extends JsonNode {
    private Object jsonNull;

    public Object getJsonNull() {
        return jsonNull;
    }

    public void setJsonNull(Object jsonNull) {
        this.jsonNull = jsonNull;
    }

    @Override
    public String toString() {
        return "null";
    }
}
