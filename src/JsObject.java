import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.BiConsumer;

public final class JsObject extends JsNode{
    static final JsObject EMPTY = new JsObject(Collections.emptyMap());
    final Map<String, JsNode> values;

    private JsObject(Map<String, JsNode> values) {
        this.values = values;
    }

    JsObject() {
        this.values = new HashMap<>();
    }

    public JsNode get(String key) {
        return values.get(key);
    }
    public JsArray getArray(String key) {
        return (JsArray) values.get(key);
    }

    public JsObject getObject(String key) {
        return (JsObject) values.get(key);
    }

    public JsNumber getNumber(String key) {
        return (JsNumber) values.get(key);
    }


    public JsString getString(String key) {
        return (JsString) values.get(key);
    }

    public JsBoolean getBoolean(String key) {
        return (JsBoolean) values.get(key);
    }
    public int size() {
        return values.size();
    }

    public void forEach(BiConsumer<String, JsNode> action) {
        values.forEach(action);
    }

    @Override
    public String toString() {
        if (values.isEmpty()) return "{}";

        StringJoiner sj = new StringJoiner(", ", "{", "}");
        for (Map.Entry<String, JsNode> e : values.entrySet()) {
            if (e.getValue() instanceof CharSequence) {
                sj.add("\"" + e.getKey() + "\":\"" + e.getValue() + '"');
            } else {
                sj.add("\"" + e.getKey() + "\":" + e.getValue());
            }
        }
        return sj.toString();
}

}

