package entity;

import java.util.*;

public final class JsonArray extends JsonNode implements Iterable<JsonNode> {
    public static final JsonArray EMPTY = new JsonArray(Collections.emptyList());
    public final List<JsonNode> values;

    JsonArray(List<JsonNode> values) {
        this.values = values;
    }

    public JsonArray() {
        this.values = new ArrayList<>();
    }

    public JsonArray getArray(int pos) {
        return (JsonArray) values.get(pos);
    }

    public JsonObject getObject(int pos) {
        return (JsonObject) values.get(pos);
    }

    public JsonBoolean getBoolean(int pos) {
        return (JsonBoolean) values.get(pos);
    }

    public JsonString getString(int pos) {
        return (JsonString) values.get(pos);
    }

    public JsonNumber getNumber(int pos) {
        return (JsonNumber) values.get(pos);
    }


    public Object get(int index) {
        return values.get(index);
    }

    public int size() {
        return values.size();
    }


    @Override
    public String toString() {
        if (values.isEmpty()) return "[]";

        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Object e : values) {
            if (e instanceof String) {
                sj.add("\"" + e + "\"");
            } else {
                sj.add(e.toString());
            }
        }
        return sj.toString();
    }

    @Override
    public Iterator<JsonNode> iterator() {
        return values.iterator();
    }
}