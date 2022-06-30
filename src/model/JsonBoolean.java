package entity;

public class JsonBoolean extends JsonNode {
    boolean jsonBoolean;

    public void setJsonBoolean(boolean jsonBoolean) {
        this.jsonBoolean = jsonBoolean;
    }

    public boolean getJsonBoolean() {
        return jsonBoolean;
    }

    @Override
    public String toString() {
        return Boolean.toString(jsonBoolean);
    }
}

