package main.java.com.intexsoft.model;

import lombok.Data;

import java.util.*;
@Data
public final class JsonArray extends JsonNode implements Iterable<JsonNode> {
    public static final JsonArray EMPTY = new JsonArray(Collections.emptyList());
    public final List<JsonNode> values;

    JsonArray(List<JsonNode> values) {
        this.values = values;
    }

    public JsonArray() {
        this.values = new ArrayList<>();
    }

    public Object get(int index) {
        return values.get(index);
    }

    public void add(int index, JsonNode value) {
        values.add(index, value);
    }
    public void add(JsonNode value){values.add(value);}

    public List<JsonNode> getValues() {
        return values;
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