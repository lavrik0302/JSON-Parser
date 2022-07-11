package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.BiConsumer;

public class JsonObject extends JsonNode {
    public static final JsonObject EMPTY = new JsonObject(Collections.emptyMap());
    public final Map<String, JsonNode> values;

    private JsonObject(Map<String, JsonNode> values) {
        this.values = values;
    }

    public JsonObject() {
        this.values = new HashMap<>();
    }

    public JsonNode get(String key) {
        return values.get(key);
    }

    public void add(String key, JsonNode value) {
        values.put(key, value);
    }

    public int size() {
        return values.size();
    }

    public void forEach(BiConsumer<String, JsonNode> action) {
        values.forEach(action);
    }

    @Override
    public String toString() {
        if (values.isEmpty()) return "{}";

        StringJoiner sj = new StringJoiner(", ", "{", "}");
        for (Map.Entry<String, JsonNode> e : values.entrySet()) {
            if (e.getValue() instanceof CharSequence) {
                sj.add("\"" + e.getKey() + "\":\"" + e.getValue() + '"');
            } else {
                sj.add("\"" + e.getKey() + "\":" + e.getValue());
            }
        }
        return sj.toString();
    }

}

